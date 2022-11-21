# TestAuth

Demo application to storing sensitive data securely.

Android demo application  which show how securely store sensitive data.

* Consist three screens SigIn,Signup and MainScreen.
* SignUp Screen: To register new user. 
* Main Screen: Show welcome message for new user and sign out button.
* Sign Screen: Registered user can sign in with username and password.
* Algorithm used  for encryption/decryption transformation algorithm: “AES/GCM/NoPadding”
* Android keystore used store keys securely.
* Secure data stored in room database(implementation in seperate branch '_db_room_implementation')
### Architecture:
Project is developed in kotlin programming  language.Architecture used in project is MVVM with coroutine Flow api .

### Libraries Used

- [Jetpack](https://developer.android.com/jetpack) - A suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Data Binding](https://developer.android.com/topic/libraries/data-binding)-The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- [Material Design support libraries](https://material.io/develop/android/docs/getting-started) - Modern UI designing library for modern apps.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.
- [Room](https://developer.android.com/training/data-storage/room) LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.


## Installation

* Cloning this repository and import the project in Android Studio by moving to File->new->import project
* After the gradle sync complete, connect a physical device or use Android emulator.
* Run the app by clicking on the play button on top bar, or by pressing ctrl+R buttons.

### Running The Tests

Follow the steps to get test case reports:
* Move to test packages in Android Studio (Java test packages)
* Select the package by right clicking, select more run option then select 'run Tests in package with coverage'
* Test results will be shown after the executions are finished.

# MIT License

Copyright 2021

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
