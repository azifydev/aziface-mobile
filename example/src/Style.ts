import { Dimensions, StyleSheet } from 'react-native';

const padding = 20;
const borderRadius = 15;
const gap = 2 * padding;
const fontSize = 16;
const height = Dimensions.get('window').height;

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexGrow: 1,
    padding: padding,
    backgroundColor: '#fff',
  },
  title: {
    fontSize: fontSize * 2,
    color: '#2f2f2f',
    fontWeight: 'bold',
  },
  button: {
    width: '100%',
    backgroundColor: '#4a68b3',
    padding: padding,
    borderRadius: borderRadius,
    alignItems: 'center',
    justifyContent: 'center',
  },
  buttonText: {
    fontSize: fontSize,
    color: '#fff',
    fontWeight: 'bold',
  },
  loginContent: {
    flex: 1,
    alignItems: 'center',
    paddingTop: height * 0.15,
    gap: gap,
  },
  loginInput: {
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
    minHeight: 60,
    backgroundColor: '#f0f0f0',
    borderRadius: borderRadius,
    padding: padding,
    fontSize: fontSize,
  },
  azifaceContent: {
    flex: 1,
    alignItems: 'center',
    paddingTop: height * 0.15,
    gap: gap,
  },
});
