/* eslint-disable react-native/no-inline-styles */
/* eslint-disable react/react-in-jsx-scope */
import { View, Text, TextInput, TouchableOpacity, Alert } from 'react-native';
import { styles } from './Style';
import { useState } from 'react';
import {
  useLogin,
  useDev,
  type DevResponseData,
  useCreateBiometricSession,
  useCreateBiometricId,
} from './services/symphony.service';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const { mutateAsync: login, isPending: isPendingLogin } = useLogin();
  const { mutateAsync: dev, isPending: isPendingDev } = useDev();
  const {
    mutateAsync: createBiometricId,
    isPending: isPendingCreateBiometricId,
  } = useCreateBiometricId();
  const {
    mutateAsync: createBiometricSession,
    isPending: isPendingCreateBiometricSession,
  } = useCreateBiometricSession();

  const handleBiometrics = async () => {
    try {
      const biometrics = await createBiometricId();
      if (biometrics?.id) {
        await createBiometricSession(biometrics?.id);
      }
    } catch (error) {
      // console.log('Biometrics error:', error);
    }
  };

  const handleLoginCode = async (token: string, uuidVerification: string) => {
    try {
      const res = await login({
        username,
        password,
        uuidVerification,
        token,
      });

      if (!res?.token) {
        Alert.alert(
          'Login Error',
          'An error occurred while trying to log in with code. Please try again later.'
        );
      } else {
        await handleBiometrics();
      }
    } catch (error) {
      // console.log('Login with code response error:', error);
      Alert.alert(
        'Login Error',
        'An error occurred while trying to log in with code. Please try again later.'
      );
    }
  };

  const getCode = (codes: DevResponseData, uuidVerification: string) => {
    if (codes && codes?.verifications?.length > 0) {
      const verifications = codes?.verifications?.filter(
        (code) => code.value === username
      );
      handleLoginCode(verifications?.[0]?.token || '', uuidVerification);
    }
  };

  const handleLogin = async () => {
    try {
      const res = await login({
        username,
        password,
      });
      // console.log('Login response:', res);
      if (res?.uuidVerification && !res?.twoFa) {
        const codes = await dev();
        getCode(codes, res?.uuidVerification);
      } else {
        Alert.alert(
          'Login Error',
          'An error occurred while trying to log in. Please try again later.'
        );
      }
    } catch (error) {
      // console.log('Login response error:', error);

      Alert.alert(
        'Login Error',
        'An error occurred while trying to log in. Please try again later.'
      );
    }
  };
  const isLoding =
    isPendingLogin ||
    isPendingDev ||
    isPendingCreateBiometricId ||
    isPendingCreateBiometricSession;
  return (
    <View style={styles.loginContent}>
      <Text style={styles.title}>Login</Text>
      <TextInput
        placeholder="Email or Username"
        autoCapitalize="none"
        autoCorrect={false}
        style={styles.loginInput}
        onChangeText={setUsername}
      />
      <TextInput
        placeholder="Password"
        autoCapitalize="none"
        autoCorrect={false}
        secureTextEntry
        style={styles.loginInput}
        onChangeText={setPassword}
      />
      <TouchableOpacity
        style={styles.button}
        activeOpacity={0.8}
        onPress={handleLogin}
      >
        <Text
          style={[styles.buttonText, { opacity: isLoding ? 0.5 : 1 }]}
          disabled={isLoding}
        >
          {isLoding ? 'Loading...' : 'Confirm'}
        </Text>
      </TouchableOpacity>
    </View>
  );
}
