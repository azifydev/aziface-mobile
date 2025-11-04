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
  photoIDMatch(data?: UnsafeObject): Promise<UnsafeObject>;
  photoIDScanOnly(data?: UnsafeObject): Promise<UnsafeObject>;
  enroll(data?: UnsafeObject): Promise<UnsafeObject>;
  authenticate(data?: UnsafeObject): Promise<UnsafeObject>;
  liveness(data?: UnsafeObject): Promise<UnsafeObject>;
  setTheme(options?: UnsafeObject): void;
  vocal(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('AzifaceMobile');
