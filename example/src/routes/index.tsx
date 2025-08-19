import { NavigationContainer } from '@react-navigation/native';
import { When, Unless } from 'react-if';
import { AppRoutes } from './app.route';
import { AuthRoutes } from './auth.route';
import { useUser } from '../hooks/useuser.hook';

export function Routes() {
  const { tokenBiometric, token } = useUser();
  const isAuth = !!token && !!tokenBiometric;
  return (
    <NavigationContainer>
      <When condition={isAuth}>
        <AppRoutes />
      </When>
      <Unless condition={isAuth}>
        <AuthRoutes />
      </Unless>
    </NavigationContainer>
  );
}
