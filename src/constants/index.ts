import type { AzifaceSdkProps } from '..';

export const NATIVE_CONSTANTS = (
  data?: AzifaceSdkProps.MatchData
): Record<AzifaceSdkProps.MatchType, AzifaceSdkProps.MatchConfig> => ({
  authenticate: {
    key: 'authenticateMessage',
    hasExternalDatabaseRefID: true,
    parameters: data?.parameters || null,
    endpoint: data?.endpoint || '/match-3d-3d',
    successMessage: data?.successMessage || 'Authenticated',
    uploadMessageIos: data?.uploadMessageIos || 'Still Uploading...',
  },
  enroll: {
    key: 'enrollMessage',
    hasExternalDatabaseRefID: true,
    parameters: data?.parameters || null,
    endpoint: data?.endpoint || '/enrollment-3d',
    successMessage: data?.successMessage || 'Liveness\nConfirmed',
    uploadMessageIos: data?.uploadMessageIos || 'Still Uploading...',
  },
  liveness: {
    key: 'livenessMessage',
    hasExternalDatabaseRefID: false,
    parameters: data?.parameters || null,
    endpoint: data?.endpoint || '/liveness-3d',
    successMessage: data?.successMessage || 'Liveness\nConfirmed',
    uploadMessageIos: data?.uploadMessageIos || 'Still Uploading...',
  },
});