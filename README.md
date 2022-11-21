# TestAuth

To develop a simple app that prioritizes storing sensitive data securely. Must consider latest software development practices and tools for developing a simple and low level design app.

Android demo application  which show how securely store sensitive data.

* Consist three screens SigIn,Signup and MainScreen.
* SignUp Screen: To register new user. 
* Main Screen: Show welcome message for new user and sign out button.
* Sign Screen: Registered user can sign in with username and password.
* Algorithm used  for encryption/decryption transformation algorithm: “AES/GCM/NoPadding”
* Android keystore used store keys securely.
### Architecture:
Project is developed in kotlin programming  language.Architecture used in project is MVVM with coroutine Flow api .

### Libraries Used

- [Jetpack](https://developer.android.com/jetpack) - A suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.
- [Hilt](https://insert-koin.io/) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Data Binding](https://developer.android.com/topic/libraries/data-binding)-The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- [Material Design support libraries](https://material.io/develop/android/docs/getting-started) - Modern UI designing library for modern apps.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.