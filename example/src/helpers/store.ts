import { MMKV } from 'react-native-mmkv';

const STORE = new MMKV();

export const storeString = (key: string, value: string) => {
  try {
    return STORE.set(key, value);
  } catch (e) {
    return console.log(e);
  }
};

export const getString = (key: string) => {
  try {
    const result = STORE.getString(key);
    return result;
  } catch (e) {
    return console.log(e);
  }
};

export const storeClearAll = () => {
  try {
    STORE.clearAll();
  } catch (e) {
    console.log(e);
  }
};
