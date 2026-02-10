import {
  Alert,
  Text,
  TouchableOpacity,
  StyleSheet,
  Platform,
} from 'react-native';
import {
  FaceView,
  initialize,
  enroll,
  authenticate,
  liveness,
  photoScan,
  photoMatch,
  setLocale,
  setDynamicStrings,
  resetDynamicStrings,
  vocal,
  type Processor,
  type Headers,
  type Locale,
} from '@azify/aziface-mobile';
import {
  getDeviceId,
  getFingerprintSync,
  getIpAddressSync,
  getSystemName,
  getUserAgent,
  syncUniqueId,
} from 'react-native-device-info';
import md5 from 'md5';
import { useState } from 'react';
import { LOCALES, DYNAMIC_STRINGS } from '../constants';

type FaceType =
  | 'enroll'
  | 'authenticate'
  | 'liveness'
  | 'photoScan'
  | 'photoMatch';

export default function Screen() {
  const [isInitialized, setIsInitialized] = useState(false);
  const [isEnabledVocal, setIsEnabledVocal] = useState(false);
  const [isDynamicStringsEnabled, setIsDynamicStringsEnabled] = useState(false);
  const [localization, setLocalization] = useState<Locale>('default');

  async function onInitialize() {
    const clientInfo = getSystemName();
    const userAgent = await getUserAgent();
    const xForwardedFor = getIpAddressSync();
    const isAndroid = Platform.OS === 'android';

    const headers: Headers = {
      'x-token-bearer': 'YOUR_TOKEN_BEARER',
      'clientInfo': clientInfo,
      'contentType': 'application/json',
      'device': md5(isAndroid ? getFingerprintSync() : await syncUniqueId()),
      'deviceid': getDeviceId(),
      'deviceip': xForwardedFor,
      'locale': 'pt-BR',
      'xForwardedFor': xForwardedFor,
      'user-agent': userAgent,
      'x-only-raw-analysis': '1',
    };

    const initialized = await initialize({
      headers,
      params: {
        baseUrl: 'YOUR_BASE_URL',
        deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
      },
    });

    setIsInitialized(initialized);
    setIsEnabledVocal(false);

    if (!initialized) {
      Alert.alert('Initialization failed');
    }
  }

  async function onFace(type: FaceType) {
    try {
      let result: Processor | null = null;

      switch (type) {
        case 'enroll':
          result = await enroll();
          break;
        case 'authenticate':
          result = await authenticate();
          break;
        case 'liveness':
          result = await liveness();
          break;
        case 'photoMatch':
          result = await photoMatch();
          break;
        case 'photoScan':
          result = await photoScan();
          break;
        default:
          result = null;
          break;
      }

      console.log('Result', result);
    } catch (error) {
      console.log('Exception', error);
    }

    setIsEnabledVocal(false);
  }

  function onVocal(enabled: boolean) {
    setIsEnabledVocal(enabled);
  }

  const onLocale = () => {
    const localeIndex = Math.floor(Math.random() * (LOCALES.length - 2));
    const value = LOCALES.filter((l) => l !== localization)[localeIndex]!;

    setLocalization(value);
    setLocale(value);
  };

  const onResetLocale = () => {
    setLocalization('default');
    setLocale('default');
  };

  const onDynamicStrings = () => {
    if (isDynamicStringsEnabled) {
      setIsDynamicStringsEnabled(false);
      resetDynamicStrings();
    } else {
      setIsDynamicStringsEnabled(true);
      setDynamicStrings(DYNAMIC_STRINGS);
    }
  };

  const opacityStyle = { opacity: isInitialized ? 1 : 0.5 };
  const vocalStyle = {
    backgroundColor: isEnabledVocal ? 'green' : 'red',
    ...opacityStyle,
  };

  return (
    <FaceView
      onInitialize={(event) => console.log('onInitialize', event)}
      onOpen={(event) => console.log('onOpen', event)}
      onClose={(event) => console.log('onClose', event)}
      onCancel={(event) => console.log('onCancel', event)}
      onError={(event) => console.log('onError', event)}
      onVocal={onVocal}
      style={styles.container}
    >
      <TouchableOpacity onPress={onInitialize} style={styles.button}>
        <Text style={styles.text}>Initialize SDK</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => onFace('enroll')}
        disabled={!isInitialized}
        style={[styles.button, opacityStyle]}
      >
        <Text style={styles.text}>Enroll</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => onFace('authenticate')}
        disabled={!isInitialized}
        style={[styles.button, opacityStyle]}
      >
        <Text style={styles.text}>Authenticate</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => onFace('liveness')}
        disabled={!isInitialized}
        style={[styles.button, opacityStyle]}
      >
        <Text style={styles.text}>Liveness</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => onFace('photoMatch')}
        disabled={!isInitialized}
        style={[styles.button, opacityStyle]}
      >
        <Text style={styles.text}>Photo Match</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => onFace('photoScan')}
        disabled={!isInitialized}
        style={[styles.button, opacityStyle]}
      >
        <Text style={styles.text}>Photo Scan</Text>
      </TouchableOpacity>

      <TouchableOpacity
        disabled={!isInitialized}
        style={[styles.button, opacityStyle]}
        onPress={onLocale}
        onLongPress={onResetLocale}
      >
        <Text style={styles.text}>Localization: {localization}</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={vocal} style={[styles.button, vocalStyle]}>
        <Text style={styles.text}>Vocal {isEnabledVocal ? 'On' : 'Off'}</Text>
      </TouchableOpacity>

      <TouchableOpacity
        disabled={!isInitialized}
        style={[styles.button, opacityStyle]}
        onPress={onDynamicStrings}
      >
        <Text style={styles.text}>
          {isDynamicStringsEnabled
            ? 'Reset Dynamic Strings'
            : 'Set Dynamic Strings'}
        </Text>
      </TouchableOpacity>
    </FaceView>
  );
}

export const styles = StyleSheet.create({
  container: {
    flex: 1,

    justifyContent: 'center',
    alignItems: 'center',

    padding: 20,

    gap: 20,
  },

  button: {
    width: '100%',
    height: 48,

    justifyContent: 'center',
    alignItems: 'center',

    backgroundColor: 'blue',

    borderRadius: 8,
  },

  text: {
    color: 'white',
    fontWeight: 'bold',

    textAlign: 'center',
  },
});
