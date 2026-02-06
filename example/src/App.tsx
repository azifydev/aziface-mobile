import { ScrollView, StatusBar } from 'react-native';

import { styles } from './styles';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { Routes } from './routes';

const queryClient = new QueryClient();

export default function Index() {
  return (
    <QueryClientProvider client={queryClient}>
      <StatusBar barStyle="dark-content" backgroundColor="white" />

      <ScrollView contentContainerStyle={styles.container}>
        <Routes />
      </ScrollView>
    </QueryClientProvider>
  );
}
