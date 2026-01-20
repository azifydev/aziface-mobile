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
  vocal,
  type Processor,
  type Headers,
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

type FaceType =
  | 'enroll'
  | 'authenticate'
  | 'liveness'
  | 'photoScan'
  | 'photoMatch';

export default function Screen() {
  const [isInitialized, setIsInitialized] = useState(false);
  const [isEnabledVocal, setIsEnabledVocal] = useState(false);

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

      <TouchableOpacity onPress={vocal} style={[styles.button, vocalStyle]}>
        <Text style={styles.text}>Vocal {isEnabledVocal ? 'On' : 'Off'}</Text>
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
