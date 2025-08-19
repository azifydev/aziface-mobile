import { useUserStore } from '../hooks/useuser.hook';
import { clientApi } from './clientApi';
import { useMutation, useQuery } from '@tanstack/react-query';

interface ConfigsResponseData {
  device: string;
  productionKey: string;
  key: string;
  error?: {
    message: string;
  };
}
interface CreateUserRequest {
  name: string;
  assembleUserId: string;
  username: string;
  password: string;
}
interface LoginRequest {
  username: string;
  password: string;
}
interface LoginResponse {
  accessToken: string;
  tokenType: string;
  expiresIn: number;
  user: {
    id: string;
    name: string;
  };
}
export function useCreate() {
  return useMutation({
    mutationKey: ['create-user'],
    mutationFn: async (params: CreateUserRequest) => {
      const response = await clientApi.post('/users', {
        ...params,
      });
      return response?.data;
    },
  });
}
export function useLogin() {
  return useMutation({
    mutationKey: ['login'],
    mutationFn: async (params: LoginRequest) => {
      const response = await clientApi.post('/auth/login', {
        ...params,
      });
      if (response?.data?.accessToken) {
        useUserStore.getState().setToken(response?.data?.accessToken);
      }
      return response?.data as LoginResponse;
    },
  });
}

export function useCreateBiometric() {
  return useMutation({
    mutationKey: ['biometric-auth'],
    mutationFn: async () => {
      const response = await clientApi.post('/biometrics/biometric-auth');
      if (response.data.error) throw new Error(response.data.message);
      if (response?.data?.processId) {
        useUserStore.getState().setProcessId(response?.data?.processId);
      }
      return response?.data?.processId;
    },
  });
}

export function useBiometricSession() {
  return useMutation({
    mutationKey: ['biometric-session'],
    mutationFn: async () => {
      const response = await clientApi.post(
        '/biometrics/biometric-auth/session'
      );
      if (response.data.error) throw new Error(response.data.message);
      if (response?.data?.token) {
        useUserStore.getState().setTokenBiometric(response?.data?.token);
      }
      console.log('useBiometricSession Response:', response);
      return response?.data?.token;
    },
  });
}

export function useBiometricConfigs() {
  return useQuery({
    queryKey: ['configs'],
    queryFn: async () => {
      const response = await clientApi.get<ConfigsResponseData>(
        '/biometrics/configs'
      );

      return response.data;
    },
  });
}
