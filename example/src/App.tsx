/* eslint-disable react/react-in-jsx-scope */
import { useUser } from './hooks/useuser.hook';
import Aziface from './Aziface';
import Login from './Login';

export default function App() {
  const { token } = useUser();
  return token ? <Aziface /> : <Login />;
}
