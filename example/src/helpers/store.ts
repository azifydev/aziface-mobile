/* eslint-disable no-console */
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

export const storeBool = (key: string, value: boolean) => {
  try {
    return STORE.set(key, value);
  } catch (e) {
    return errors(e);
  }
};

export const getBool = (key: string): boolean => {
  const result = STORE.getBoolean(key);
  return !!result;
};

export const storeObject = (key: string, value: object) =>
  STORE.set(key, JSON.stringify(value));

export const getObject = (key: string) => {
  const json = STORE.getString(key) || undefined;

  if (!json) return undefined;

  return JSON.parse(json);
};

export const storeClearAll = () => {
  try {
    STORE.clearAll();
  } catch (e) {
    errors(e);
  }
};
