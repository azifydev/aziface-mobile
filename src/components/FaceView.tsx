import { memo, useEffect } from 'react';
import { View } from 'react-native';
import {
  onCancel as cancel,
  onClose as close,
  onError as error,
  onInitialize as initialize,
  onOpen as open,
  onVocal as vocal,
} from '../listeners';
import type { FaceViewProps } from '../@types';
import type { EventSubscription } from 'react-native';

function FaceView(props: FaceViewProps) {
  const {
    children,
    onCancel,
    onClose,
    onError,
    onOpen,
    onVocal,
    onInitialize,
    ...rest
  } = props;

  useEffect(() => {
    const subscriptions = [
      onInitialize && initialize(onInitialize),
      onOpen && open(onOpen),
      onClose && close(onClose),
      onCancel && cancel(onCancel),
      onError && error(onError),
      onVocal && vocal(onVocal),
    ].filter(Boolean) as EventSubscription[];

    return () => {
      subscriptions.forEach((subscription) => subscription.remove());
    };
  }, [onCancel, onClose, onError, onVocal, onOpen, onInitialize]);

  return <View {...rest}>{children}</View>;
}

export default memo(FaceView);
