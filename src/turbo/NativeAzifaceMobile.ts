import {
  TurboModuleRegistry,
  type TurboModule,
  type CodegenTypes,
} from 'react-native';

export interface Spec extends TurboModule {
  readonly onInitialize: CodegenTypes.EventEmitter<boolean>;
  readonly onOpen: CodegenTypes.EventEmitter<boolean>;
  readonly onClose: CodegenTypes.EventEmitter<boolean>;
  readonly onCancel: CodegenTypes.EventEmitter<boolean>;
  readonly onError: CodegenTypes.EventEmitter<boolean>;
  readonly onVocal: CodegenTypes.EventEmitter<boolean>;

  initialize(
    params?: CodegenTypes.UnsafeObject,
    headers?: CodegenTypes.UnsafeObject
  ): Promise<boolean>;
  photoIDMatch(data?: CodegenTypes.UnsafeObject): Promise<boolean>;
  photoIDScanOnly(data?: CodegenTypes.UnsafeObject): Promise<boolean>;
  enroll(data?: CodegenTypes.UnsafeObject): Promise<boolean>;
  authenticate(data?: CodegenTypes.UnsafeObject): Promise<boolean>;
  liveness(data?: CodegenTypes.UnsafeObject): Promise<boolean>;
  setTheme(options?: CodegenTypes.UnsafeObject): void;
  vocal(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('AzifaceMobile');
