import { azifaceApi } from './azifaceApi';
import { useMutation, useQuery } from '@tanstack/react-query';
import uuid from 'react-native-uuid';
import { useUserStore } from '../hooks/useuser.hook';

interface AzifaceCommonError {
  code?: string;
  message?: string;
  status?: number;
}

interface AzifaceCommonResponse<T> {
  data: T;
  error: AzifaceCommonError;
}

interface ConfigsResponseData {
  device: string;
  productionKey: string;
  key: string;
}

export function useConfigs() {
  return useQuery({
    queryKey: ['configs'],
    queryFn: async () => {
      const response =
        await azifaceApi.get<AzifaceCommonResponse<ConfigsResponseData>>(
          '/Configs'
        );
      if (response.data.error) {
        throw new Error(response.data.error.message);
      }
      return response.data.data;
    },
  });
}

export function useCreateProcess() {
  return useMutation({
    mutationKey: ['create-process'],
    mutationFn: async () => {
      if (!useUserStore.getState().processId) {
        const id = uuid.v4();
        useUserStore.getState().setProcessId(id);
      }

      const response = await azifaceApi.post<AzifaceCommonResponse<string>>(
        `/Process/${useUserStore.getState().processId}`
      );
      if (response.data.error) {
        throw new Error(response.data.error.message);
      }
      return response.data.data;
    },
  });
}
