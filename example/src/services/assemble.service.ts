import { useUserStore } from '../hooks/useuser.hook';
import { assembleApi } from './assembleApi';
import { useMutation, useQuery } from '@tanstack/react-query';

interface LoginRequestData {
  assembleUserId?: string;
  externalSecret?: string;
}

interface ConfigsResponseData {
  device: string;
  productionKey: string;
  key: string;
  error?: {
    message: string;
  };
}

export function useProcess() {
  return useMutation({
    mutationKey: ['process'],
    mutationFn: async (userId: string) => {
      const response = await assembleApi.post('/biometric/process', {
        userId,
      });
      return response?.data?.processId;
    },
  });
}

export function useOnboardingProcess() {
  return useMutation({
    mutationKey: ['biometric-process'],
    mutationFn: async (biometricProcess: string) => {
      const response = await assembleApi.post(
        '/onboardings/biometric-process',
        {
          biometricProcess,
          userType: 'INDIVIDUAL',
        }
      );
      return response?.data?.data;
    },
  });
}

export function useBiometricLogin() {
  return useMutation({
    mutationKey: ['login'],
    mutationFn: async (params: LoginRequestData) => {
      const response = await assembleApi.post('/auth/login', { ...params });
      if (response?.data?.accessToken) {
        useUserStore.getState().setToken(response?.data?.accessToken);
      }
      return response?.data?.data;
    },
  });
}

export function useBiometricConfigs() {
  return useQuery({
    queryKey: ['configs'],
    queryFn: async () => {
      const response =
        await assembleApi.get<ConfigsResponseData>('/biometric/configs');

      return response.data;
    },
  });
}
