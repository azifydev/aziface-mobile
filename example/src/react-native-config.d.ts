declare module 'react-native-config' {
  export interface NativeConfig {
    API_URL_AZTECH: string;
    API_URL_AZIFY: string;
    X_CLIENT_ID: string;
    X_CLIENT_SECRET: string;
    API_URL_ASSEMBLE: string;
    X_API_KEY: string;
    ID: string;
  }

  export const Config: NativeConfig;
  export default Config;
}
