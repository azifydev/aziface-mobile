/* eslint-disable react-native/no-inline-styles */
/* eslint-disable react/react-in-jsx-scope */
import {
  View,
  Text,
  TouchableOpacity,
  NativeEventEmitter,
  type NativeModule,
  Platform,
} from 'react-native';
import {
  AzifaceMobileSdk,
  enroll,
  initialize,
  photoMatch,
} from '@azify/aziface-mobile';
import { useUser } from './hooks/useuser.hook';
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
import { useConfigs, useCreateProcess } from './services/aziface.service';
import { azifaceBaseURL } from './services/azifaceApi';
import { styles } from './Style';

export default function Aziface() {
  const { tokenBiometric, processId, isInitialized, setIsInitialized } =
    useUser();
  const { data: configs } = useConfigs();
  const { mutateAsync: createProcess } = useCreateProcess();
  const onPressInit = async () => {
    console.log('onPressInit');
    if (!processId) {
      await createProcess();
    }
    const clientInfo = `${getSystemName?.()},${pkg?.version}`;
    const isAndroid = Platform.OS === 'android';
    const userAgent = await getUserAgent?.();
    const xForwardedFor = await getIpAddressSync?.();
    const headers = {
      'authorization': tokenBiometric,
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
    const params = {
      isDeveloperMode: true,
      processId: processId,
      device: configs?.device || '',
      url: azifaceBaseURL,
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
      <TouchableOpacity style={styles.button} activeOpacity={0.8}>
        <Text style={styles.buttonText} onPress={onPressInit}>
          Init Aziface sdk
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[styles.button, { opacity: isInitialized ? 1 : 0.5 }]}
        activeOpacity={0.8}
        onPress={onPressEnroll}
        disabled={!isInitialized}
      >
        <Text style={styles.buttonText}>Enrollment</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[styles.button, { opacity: isInitialized ? 1 : 0.5 }]}
        activeOpacity={0.8}
        onPress={onPressPhotoMatch}
        disabled={!isInitialized}
      >
        <Text style={styles.buttonText}>Photo Match</Text>
      </TouchableOpacity>
    </View>
  );
}
