import { create } from 'zustand';
import { getString, storeString } from '../helpers/store';

interface UseUserProps {
  token: string;
  setToken: (token: string) => void;
}
export const useUserStore = create<UseUserProps>((set) => ({
  token: getString('token') || '',
  setToken: (token: string) => {
    set({ token });
    storeString('token', token);
  },
}));

export const useUser = useUserStore;
