import {
  Text,
  TouchableOpacity,
  Platform,
  TextInput,
  ScrollView,
} from 'react-native';
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
import md5 from 'md5';
import { styles } from './Style';
import Config from 'react-native-config';
import { useState } from 'react';
import { useUser } from '../hooks/useuser.hook';
import { useBiometricConfigs } from '../services/client.service';
import type { FaceType } from '../types/home';

export default function Home() {
  const { data: configs } = useBiometricConfigs();
  const { tokenBiometric, processId: process, logout } = useUser();
  const [processId, setProcessId] = useState(process);
  const [isInitialized, setIsInitialized] = useState(false);
  const [isEnabledVocal, setIsEnabledVocal] = useState(false);

  const isDisabledActions = !isInitialized || !processId;
  const opacity = !isDisabledActions ? 1 : 0.5;

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
      let isSuccess = false;

      switch (type) {
        case 'enroll':
          isSuccess = await enroll(data);
          break;
        case 'liveness':
          isSuccess = await liveness(data);
          break;
        case 'authenticate':
          isSuccess = await authenticate(data);
          break;
        case 'photoMatch':
          isSuccess = await photoMatch(data);
          break;
        case 'photoScan':
          isSuccess = await photoScan(data);
          break;
        default:
          isSuccess = false;
          break;
      }

      setIsEnabledVocal(false);
      console.log(type, isSuccess);
    } catch (error: any) {
      console.error(type, error.message);
    }
  };

  const onVocal = () => {
    setIsEnabledVocal((prev) => !prev);
    vocal();
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
        onVocal={(event) => console.log('onVocal', event)}
      >
        <TextInput
          placeholder="Process ID"
          autoCapitalize="none"
          autoCorrect={false}
          style={styles.loginInput}
          value={processId}
          onChangeText={setProcessId}
          placeholderTextColor="black"
          onSubmitEditing={onInitialize}
        />

        <TouchableOpacity
          style={[styles.button, { opacity: processId ? 1 : 0.5 }]}
          activeOpacity={0.8}
          disabled={!processId}
          onPress={onInitialize}
        >
          <Text style={styles.buttonText}>Initialize SDK</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('enroll')}
          disabled={isDisabledActions}
        >
          <Text style={styles.buttonText}>Enrollment</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('liveness')}
          disabled={isDisabledActions}
        >
          <Text style={styles.buttonText}>Liveness</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('authenticate')}
          disabled={isDisabledActions}
        >
          <Text style={styles.buttonText}>Authenticate</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('photoMatch')}
          disabled={isDisabledActions}
        >
          <Text style={styles.buttonText}>Photo Match</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('photoScan')}
          disabled={isDisabledActions}
        >
          <Text style={styles.buttonText}>Photo Scan</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={onVocal}
          disabled={isDisabledActions}
        >
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
