import {
  getDeviceId,
  getIpAddressSync,
  getSystemName,
} from 'react-native-device-info';
import { getUserAgent } from 'react-native-user-agent';
import Config from 'react-native-config';

import * as pkg from '../../package.json';

export function getAzifyHeaders() {
  const { name, version } = pkg;
  const clientInfo = `${getSystemName?.()},${version}`;
  const userAgent = getUserAgent?.();
  const deviceInfo = getIpAddressSync?.();
  const deviceId = getDeviceId?.();

  const result = {
    'Content-Type': 'application/json',
    'User-Agent': `${clientInfo}/${name}-mobile`,
    'x-forwarded-for': deviceInfo,
    'x-deviceid': deviceId,
    'x-user-agent': userAgent,
    'x-locale': 'pt-BR',
    'x-client-id': Config.X_CLIENT_ID,
  };
  return result;
}
