import { View, Text, TextInput, TouchableOpacity, Alert } from 'react-native';
import { styles } from './Style';
import { useState } from 'react';
import {
  useBiometricSession,
  useCreateBiometric,
  useLogin,
} from '../services/client.service';
import { useUser } from '../hooks/useuser.hook';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { processId } = useUser();
  const { mutateAsync: login, isPending: isPendingLogin } = useLogin();
  const { mutateAsync: createBiometric, isPending: isPendingBiometric } =
    useCreateBiometric();
  const { mutateAsync: createSession, isPending: isPendingSession } =
    useBiometricSession();

  const handleBiometric = async () => {
    try {
      if (!processId) {
        await createBiometric();
      }
      await createSession();
    } catch (error: any) {
      Alert.alert('Generate Biometric Error');
    }
  };
  const handleLogin = async () => {
    try {
      await login({ username, password });
      await handleBiometric();
    } catch (error: any) {
      Alert.alert('Login Error', error.message);
    }
  };
  const isPending = isPendingBiometric || isPendingSession || isPendingLogin;
  const isLoading = isPending || !password || !username;
  const opacity = isLoading ? 0.5 : 1;
  return (
    <View style={styles.loginContent}>
      <TextInput
        placeholder="Username"
        autoCapitalize="none"
        autoCorrect={false}
        style={styles.loginInput}
        placeholderTextColor="black"
        onChangeText={setUsername}
      />
      <TextInput
        placeholder="Password"
        autoCapitalize="none"
        placeholderTextColor="black"
        autoCorrect={false}
        secureTextEntry
        style={styles.loginInput}
        onChangeText={setPassword}
      />
      <TouchableOpacity
        style={[styles.button, { opacity }]}
        activeOpacity={0.8}
        onPress={handleLogin}
        disabled={isLoading}
      >
        <Text style={styles.buttonText}>
          {isLoading ? 'Loading...' : 'Confirm'}
        </Text>
      </TouchableOpacity>
    </View>
  );
}
