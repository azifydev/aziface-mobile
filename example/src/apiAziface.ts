import axios, { AxiosError } from 'axios';
import { Alert } from 'react-native';

export const FACETEC_URI = 'https://customerv2.capitual.com/v2/CapFace';
const apiAziface = axios.create({
  baseURL: 'https://customerv2.capitual.com/v2/CapFaceConfigs',
});

apiAziface.interceptors.response.use(
  (res) => res,
  async (error: AxiosError) => {
    const { message } = error;
    if (message === 'Network Error') {
      Alert.alert('Não conseguimos acesso ao servidor: Erro de rede');
    }
    return Promise.reject(error);
  }
);

apiAziface.interceptors.request.use((req) => req);

interface FacetecConfigResponse {
  device: string;
  productionKey: string;
  key: string;
}

export default async function getFacetecConfig() {
  const data = await apiAziface.get<FacetecConfigResponse>('');
  return data.data;
}
