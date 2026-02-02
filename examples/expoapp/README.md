# Expo App

## A basic Expo App

This example contains initialize, enroll, authenticate, liveness, photo match, photo scan and vocal guidance methods.

##### Prepare your environment:

```sh
npm install # or yarn install
```

##### Install pods:

```sh
cd ios && pod install && cd ..
```

##### Before, run prebuild command:

```sh
npx expo prebuild
```

##### In the `./android` folder, you must create the `local.properties` file, which is required to work in Android:

```properties
sdk.dir=ANDROID_SDK_DIR
```

##### Now, in the `./app/index.tsx` file, search for the `onInitialize` function. You must add your credentials to initialize Aziface SDK correctly:

```tsx
async function onInitialize() {
  // ...

  const headers: Headers = {
    'x-token-bearer': 'YOUR_TOKEN_BEARER',
    // ...
  };

  const initialized = await initialize({
    headers,
    params: {
      baseUrl: 'YOUR_BASE_URL',
      deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
    },
  });

  // ...
}

// ...
```

The processors will be available when the SDK is initialized.

##### Clean your Gradle (Recommended):

```sh
# ./android
./gradlew clean
```

##### Finally, start Expo App and execute to each platform:

```sh
npm run start # or yarn start
npm run android # or yarn android
npm run ios # or yarn ios
```
