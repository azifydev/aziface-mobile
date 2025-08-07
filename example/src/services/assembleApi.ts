import Config from 'react-native-config';
export const assembleBaseURL = Config.API_URL_ASSEMBLE;
import axios from 'axios';
import { useUserStore } from '../hooks/useuser.hook';

export const assembleApi = axios.create({
  baseURL: assembleBaseURL,
  headers: {
    'Content-Type': 'application/json',
    'x-api-key': Config.X_API_KEY,
  },
});
assembleApi.interceptors.request.use(
  async (config) => {
    const token = useUserStore.getState()?.token || '';
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

assembleApi.interceptors.response.use(
  (res) => res,
  (error) => {
    return Promise.reject(error);
  }
);
