import { useUserStore } from '../hooks/useuser.hook';
import { assembleApi } from './assembleApi';
import { useMutation, useQuery } from '@tanstack/react-query';

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

export function useBiometricLogin() {
  return useMutation({
    mutationKey: ['login'],
    mutationFn: async (userId: string) => {
      const response = await assembleApi.post('/biometric/sessions', {
        userId,
      });
      if (response?.data?.token) {
        useUserStore.getState().setToken(response?.data?.token);
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
