# Configuração do FaceTecSDKForDevelopment (iOS) 🛠️

A partir da versão **10.1** do FaceTec SDK, a FaceTec distribui dois frameworks para iOS:

| Framework                              | Quando usar                                                           |
| -------------------------------------- | --------------------------------------------------------------------- |
| `FaceTecSDK.xcframework`               | Produção (Release, TestFlight, App Store) e simulador.                |
| `FaceTecSDKForDevelopment.xcframework` | Builds **Debug** executados pelo **Xcode** em **dispositivo físico**. |

Este documento explica o problema e como a biblioteca troca o framework **automaticamente** durante o `pod install`, sem configuração no projeto de quem usa `@azify/aziface-mobile`.

<hr/>

## Sumário

- [O problema](#o-problema)
- [Como a biblioteca resolve](#como-a-biblioteca-resolve)
  - [1. Framework de desenvolvimento no pacote](#1-framework-de-desenvolvimento-no-pacote)
  - [2. Build settings do pod](#2-build-settings-do-pod)
  - [3. Script phase do pod](#3-script-phase-do-pod)
  - [4. Como validar](#4-como-validar)
- [O que o usuário da biblioteca precisa fazer](#o-que-o-usuário-da-biblioteca-precisa-fazer)
  - [Desativar a troca](#desativar-a-troca)
- [Perguntas frequentes](#perguntas-frequentes)

<hr/>

## O problema

Ao executar o app em **Debug**, pelo **Xcode**, em um **iPhone físico**, a chamada `FaceTec.sdk.initializeWithSessionRequest(...)` quebrava com:

```
Thread 1: EXC_BAD_ACCESS (code=1, address=0x40) em objc_msgSend
```

O local do crash variava (às vezes na linha do `initializeWithSessionRequest`, às vezes no `DispatchQueue.main.async`) e não acontecia no simulador. A causa **não** era o código Swift nem `FaceTec.sdk` ser `nil`: o binário de **produção** do SDK se comporta mal de propósito quando detecta que está rodando no Xcode. O próprio binário contém o aviso:

```
************************************************************************
* WARNING: You appear to be using the Production FaceTec SDK in Xcode. *
* You must use FaceTecSDKForDevelopment to avoid bad behavior.         *
************************************************************************
```

A solução oficial da FaceTec é usar, **somente em builds Debug para dispositivo**, o `FaceTecSDKForDevelopment` no lugar do framework de produção. A FaceTec sugere fazer isso com uma build phase no target do app; esta biblioteca faz o mesmo **dentro do próprio pod**, para que funcione sem configuração no app.

> [!NOTE]
> Também movemos o corpo de `initialize` em [`ios/Aziface.swift`](../ios/Aziface.swift) para dentro de `DispatchQueue.main.async`. Métodos de TurboModule rodam em uma fila de background e o SDK deve ser inicializado na main thread. Essa mudança é correta, mas **não** era a causa do crash.

<hr/>

## Como a biblioteca resolve

Tudo é configurado no [`AzifaceMobile.podspec`](../AzifaceMobile.podspec) e aplicado no `pod install`.

### 1. Framework de desenvolvimento no pacote

O `FaceTecSDKForDevelopment.xcframework` (mesma versão do SDK de produção, **10.1.21**) fica em:

```
ios/Frameworks/FaceTecSDK.xcframework                 # produção
ios/Frameworks/FaceTecSDKForDevelopment.xcframework   # desenvolvimento
```

Os dois são publicados no npm (campo `files` do [`package.json`](../package.json)). No podspec, o framework de desenvolvimento é declarado em `preserve_paths`, pois ele não é linkado diretamente: só é copiado pelo script.

```ruby
s.vendored_frameworks = "ios/Frameworks/FaceTecSDK.xcframework"
s.preserve_paths = "ios/Frameworks/FaceTecSDKForDevelopment.xcframework"
```

### 2. Build settings do pod

O podspec define build settings **apenas no target do pod** (`AzifaceMobile`), sem alterar o target do app:

```ruby
s.pod_target_xcconfig = {
  "ENABLE_USER_SCRIPT_SANDBOXING" => "NO",
  "USE_FACETEC_DEVELOPMENT_FRAMEWORK[sdk=iphoneos*]" => ENV.fetch("USE_FACETEC_DEVELOPMENT_FRAMEWORK", "YES")
}
```

| Setting                                            | Valor          | Motivo                                                                                          |
| -------------------------------------------------- | -------------- | ----------------------------------------------------------------------------------------------- |
| `USE_FACETEC_DEVELOPMENT_FRAMEWORK[sdk=iphoneos*]` | `YES` (padrão) | Liga a troca apenas para dispositivo físico. Pode ser desligada no `pod install`.               |
| `ENABLE_USER_SCRIPT_SANDBOXING`                    | `NO`           | Com sandbox, o script não consegue ler o framework de desenvolvimento nem sobrescrever a cópia. |

> [!NOTE]
> O `pod_target_xcconfig` precisa ser definido **antes** de `install_modules_dependencies(s)`, que mescla suas próprias configurações com as já existentes.

### 3. Script phase do pod

O CocoaPods embute um `.xcframework` em duas etapas:

1. **`[CP] Copy XCFrameworks`** (target do pod): copia a fatia correta (`ios-arm64` ou simulador) para `${PODS_XCFRAMEWORKS_BUILD_DIR}/AzifaceMobile/FaceTecSDK.framework`.
2. **`[CP] Embed Pods Frameworks`** (target do app): copia essa cópia para dentro do `.app` e **assina** o framework.

O podspec adiciona uma `script_phase` ao target do pod que roda **entre** essas duas etapas (`execution_position: :after_compile`) e substitui a cópia intermediária pela fatia `ios-arm64` do `FaceTecSDKForDevelopment`. Assim, o app embute e assina o framework de desenvolvimento normalmente, sem nenhuma build phase no projeto do usuário.

Ordem das fases no target `AzifaceMobile` após o `pod install`:

```
[CP] Copy XCFrameworks
Sources
Frameworks
Copy generated compatibility header
[CP-User] [AZIFACE] Swap FaceTecSDK For Development
```

O script só faz a troca quando **as duas condições** são verdadeiras:

- `USE_FACETEC_DEVELOPMENT_FRAMEWORK == YES`: só é definido para `sdk=iphoneos*`, então o **simulador** é ignorado.
- `GCC_PREPROCESSOR_DEFINITIONS` contém `DEBUG=1`: o CocoaPods define isso nas configurações do tipo debug (inclusive configurações customizadas mapeadas como `:debug` no `Podfile`), então **Release** é ignorado.

Além disso, o script:

- Compara a versão dos dois frameworks e **falha o build** se forem diferentes.
- Emite um `warning:` e mantém o SDK de produção se o framework de desenvolvimento não existir.

### 4. Como validar

No log do build Debug para dispositivo deve aparecer:

```
FaceTecSDK: Framework has been replaced with FaceTecSDKForDevelopment
```

Em builds para simulador ou Release, o log mostra `Skipping! FaceTecSDKForDevelopment not requested.`

Para conferir o binário embutido no app (deve retornar `0`):

```bash
strings -a <DerivedData>/Build/Products/Debug-iphoneos/<App>.app/Frameworks/FaceTecSDK.framework/FaceTecSDK \
  | grep -c 'Production FaceTec SDK in Xcode'
```

<hr/>

## O que o usuário da biblioteca precisa fazer

**Nada**, além do fluxo normal:

```bash
npm install @azify/aziface-mobile
cd ios && pod install
```

Não é necessário criar build phase, variável ou alterar o `Podfile`. Ao rodar em Debug num dispositivo físico pelo Xcode, o app já usa o `FaceTecSDKForDevelopment`. Em Release, TestFlight, App Store e simulador, o app usa o SDK de produção.

> [!IMPORTANT]
> A configuração fica no projeto de Pods, que é gerado no `pod install`. Após atualizar a biblioteca, rode `pod install` novamente. Em projetos **Expo**, o `npx expo prebuild` já executa o `pod install`, então nada muda.

### Desativar a troca

Para usar sempre o SDK de produção (por exemplo, para reproduzir um comportamento de produção), rode o `pod install` com a variável de ambiente:

```bash
USE_FACETEC_DEVELOPMENT_FRAMEWORK=NO pod install
```

Para reativar, rode `pod install` novamente sem a variável.

<hr/>

## Perguntas frequentes

**O framework de desenvolvimento vai para produção?**
Não. A troca só acontece em configurações debug para `iphoneos`. Em Release, o script termina com `Skipping!` e o app usa o SDK de produção.

**Preciso disso no simulador?**
Não. O simulador usa a fatia `ios-arm64_x86_64-simulator` do SDK de produção, que não tem esse comportamento.

**Como atualizo o FaceTec SDK na biblioteca?**
Substitua os dois frameworks em `ios/Frameworks/` (`FaceTecSDK.xcframework` e `FaceTecSDKForDevelopment.xcframework`), sempre na mesma versão. Se as versões forem diferentes, o build Debug em dispositivo falha com `error: FaceTecSDK X != FaceTecSDKForDevelopment Y.`

**Apareceu `warning: FaceTecSDKForDevelopment not found`.**
O framework de desenvolvimento não está no pacote instalado. O build continua com o SDK de produção, que pode quebrar ao rodar pelo Xcode em dispositivo.

**O app do usuário também precisa desabilitar o User Script Sandboxing?**
Não para a troca do FaceTec: o `ENABLE_USER_SCRIPT_SANDBOXING = NO` é aplicado só ao target do pod. (Projetos React Native geralmente já usam `NO` no app por causa da fase _Bundle React Native code and images_.)

**A troca parou de funcionar depois de atualizar a biblioteca.**
Rode `pod install` novamente para regenerar o projeto de Pods com o podspec atualizado.
