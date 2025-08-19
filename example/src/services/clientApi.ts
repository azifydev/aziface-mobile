import Config from 'react-native-config';
export const clientBaseURL = Config.API_CLIENT_API;
import axios from 'axios';
import { useUserStore } from '../hooks/useuser.hook';

export const clientApi = axios.create({
  baseURL: clientBaseURL,
  headers: {
    'Content-Type': 'application/json',
    'x-api-key': Config.X_API_KEY,
  },
});
clientApi.interceptors.request.use(
  async (config) => {
    const token = useUserStore.getState()?.token || '';
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
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

clientApi.interceptors.response.use(
  (res) => res,
  (error) => {
    return Promise.reject(error);
  }
);
