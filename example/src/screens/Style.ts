import { Dimensions, StyleSheet } from 'react-native';

const padding = 20;
const borderRadius = 15;
const gap = 2 * padding;
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
    color: '#fff',
    fontWeight: 'bold',
  },
  loginContent: {
    gap,
    flex: 1,
    alignItems: 'center',
    paddingTop: height * 0.15,
    backgroundColor: '#ffffffff',
  },
  registerContent: {
    gap,
    flex: 1,
    flexGrow: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#ffffffff',
  },
  welcomeContent: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    gap: gap,
    backgroundColor: '#ffffffff',
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
  },
  azifaceContent: {
    gap,
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#ffffffff',
  },
  buttonLogout: {
    padding,
    borderRadius,
    width: '100%',
    backgroundColor: '#e04561ff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  textLogout: {
    color: '#ffffffff',
    fontWeight: 'bold',
  },
  scroll: {
    flex: 1,
  },
  scrollContent: {
    flexGrow: 1,
  },
});
