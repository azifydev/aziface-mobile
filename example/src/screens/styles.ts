import { Dimensions, Platform, StyleSheet } from 'react-native';

const padding = 20;
const borderRadius = 15;
const gap = 32;
const fontSize = 16;
const height = Dimensions.get('window').height;

export const styles = StyleSheet.create({
  title: {
    fontSize: fontSize * 2,
    color: '#2f2f2f',
    fontWeight: 'bold',
  },
  button: {
    padding,
    borderRadius,
    width: '100%',
    backgroundColor: '#4a68b3',
    alignItems: 'center',
    justifyContent: 'center',
  },
  buttonText: {
    fontSize,
    color: 'white',
    fontWeight: 'bold',
  },
  loginContent: {
    gap,
    flex: 1,
    alignItems: 'center',
    paddingTop: height * 0.15,
    backgroundColor: 'white',
  },
  registerContent: {
    gap,
    flex: 1,
    flexGrow: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'white',
  },
  welcomeContent: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    gap: gap,
    backgroundColor: 'white',
  },
  loginInput: {
    borderRadius,
    padding,
    fontSize,
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
    minHeight: 60,
    backgroundColor: '#f0f0f0',
    color: 'black',
  },
  content: {
    gap,
    flexGrow: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'white',
  },
  buttonLogout: {
    padding,
    borderRadius,
    width: '100%',
    backgroundColor: '#e04561',
    alignItems: 'center',
    justifyContent: 'center',
  },
  textLogout: {
    color: 'white',
    fontWeight: 'bold',
  },
  scroll: {
    paddingTop: Platform.OS === 'ios' ? gap : 0,
    flexGrow: 1,
    backgroundColor: 'white',
  },
  scrollContent: {
    paddingVertical: 48,
    flexGrow: 1,
  },
});
