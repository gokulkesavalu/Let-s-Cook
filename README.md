# Let's Cook

A modern Android application for cooking enthusiasts, built with the latest Android technologies and a modular architecture.

## Project Structure

This project follows a modular architecture to ensure scalability and maintainability.

- **:app**: The main application module that orchestrates navigation and dependency injection.
- **:feature**: Contains feature-specific modules.
    - **:feature:home**: The home screen and related logic.
    - **:feature:meals**: Display and management of meal lists.
    - **:feature:mealdetails**: Detailed view for a specific meal.
- **:core**: Shared modules used across features.
    - **:core:ui**: Shared UI components, themes, and design system.
    - **:core:network**: Networking logic using Retrofit and OkHttp.
    - **:core:database**: Local data persistence using Room.

## Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/) (2.2.10)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3
- **Dependency Management**: Gradle Version Catalog (`libs.versions.toml`)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) (2.59.2)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & OkHttp (Kotlin Serialization for JSON)
- **Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Architecture**: MVVM / Clean Architecture principles

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio (Ladybug or newer recommended).
3. Build the project: `./gradlew assembleDebug`
4. Run the app on an emulator or physical device.

## Recent Updates

- Migrated to **Android Gradle Plugin 9.2.1**.
- Updated to **Kotlin 2.2.10**.
- Updated to **Compose BOM 2026.05.01**.
- Integrated **Hilt** for dependency injection across `:app`, `:core:network`, and `:core:database`.
- Completed **Network Module** with `MealsService` and DTOs for categories and ingredients.
- Added comprehensive **KDoc** documentation to the networking layer.
- Refactored build scripts to remove unnecessary Proguard configurations and resolve classloader issues.
