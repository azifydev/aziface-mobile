# Contributing

Contributions are always welcome, no matter how large or small!

We want this community to be friendly and respectful to each other. Please follow it in all your interactions with the project. Before contributing, please read the [code of conduct](./CODE_OF_CONDUCT.md).

## Development workflow

To get started with the project, run `npm i` in the root directory to install the required dependencies for each package:

```sh
npm i
```

> While it's possible to use [`npm`](https://github.com/npm/cli), the tooling is built around [`npm`](https://docs.npmjs.com/), so you'll have an easier time if you use `npm` for development.

While developing, you can run the [example app](/example/) to test your changes. Any changes you make in your library's JavaScript code will be reflected in the example app without a rebuild. If you change any native code, then you'll need to rebuild the example app.

To run the example app on Android:

```sh
npm run android
```

To run the example app on iOS:

```sh
npm run ios
```

Make sure your code passes TypeScript. Run the following to verify:

```sh
npm run typecheck
```

To edit the Objective-C or Swift files, open `example/ios/AzifaceMobileExample.xcworkspace` in XCode and find the source files at `Pods > Development Pods > @azify/aziface-mobile`.

To edit the Java or Kotlin files, open `example/android` in Android studio and find the source files at `aziface-mobile-sdk` under `Android`.

### Commit message convention

We follow the [conventional commits specification](https://www.conventionalcommits.org/en) for our commit messages:

- `fix`: bug fixes, e.g. fix crash due to deprecated method.
- `feat`: new features, e.g. add new method to the module.
- `refactor`: code refactor, e.g. migrate from class components to hooks.
- `docs`: changes into documentation, e.g. add usage example for the module..
- `test`: adding or updating tests, e.g. add integration tests using detox.
- `chore`: tooling changes, e.g. change CI config.
- `perf`: use for performance adjustment.

Our pre-commit hooks verify that your commit message matches this format when committing.

### Linting and tests

[ESLint](https://eslint.org/), [Prettier](https://prettier.io/), [TypeScript](https://www.typescriptlang.org/)

We use [TypeScript](https://www.typescriptlang.org/) for type checking, [ESLint](https://eslint.org/) with [Prettier](https://prettier.io/) for linting and formatting the code, and [Jest](https://jestjs.io/) for testing.

Our pre-commit hooks verify that the linter and tests pass when committing.

### Scripts

The `package.json` file contains various scripts for common tasks:

- `npm run clean`: clean all project.
- `npm run prepack`: pre-build the library.
- `npm run release`: run [semantic-release](https://semantic-release.gitbook.io/semantic-release/) to release new version.
- `npm run setup`: setup project by installing all dependencies and pods.
- `npm run typecheck`: type-check files with TypeScript.
- `npm run android`: run the example app on Android.
- `npm run ios`: run the example app on iOS.

### Sending a pull request

> **Working on your first pull request?** You can learn how from this _free_ series: [How to Contribute to an Open Source Project on GitHub](https://app.egghead.io/playlists/how-to-contribute-to-an-open-source-project-on-github).

When you're sending a pull request:

- Prefer small pull requests focused on one change.
- Verify that linters and tests are passing.
- Review the documentation to make sure it looks good.
- Follow the pull request template when opening a pull request.
- For pull requests that change the API or implementation, discuss with maintainers first by opening an issue.
