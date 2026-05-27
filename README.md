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
    - **:core:common**: Low-level utilities and shared Kotlin-only logic (e.g., Flag Emoji mapping).
    - **:core:ui**: Shared UI components, themes, and design system (e.g., `AreaItem` card).
    - **:core:network**: Networking logic using Retrofit and OkHttp.
    - **:core:database**: Local data persistence using Room.

## Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/) (2.2.10)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3
- **Dependency Management**: Gradle Version Catalog (`libs.versions.toml`)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) (2.59.2)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & OkHttp (Kotlin Serialization for JSON)
- **Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Architecture**: MVVM / Clean Architecture principles (Repository Pattern)

## Architecture Overview

The application follows the **Clean Architecture** principles, separating concerns into three main layers:

1. **UI Layer**: Composed of Jetpack Compose screens and view models (to be implemented).
2. **Domain Layer**: Contains the business logic, domain models, and repository interfaces. This layer is independent of any other layer.
3. **Data Layer**: Includes repository implementations, data sources (Retrofit for API, Room for local DB), and data mappers.

### Repository Pattern

The project utilizes the **Repository Pattern** to abstract data sources from the domain layer. 

- **HomeRepository**: Orchestrates the fetching of categories, areas, and ingredients.
    - `HomeRepositoryImpl`: Fetches data from the `MealsService` (Remote) and will eventually handle local caching via `LetsCookDatabase`.

1. Clone the repository.
2. Open the project in Android Studio (Ladybug or newer recommended).
3. Build the project: `./gradlew assembleDebug`
4. Run the app on an emulator or physical device.

## Recent Updates

- Migrated to **Android Gradle Plugin 9.2.1**.
- Updated to **Kotlin 2.2.10**.
- Updated to **Compose BOM 2026.05.01**.
- Integrated **Hilt** for dependency injection across `:app`, `:core:network`, and `:core:database`.
- Completed **Network Module** with `MealsService` and DTOs for categories, ingredients, and areas (including `strCountry` metadata).
- **Database Layer Refinement**: Fixed KSP naming conflicts and ensured proper primary keys for Room entities (`CategoryEntity`, `IngredientEntity`, `AreaEntity`).
- **Comprehensive Documentation**: Added KDoc documentation across the entire project, covering all modules from core utilities to feature-level UI components and data mappers (`HomeMapper`).
- Created **:core:common** module for shared utilities like the `getFlagEmoji` mapping.
- Implemented **`AreaItem`** in `:core:ui`: A modern, card-styled component for displaying cuisines with large flag emojis, supporting both light and dark modes.
- Refactored build scripts to remove unnecessary Proguard configurations and resolve classloader issues.
