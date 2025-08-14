/* eslint-disable react/react-in-jsx-scope */
import { View, Text, TextInput, TouchableOpacity, Alert } from 'react-native';
import { styles } from './Style';
import { useState } from 'react';
import { useBiometricLogin } from './services/assemble.service';
import Config from 'react-native-config';

export default function Login() {
  const [password, setPassword] = useState('');
  const { mutateAsync: login, isPending: isPendingLogin } = useBiometricLogin();

  const handleLogin = async () => {
    try {
      await login(Config.ID);
    } catch (error) {
      Alert.alert(
        'Login Error',
        'An error occurred while trying to log in. Please try again later.'
      );
    }
  };
  const isLoading = isPendingLogin || !password;
  const opacity = isLoading ? 0.5 : 1;
  return (
    <View style={styles.loginContent}>
      <Text style={styles.title}>Login</Text>
      <TextInput
        placeholder="Password"
        autoCapitalize="none"
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
          {isPendingLogin ? 'Loading...' : 'Confirm'}
        </Text>
      </TouchableOpacity>
    </View>
  );
}
