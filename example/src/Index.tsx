/* eslint-disable react/react-in-jsx-scope */
import { ScrollView } from 'react-native';

import { styles } from './Style';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import App from './App';

const queryClient = new QueryClient();

export default function Index() {
  return (
    <QueryClientProvider client={queryClient}>
      <ScrollView contentContainerStyle={styles.container}>
        <App />
      </ScrollView>
    </QueryClientProvider>
  );
}
