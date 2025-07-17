import Config from 'react-native-config';
export const azifaceBaseURL = Config.API_URL_AZTECH;
import axios from 'axios';
import { useUserStore } from '../hooks/useuser.hook';

export const azifaceApi = axios.create({
  baseURL: azifaceBaseURL,
});
azifaceApi.interceptors.request.use(
  async (config) => {
    const token = useUserStore.getState()?.tokenBiometric || '';
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      config.headers['x-token-bearer'] = token;
    }
    config.validateStatus = (status) => {
      if (status === 401) {
        return false;
      }
      return true;
    };
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

azifaceApi.interceptors.response.use(
  (res) => res,
  (error) => {
    return Promise.reject(error);
  }
);
