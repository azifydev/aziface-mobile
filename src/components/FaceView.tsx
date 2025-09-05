import React, { memo, forwardRef, useEffect } from 'react';
import { View, NativeEventEmitter, type NativeModule } from 'react-native';
import type { FaceViewProps } from '../types';
import { AzifaceModule } from '../methods';

const FaceView = forwardRef<View, FaceViewProps>((props, ref) => {
  const {
    children,
    onCancel,
    onClose,
    onError,
    onOpen,
    onInitialize,
    ...rest
  } = props;

  useEffect(() => {
    const emitter = new NativeEventEmitter(
      AzifaceModule as unknown as NativeModule
    );

    emitter.addListener('onOpen', (event: boolean) => onOpen?.(event));
    emitter.addListener('onClose', (event: boolean) => onClose?.(event));
    emitter.addListener('onCancel', (event: boolean) => onCancel?.(event));
    emitter.addListener('onError', (event: boolean) => onError?.(event));
    emitter.addListener('onInitialize', (event: boolean) =>
      onInitialize?.(event)
    );

    return () => {
      emitter.removeAllListeners('onOpen');
      emitter.removeAllListeners('onClose');
      emitter.removeAllListeners('onCancel');
      emitter.removeAllListeners('onError');
      emitter.removeAllListeners('onInitialize');
    };
  }, [onCancel, onClose, onError, onOpen, onInitialize]);

  return (
    <View ref={ref} {...rest}>
      {children}
    </View>
  );
});

export default memo(FaceView);
