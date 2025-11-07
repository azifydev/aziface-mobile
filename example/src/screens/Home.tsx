import {
  Text,
  TouchableOpacity,
  Platform,
  ScrollView,
  type TouchableOpacityProps,
} from 'react-native';
import { useState } from 'react';
import {
  initialize,
  enroll,
  authenticate,
  liveness,
  photoMatch,
  photoScan,
  vocal,
  FaceView,
  type Params,
  type Headers,
  type Processor,
} from '@azify/aziface-mobile';
import * as pkg from '../../package.json';
import {
  getDeviceId,
  getFingerprintSync,
  getIpAddressSync,
  getSystemName,
  getUserAgent,
  syncUniqueId,
} from 'react-native-device-info';
import Config from 'react-native-config';
import md5 from 'md5';
import { styles } from './styles';
import { useUser } from '../hooks';
import { useBiometricConfigs } from '../services';
import type { FaceType } from '../types';

export function Home() {
  const { data: configs } = useBiometricConfigs();
  const { tokenBiometric, logout } = useUser();
  const [isInitialized, setIsInitialized] = useState(false);
  const [isEnabledVocal, setIsEnabledVocal] = useState(false);

  const commonButtonProps: TouchableOpacityProps = {
    style: [styles.button, { opacity: isInitialized ? 1 : 0.5 }],
    disabled: !isInitialized,
    activeOpacity: 0.8,
  };

  const onInitialize = async () => {
    const clientInfo = `${getSystemName?.()},${pkg?.version}`;
    const userAgent = await getUserAgent?.();
    const xForwardedFor = getIpAddressSync?.();
    const isAndroid = Platform.OS === 'android';

    const headers: Headers = {
      'x-token-bearer': tokenBiometric,
      'clientInfo': clientInfo,
      'contentType': 'application/json',
      'device': md5(
        isAndroid ? getFingerprintSync?.() : await syncUniqueId?.()
      ),
      'deviceid': getDeviceId?.(),
      'deviceip': xForwardedFor,
      'locale': 'pt-BR',
      'xForwardedFor': xForwardedFor,
      'user-agent': userAgent,
      'x-only-raw-analysis': '1',
    };

    const params: Params = {
      deviceKeyIdentifier: configs?.device || '',
      baseUrl: Config.API_URL_AZTECH,
      isDevelopment: false,
    };

    try {
      const initialized = await initialize({ params, headers });
      setIsInitialized(initialized);
      setIsEnabledVocal(false);
      console.log('isInitialized', initialized);
    } catch (error) {
      console.error('Initialize', error);
    }
  };

  const onFaceScan = async (type: FaceType, data?: any) => {
    try {
      let process: Processor | null = null;

      switch (type) {
        case 'enroll':
          process = await enroll(data);
          break;
        case 'liveness':
          process = await liveness(data);
          break;
        case 'authenticate':
          process = await authenticate(data);
          break;
        case 'photoMatch':
          process = await photoMatch(data);
          break;
        case 'photoScan':
          process = await photoScan(data);
          break;
        default:
          process = null;
          break;
      }

      setIsEnabledVocal(false);
      console.log(type, process);
    } catch (error: any) {
      console.error(type, error.message);
    }
  };

  const onVocal = (enabled: boolean) => {
    console.log('onVocal', enabled);
    setIsEnabledVocal(enabled);
  };

  return (
    <ScrollView
      style={styles.scroll}
      contentContainerStyle={styles.scrollContent}
      showsVerticalScrollIndicator={false}
    >
      <FaceView
        style={styles.content}
        onInitialize={(event) => console.log('onInitialize', event)}
        onOpen={(event) => console.log('onOpen', event)}
        onClose={(event) => console.log('onClose', event)}
        onCancel={(event) => console.log('onCancel', event)}
        onError={(event) => console.log('onError', event)}
        onVocal={onVocal}
      >
        <TouchableOpacity
          style={styles.button}
          activeOpacity={0.8}
          onPress={onInitialize}
        >
          <Text style={styles.buttonText}>Initialize SDK</Text>
        </TouchableOpacity>

        <TouchableOpacity
          {...commonButtonProps}
          onPress={() => onFaceScan('enroll')}
        >
          <Text style={styles.buttonText}>Enrollment</Text>
        </TouchableOpacity>

        <TouchableOpacity
          {...commonButtonProps}
          onPress={() => onFaceScan('liveness')}
        >
          <Text style={styles.buttonText}>Liveness</Text>
        </TouchableOpacity>

        <TouchableOpacity
          {...commonButtonProps}
          onPress={() => onFaceScan('authenticate')}
        >
          <Text style={styles.buttonText}>Authenticate</Text>
        </TouchableOpacity>

        <TouchableOpacity
          {...commonButtonProps}
          onPress={() => onFaceScan('photoMatch')}
        >
          <Text style={styles.buttonText}>Photo Match</Text>
        </TouchableOpacity>

        <TouchableOpacity
          {...commonButtonProps}
          onPress={() => onFaceScan('photoScan')}
        >
          <Text style={styles.buttonText}>Photo Scan</Text>
        </TouchableOpacity>

        <TouchableOpacity {...commonButtonProps} onPress={vocal}>
          <Text style={styles.buttonText}>
            Vocal {isEnabledVocal ? 'On' : 'Off'}
          </Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.buttonLogout} onPress={logout}>
          <Text style={styles.textLogout}>Logout</Text>
        </TouchableOpacity>
      </FaceView>
    </ScrollView>
  );
}
