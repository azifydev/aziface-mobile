import { View, Text, TextInput, TouchableOpacity, Alert } from 'react-native';
import { useState } from 'react';
import { useBiometricSession, useLogin } from '../services';
import { styles } from './styles';

export function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { mutateAsync: login, isPending: isPendingLogin } = useLogin();
  const { mutateAsync: createSession, isPending: isPendingSession } =
    useBiometricSession();

  const handleLogin = async () => {
    try {
      await login({ username, password });
      await createSession();
    } catch (error: any) {
      Alert.alert('Login Error', error.message);
    }
  };
  const isPending = isPendingSession || isPendingLogin;
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
