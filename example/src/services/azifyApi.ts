import Config from 'react-native-config';

const baseURL = Config.API_URL_AZIFY;
import axios from 'axios';
import { getAzifyHeaders } from './headers';
import { useUserStore } from '../hooks/useuser.hook';

const headersDefault = getAzifyHeaders();

export const azifyApi = axios.create({
  baseURL: baseURL,
  headers: { ...headersDefault },
});
azifyApi.interceptors.request.use(
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

azifyApi.interceptors.response.use(
  (res) => res,
  (error) => {
    return Promise.reject(error);
  }
);
