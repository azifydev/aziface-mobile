import { TurboModuleRegistry, type TurboModule } from 'react-native';
import type {
  EventEmitter,
  UnsafeObject,
  WithDefault,
} from 'react-native/Libraries/Types/CodegenTypesNamespace';

export interface Spec extends TurboModule {
  readonly onInitialize: EventEmitter<boolean>;
  readonly onOpen: EventEmitter<boolean>;
  readonly onClose: EventEmitter<boolean>;
  readonly onCancel: EventEmitter<boolean>;
  readonly onError: EventEmitter<boolean>;
  readonly onVocal: EventEmitter<boolean>;

  initialize(params?: UnsafeObject, headers?: UnsafeObject): Promise<boolean>;
  photoIDMatch(data?: UnsafeObject): Promise<string>;
  photoIDScanOnly(data?: UnsafeObject): Promise<string>;
  enroll(data?: UnsafeObject): Promise<string>;
  authenticate(data?: UnsafeObject): Promise<string>;
  liveness(data?: UnsafeObject): Promise<string>;
  setLocale(locale?: WithDefault<string, 'default'>): void;
  setDynamicStrings(strings?: UnsafeObject): void;
  setTheme(options?: UnsafeObject): void;
  vocal(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('AzifaceMobile');
