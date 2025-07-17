import { useUserStore } from '../hooks/useuser.hook';
import { azifyApi } from './azifyApi';
import { useMutation } from '@tanstack/react-query';

interface AzifyCommonError {
  code?: string;
  message?: string;
  status?: number;
}

interface AzifyCommonResponse<T> {
  data: T;
  error: AzifyCommonError;
}

interface LoginServiceRequest {
  username: string;
  password: string;
  uuidVerification?: string;
  token?: string;
}

interface LoginResponseData {
  token?: string;
  uuidVerification?: string;
  twoFa?: boolean;
}

export function useLogin() {
  return useMutation({
    mutationKey: ['login'],
    mutationFn: async (params: LoginServiceRequest) => {
      const response = await azifyApi.post<
        AzifyCommonResponse<LoginResponseData>
      >('/Users/Login', params);
      if (params.token && params.uuidVerification) {
        // console.log('params:', params);
        // console.log('response?.data:', response?.data);
      }
      if (response?.data?.data?.token) {
        useUserStore.getState().setToken(response?.data?.data?.token);
      }
      return response?.data?.data;
    },
  });
}

export interface DevResponseData {
  verifications: {
    id: number;
    partner: number;
    uuid: string;
    type: string;
    value: string;
    locale: string;
    status: string;
    attempts: number;
    maxAttempts: number;
    token: string;
    created_at: string;
    updated_at: string;
    deleted_at: string;
  }[];
}
export function useDev() {
  return useMutation({
    mutationKey: ['dev'],
    mutationFn: async () => {
      const response =
        await azifyApi.get<AzifyCommonResponse<DevResponseData>>('/Dev');
      if (response.data.error) {
        throw new Error(response.data.error.message);
      }
      return response.data.data;
    },
  });
}

interface CreateBiometricIdResponseData {
  id: string;
}

export function useCreateBiometricId() {
  return useMutation({
    mutationKey: ['create-biometric-id'],
    mutationFn: async () => {
      const response = await azifyApi.post<
        AzifyCommonResponse<CreateBiometricIdResponseData>
      >('/BiometricAuth', {
        action: 'SET_BIOMETRICS',
      });
      // console.log('response useCreateBiometricId', response);
      if (response.data.error) {
        throw new Error(response.data.error.message);
      }
      return response.data.data;
    },
  });
}

interface CreateBiometricSessionResponseData {
  token: string;
}
export function useCreateBiometricSession() {
  return useMutation({
    mutationKey: ['create-biometric-session'],
    mutationFn: async (biometricId: string) => {
      const response = await azifyApi.post<
        AzifyCommonResponse<CreateBiometricSessionResponseData>
      >(`/BiometricAuth/${biometricId}/Session`);
      // console.log('useCreateBiometricSession', response);

      if (response?.data?.error) {
        throw new Error(response.data.error.message);
      }
      if (response?.data?.data?.token) {
        useUserStore.getState().setTokenBiometric(response.data.data.token);
      }
      return response?.data?.data?.token;
    },
  });
}
