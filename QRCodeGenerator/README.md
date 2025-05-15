# 🎲 Super QR Code Generator

A modern QR code generator and scanner for Android, built entirely with Jetpack Compose and guided by the latest best practices.

## 🎥 Demo Video
Check out a quick walkthrough of the app in action:
| Quick Walkthrough |
|-------------------|
| <video src="https://github.com/user-attachments/assets/9f14c517-0c29-43b3-8380-fbbc10d0ff91" />  |

## 🏁 Getting up and running

#### Run Local Server

1. Ensure Node.js is installed.
2. Navigate to the `mobile-test/seed-mock-api` directory.
3. Start the server:
```
   node index.js
```

####  Run app

1. Download the latest version of Android Studio.
2. Open the `QRCodeGenerator` folder in Android Studio.
3. Run the project on an emulator (due to local mock server).

## 🚀 Features

- Built entirely with Kotlin and Jetpack Compose
- Generates QR code images using ZXing
- Scans QR codes using ML Kit
- Navigation implemented with Compose
- MVI architecture for presentation layer
- Clean architecture with domain and repository layers
- Dependency injection with Hilt

## 🌱 Code Quality and Testing

- Formatting and linting enforced with Spotless and ktlint, using custom Compose rules
- Unit tests written with MockK and Turbine following best practices
- Snapshot testing with Paparazzi


## 🥁 Extras
#### Run unit tests
Use the standard Gradle command to execute unit tests:
```
./gradlew :app:testDebugUnitTest
```

#### 🖼️ Snapshot Testing with Paparazzi

Record and verify UI snapshots:

**Record snapshots**
```
./gradlew :app:recordPaparazziDebug
```

**Verify snapshots**
```
./gradlew :app:verifyPaparazziDebug
```

#### 🧼 Auto-format with Spotless

Automatically fix code style issues and apply formatting:
```
./gradlew :app:spotlessApply
```

## 🛠️ Tech Stack & Libraries

### 🧱 Core Android & Compose
- 🔧 [AndroidX Core KTX](https://developer.android.com/kotlin/ktx): Kotlin extensions for Android core libraries
- 🧬 [Jetpack Compose](https://developer.android.com/jetpack/compose): Modern UI toolkit for building native UI in Kotlin
- 🎨 [Material 3](https://developer.android.com/jetpack/androidx/releases/compose-material3): Material Design components for Compose

### 🔄 Lifecycle & Architecture
- 🔁 [Lifecycle Runtime KTX](https://developer.android.com/jetpack/androidx/releases/lifecycle): Lifecycle-aware components for Compose and beyond

### 📷 Camera & ML
- 📸 [CameraX](https://developer.android.com/training/camerax): Jetpack library for camera functionality with lifecycle support
- 🔍 [ML Kit Barcode Scanning](https://developers.google.com/ml-kit/vision/barcode-scanning): ML Kit’s barcode scanning API
- 🧪 [ZXing](https://github.com/zxing/zxing): Barcode image processing library used for QR code generation

### ☁️ Networking
- 🔗 [Retrofit](https://square.github.io/retrofit/): Type-safe HTTP client for Android and Kotlin
- 🧬 [Moshi](https://github.com/square/moshi): JSON serialization for Kotlin
- 🕵️ [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor): Logs HTTP requests/responses for debugging

### 💉 Dependency Injection
- 💉 [Hilt](https://dagger.dev/hilt/): Dependency injection library built on top of Dagger
- 🧭 [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt-navigation): Integrate Hilt with Jetpack Compose navigation

### 🪵 Logging & Utilities
- 🌲 [Timber](https://github.com/JakeWharton/timber): Lightweight, extensible logging library for Android

### 🧪 Testing
- ✅ [JUnit](https://junit.org/junit4/): Unit testing framework for Java/Kotlin
- 🧬 [MockK](https://mockk.io/): Mocking library for Kotlin
- ⏱️ [Kotlinx Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines): Testing support for Kotlin coroutines
- 🌊 [Turbine](https://github.com/cashapp/turbine): A lightweight testing library for Kotlin flows
- 👁️ [Espresso](https://developer.android.com/training/testing/espresso): UI testing framework for Android
- 🧰 [AndroidX Test Core & JUnit](https://developer.android.com/testing): Core utilities for Android instrumentation tests


