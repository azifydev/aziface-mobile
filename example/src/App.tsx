import * as React from 'react';

import {
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
  ScrollView,
  NativeEventEmitter,
  NativeModule,
} from 'react-native';
import {
  AzifaceMobileSdk,
  authenticate,
  enroll,
  initialize,
  photoMatch,
} from '@azify/aziface-mobile';

export default function App() {
  const init = async () => {
    /*
     * The SDK must be initialized first
     * so that the rest of the library
     * functions can work!
     *
     * */

    const headers = {
      'clientInfo': 'YUOR_CLIENT_INFO',
      'contentType': 'YOUR_CONTENT_TYPE',
      'device': 'YOUR_DEVICE',
      'deviceid': 'YOUR_DEVICE_ID',
      'deviceip': 'YOUR_DEVICE_IP',
      'locale': 'YOUR_LOCALE',
      'xForwardedFor': 'YOUR_X_FORWARDED_FOR',
      'user-agent': 'YOUR_USER_AGENT',
      'x-token-bearer': 'YOUR_X_TOKEN_BEARER',
      'x-only-raw-analysis': '1',
    };
    const params = {
      device: 'YOUR_DEVICE',
      url: 'YOUR_BASE_URL',
      key: 'YOUR_KEY',
      productionKey: 'YOUR_PRODUCTION_KEY',
      processId: 'USER_PROCESS_ID',
    };

    const isInitialized = await initialize({
      params,
      headers,
    });

    console.log('isInitialized', isInitialized);
  };
  const emitter = new NativeEventEmitter(
    AzifaceMobileSdk as unknown as NativeModule
  );
  emitter.addListener('onCloseModal', (event: boolean) =>
    console.log('onCloseModal', event)
  );

  const onPressPhotoMatch = async () => {
    try {
      const isSuccess = await photoMatch({});
      console.log('onPressPhotoMatch', isSuccess);
      console.log(isSuccess);
    } catch (error: any) {
      console.error('ERROR onPressPhotoMatch', error.message);
      console.error(error.message);
    }
  };

  const onPressEnroll = async () => {
    try {
      const isSuccess = await enroll({});
      console.log('onPressEnroll', isSuccess);
    } catch (error: any) {
      console.error('ERROR onPressEnroll', error.message);
    }
  };

  const onPressAuthenticate = async () => {
    try {
      const isSuccess = await authenticate({});
      console.log('onPressAuthenticate', isSuccess);
      console.log(isSuccess);
    } catch (error: any) {
      console.error('ERROR onPressAuthenticate', error.message);
      console.error(error.message);
    }
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.content}>
        <TouchableOpacity style={styles.button} onPress={init}>
          <Text style={styles.text}>Init Aziface Module</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={onPressPhotoMatch}>
          <Text style={styles.text}>Open Photo Match</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={onPressEnroll}>
          <Text style={styles.text}>Open Enroll</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={onPressAuthenticate}>
          <Text style={styles.text}>Open Authenticate</Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  content: {
    justifyContent: 'center',
    alignItems: 'center',
    marginVertical: 30,
  },
  button: {
    width: '100%',
    backgroundColor: '#4a68b3',
    padding: 20,
    borderRadius: 15,
    alignItems: 'center',
    justifyContent: 'center',
    marginVertical: 20,
  },
  text: {
    color: 'white',
    fontWeight: '700',
    fontSize: 22,
  },
});
