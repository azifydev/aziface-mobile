import type { NativeStackNavigationProp } from '@react-navigation/native-stack';

export type RootStackParamList = {
  welcome: undefined;
  login: undefined;
  register: undefined;
  home: undefined;
};

export type ScreensProps = NativeStackNavigationProp<RootStackParamList>;
