import { create } from 'zustand';
import { getBool, getString, storeBool, storeString } from '../helpers/store';

interface UseUserProps {
  token: string;
  tokenBiometric: string;
  processId: string;
  isInitialized: boolean;
  setToken: (token: string) => void;
  setTokenBiometric: (tokenBiometric: string) => void;
  setProcessId: (processId: string) => void;
  setIsInitialized: (isInitialized: boolean) => void;
}
export const useUserStore = create<UseUserProps>((set) => ({
  token: getString('token') || '',
  tokenBiometric: getString('tokenBiometric') || '',
  processId: getString('processId') || '',
  isInitialized: getBool('isInitialized') || false,
  setToken: (token: string) => {
    set({ token });
    storeString('token', token);
  },
  setTokenBiometric: (tokenBiometric: string) => {
    set({ tokenBiometric });
    storeString('tokenBiometric', tokenBiometric);
  },
  setProcessId: (processId: string) => {
    set({ processId });
    storeString('processId', processId);
  },
  setIsInitialized: (isInitialized: boolean) => {
    set({ isInitialized });
    storeBool('isInitialized', isInitialized);
  },
}));

export const useUser = useUserStore;
