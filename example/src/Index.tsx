import { ScrollView } from 'react-native';

import { styles } from './Style';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { Routes } from './routes';

const queryClient = new QueryClient();

export default function Index() {
  return (
    <QueryClientProvider client={queryClient}>
      <ScrollView contentContainerStyle={styles.container}>
        <Routes />
      </ScrollView>
    </QueryClientProvider>
  );
}
