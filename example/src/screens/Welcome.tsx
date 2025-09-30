import { View, Text, TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { styles } from './styles';
import type { ScreensProps } from '../types';

export function Welcome() {
  const navigate = useNavigation<ScreensProps>();
  const goToRegister = () => navigate.navigate('register');
  const goToLogin = () => navigate.navigate('login');
  return (
    <View style={styles.welcomeContent}>
      <TouchableOpacity
        style={styles.button}
        activeOpacity={0.8}
        onPress={goToRegister}
      >
        <Text style={styles.buttonText}>Register</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        activeOpacity={0.8}
        onPress={goToLogin}
      >
        <Text style={styles.buttonText}>Login</Text>
      </TouchableOpacity>
    </View>
  );
}
