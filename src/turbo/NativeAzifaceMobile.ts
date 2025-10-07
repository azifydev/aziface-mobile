import { TurboModuleRegistry, type TurboModule } from 'react-native';
import type {
  EventEmitter,
  UnsafeObject,
} from 'react-native/Libraries/Types/CodegenTypesNamespace';

export interface Spec extends TurboModule {
  readonly onInitialize: EventEmitter<boolean>;
  readonly onOpen: EventEmitter<boolean>;
  readonly onClose: EventEmitter<boolean>;
  readonly onCancel: EventEmitter<boolean>;
  readonly onError: EventEmitter<boolean>;
  readonly onVocal: EventEmitter<boolean>;

  initialize(params?: UnsafeObject, headers?: UnsafeObject): Promise<boolean>;
  photoIDMatch(data?: UnsafeObject): Promise<boolean>;
  photoIDScanOnly(data?: UnsafeObject): Promise<boolean>;
  enroll(data?: UnsafeObject): Promise<boolean>;
  authenticate(data?: UnsafeObject): Promise<boolean>;
  liveness(data?: UnsafeObject): Promise<boolean>;
  setTheme(options?: UnsafeObject): void;
  vocal(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('AzifaceMobile');
