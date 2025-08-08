import { MMKV } from 'react-native-mmkv';

const STORE = new MMKV();

export const errors = (error: unknown) => console.log(error);

export const storeString = (key: string, value: string) => {
  try {
    return STORE.set(key, value);
  } catch (e) {
    return errors(e);
  }
};

export const getString = (key: string) => {
  try {
    const result = STORE.getString(key);
    return result;
  } catch (e) {
    return errors(e);
  }
};

export const storeClearAll = () => {
  try {
    STORE.clearAll();
  } catch (e) {
    errors(e);
  }
};
