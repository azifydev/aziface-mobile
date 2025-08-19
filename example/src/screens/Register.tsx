import {
  Text,
  TextInput,
  TouchableOpacity,
  Alert,
  KeyboardAvoidingView,
} from 'react-native';
import { styles } from './Style';
import { useState } from 'react';
import { useCreate } from '../services/client.service';
import { useNavigation } from '@react-navigation/native';

export default function Register() {
  const [name, setName] = useState('');
  const [assembleUserId, setAssembleUserId] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { mutateAsync: register, isPending } = useCreate();
  const navigation = useNavigation();

  const handleRegister = async () => {
    try {
      await register({
        name,
        assembleUserId,
        username,
        password,
      });
      Alert.alert('Register Success', 'User registered successfully');
      setTimeout(() => {
        navigation.goBack();
      }, 1500);
    } catch (error: any) {
      Alert.alert('Register Error', error.message);
    }
  };
  const isLoading =
    isPending || !password || !name || !assembleUserId || !username;
  const opacity = isLoading ? 0.5 : 1;
  return (
    <KeyboardAvoidingView
      keyboardVerticalOffset={100}
      behavior="height"
      style={styles.registerContent}
    >
      <TextInput
        placeholder="Name"
        autoCapitalize="none"
        autoCorrect={false}
        style={styles.loginInput}
        onChangeText={setName}
      />
      <TextInput
        placeholder="User name"
        autoCapitalize="none"
        autoCorrect={false}
        style={styles.loginInput}
        onChangeText={setUsername}
      />
      <TextInput
        placeholder="Assemble User ID"
        autoCapitalize="none"
        autoCorrect={false}
        style={styles.loginInput}
        onChangeText={setAssembleUserId}
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
        style={[styles.button, { opacity }]}
        activeOpacity={0.8}
        onPress={handleRegister}
        disabled={isLoading}
      >
        <Text style={styles.buttonText}>
          {isPending ? 'Loading...' : 'Confirm'}
        </Text>
      </TouchableOpacity>
    </KeyboardAvoidingView>
  );
}
