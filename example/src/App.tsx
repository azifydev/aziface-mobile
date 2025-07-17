/* eslint-disable react/react-in-jsx-scope */
import { useUser } from './hooks/useuser.hook';
import Aziface from './Aziface';
import Login from './Login';

export default function App() {
  const { token, tokenBiometric } = useUser();
  // console.log('App token:', token);
  // console.log('App tokenBiometric:', tokenBiometric);
  return token && tokenBiometric ? <Aziface /> : <Login />;
}
