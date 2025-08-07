/* eslint-disable react-native/no-inline-styles */
/* eslint-disable react/react-in-jsx-scope */
import {
  View,
  Text,
  TouchableOpacity,
  NativeEventEmitter,
  type NativeModule,
  Platform,
  TextInput,
} from 'react-native';
import {
  AzifaceMobileSdk,
  enroll,
  initialize,
  photoMatch,
} from '@azify/aziface-mobile';
import * as pkg from '../package.json';
import {
  getDeviceId,
  getFingerprintSync,
  getIpAddressSync,
  getSystemName,
  getUserAgent,
  syncUniqueId,
} from 'react-native-device-info';
import md5 from 'md5';
import { useBiometricConfigs } from './services/assemble.service';
import { styles } from './Style';
import Config from 'react-native-config';
import { useState } from 'react';

export default function Aziface() {
  const { data: configs } = useBiometricConfigs();
  const [processId, setProcessId] = useState('');
  const [isInitialized, setIsInitialized] = useState(false);
  const isDisabledActions = !isInitialized || !processId;

  const onPressInit = async () => {
    const clientInfo = `${getSystemName?.()},${pkg?.version}`;
    const isAndroid = Platform.OS === 'android';
    const userAgent = await getUserAgent?.();
    const xForwardedFor = await getIpAddressSync?.();
    const headers = {
      'x-api-key': Config.X_API_KEY,
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
      processId: processId,
      device: configs?.device || '',
      url: Config.API_URL_AZTECH,
      key: configs?.key || '',
      productionKey: configs?.productionKey || '',
    };

    try {
      const isInit = await initialize({
        params,
        headers,
      });
      setIsInitialized(isInit);
      console.log('isInitialized', isInit);
    } catch (error) {
      console.error('ERROR initializing SDK', error);
    }
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

  return (
    <View style={styles.azifaceContent}>
      <TextInput
        placeholder="Process ID"
        autoCapitalize="none"
        autoCorrect={false}
        style={styles.loginInput}
        value={processId}
        onChangeText={setProcessId}
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
    </View>
  );
}
