# Cupcake

A multi-screen cupcake ordering app: choose a quantity, a flavor and a pickup date, review the order summary and send it to another app.

Built while following Google's [Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course) course (the "Cupcake" codelab).

## Features

- Start screen to order one, six or twelve cupcakes
- Flavor and pickup-date selection screens, with the running subtotal
- Order summary screen that shares the order through an Android share sheet (`ACTION_SEND`)
- Cancel from any step to return to the start; Up button in the app bar

## Built with

- Kotlin, Jetpack Compose, Material 3
- Navigation Compose (`NavHost`, routes from an enum, `popBackStack`)
- `ViewModel` exposing `StateFlow<OrderUiState>`
- Instrumented UI tests for screen content and navigation (`TestNavHostController`)
- Min SDK 24, target/compile SDK 35

## Project structure

- `CupcakeScreen.kt`: app bar, `NavHost` and navigation wiring
- `ui/`: `StartOrderScreen`, `SelectOptionScreen`, `SummaryScreen`, `OrderViewModel`
- `data/`: `OrderUiState` and static `DataSource`

## Run it

1. Open the project folder in Android Studio and let Gradle sync.
2. Run the `app` configuration on an emulator or device (Android 7.0 / API 24 or newer).

Or, with a device or emulator connected:

```sh
./gradlew installDebug
```

Instrumented UI tests (needs a device or emulator): `./gradlew connectedAndroidTest`
