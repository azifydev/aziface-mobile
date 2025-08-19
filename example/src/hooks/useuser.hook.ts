import { create } from 'zustand';
import { getString, storeString, storeClearAll } from '../helpers/store';

interface UseUserProps {
  token: string;
  tokenBiometric: string;
  processId?: string;
  setToken: (token: string) => void;
  setTokenBiometric: (tokenBiometric: string) => void;
  setProcessId: (processId: string) => void;
  logout: () => void;
}
export const useUserStore = create<UseUserProps>((set) => ({
  token: getString('token') || '',
  tokenBiometric: getString('tokenBiometric') || '',
  processId: getString('processId') || '',
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
  logout: () => {
    storeClearAll();
    set({ token: '', tokenBiometric: '', processId: '' });
  },
}));

export const useUser = useUserStore;
