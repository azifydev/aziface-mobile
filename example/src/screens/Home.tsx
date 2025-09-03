import { Text, TouchableOpacity, Platform, TextInput } from 'react-native';
import {
  enroll,
  initialize,
  photoMatch,
  setTheme,
  FaceView,
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
import { useBiometricConfigs } from '../services/client.service';
import { styles } from './Style';
import Config from 'react-native-config';
import { useState } from 'react';
import { useUser } from '../hooks/useuser.hook';

export default function Home() {
  const { data: configs } = useBiometricConfigs();
  const { tokenBiometric, processId: process, logout } = useUser();
  const [processId, setProcessId] = useState(process);
  const [isInitialized, setIsInitialized] = useState(false);

  const isDisabledActions = !isInitialized || !processId;

  const onPressInit = async () => {
    const clientInfo = `${getSystemName?.()},${pkg?.version}`;
    const isAndroid = Platform.OS === 'android';
    const userAgent = await getUserAgent?.();
    const xForwardedFor = getIpAddressSync?.();
    const headers = {
      'x-token-bearer': `${tokenBiometric}`,
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

    const params = {
      isDeveloperMode: true,
      processId: processId || '',
      device: configs?.device || '',
      url: Config.API_URL_AZTECH,
      key: configs?.key || '',
      productionKey: configs?.productionKey || '',
    };

    try {
      setTheme({
        image: {
          logo: 'brand_logo',
        },
      });
      const isInit = await initialize({ params, headers });
      setIsInitialized(isInit);
      console.log('isInitialized', isInit);
    } catch (error) {
      console.error('ERROR initializing SDK', error);
    }
  };

  const onPressPhotoMatch = async () => {
    try {
      const isSuccess = await photoMatch();
      console.log('onPressPhotoMatch', isSuccess);
    } catch (error: any) {
      console.error('ERROR onPressPhotoMatch', error.message);
    }
  };

  const onPressEnroll = async () => {
    try {
      const isSuccess = await enroll();
      console.log('onPressEnroll', isSuccess);
    } catch (error: any) {
      console.error('ERROR onPressEnroll', error.message);
    }
  };

  return (
    <FaceView style={styles.azifaceContent}>
      <TextInput
        placeholder="Process ID"
        autoCapitalize="none"
        autoCorrect={false}
        style={styles.loginInput}
        value={processId}
        onChangeText={setProcessId}
        placeholderTextColor="gray"
        onSubmitEditing={onPressInit}
      />
      <TouchableOpacity
        style={[styles.button, { opacity: processId ? 1 : 0.5 }]}
        activeOpacity={0.8}
        disabled={!processId}
        onPress={onPressInit}
      >
        <Text style={styles.buttonText}>Init Aziface sdk</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[styles.button, { opacity: !isDisabledActions ? 1 : 0.5 }]}
        activeOpacity={0.8}
        onPress={onPressEnroll}
        disabled={isDisabledActions}
      >
        <Text style={styles.buttonText}>Enrollment</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[styles.button, { opacity: !isDisabledActions ? 1 : 0.5 }]}
        activeOpacity={0.8}
        onPress={onPressPhotoMatch}
        disabled={isDisabledActions}
      >
        <Text style={styles.buttonText}>Photo Match</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.buttonLogout} onPress={logout}>
        <Text style={styles.textLogout}>Logout</Text>
      </TouchableOpacity>
    </FaceView>
  );
}
