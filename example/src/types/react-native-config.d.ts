declare module 'react-native-config' {
  export interface NativeConfig {
    API_URL_AZTECH: string;
    API_CLIENT_API: string;
    X_API_KEY: string;
    DEVICE_KEY: string;
  }

  export const Config: NativeConfig;
  export default Config;
}
