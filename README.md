# Let's Cook 🍳

A modern Android application for cooking enthusiasts, built with the latest Android technologies and
a modular, clean architecture.

## 🚀 Overview

Let's Cook is designed to showcase modern Android development practices, including multi-module
project structure, Jetpack Compose and offline-first capabilities. It provides a robust platform
for exploring meal categories, cuisines and ingredients from around the world.

## 🏗 Project Structure

The project follows a **Modular Architecture** to ensure scalability, maintainability, and clear
separation of concerns.

- **:app**: The main application entry point. Orchestrates top-level navigation, dependency
  injection configuration and theme initialization.
- **:feature**: Contains isolated, feature-specific modules following the MVVM pattern.
    - **:feature:home**: The main landing screen. Features categorized lists, cuisine areas and
      ingredients.
    - **:feature:meals**: Displays a list of recipes filtered by category, area, or ingredient.
    - **:feature:mealdetails**: Provides a comprehensive view of a specific recipe, including
      instructions and measurements.
- **:core**: Shared modules providing foundational logic and UI components.
    - **:core:common**: Low-level utilities and pure Kotlin logic (e.g., Flag Emoji mapping
      utilities).
    - **:core:ui**: Reusable UI components (like `AreaItem`), standard Material 3 themes, and
      typography.
    - **:core:navigation**: Centralized, type-safe navigation routes using Kotlin Serialization to
      eliminate string-based routing.
    - **:core:network**: Retrofit-based networking layer with global DTO definitions and logging.
    - **:core:database**: Local persistence layer using Room, ensuring the app remains functional
      offline.

## 🛠 Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/) (2.2.10)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3
- **Navigation**: [Jetpack Navigation](https://developer.android.com/guide/navigation) (Type-safe
  with Kotlin Serialization)
- **Dependency Injection
  **: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) (2.59.2)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & OkHttp (Kotlin Serialization for
  JSON)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Asynchronous Flow**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) &
  Flow
- **Architecture**: Clean Architecture / MVVM / Repository Pattern

## 📐 Architecture Overview

The application is built on **Clean Architecture** principles to ensure the business logic is
independent of the UI and external data sources.

1. **UI Layer**: Jetpack Compose screens and ViewModels. Uses `StateFlow` and
   `collectAsStateWithLifecycle()` for reactive UI updates.
2. **Domain Layer**: Pure Kotlin layer containing domain models and repository interfaces.
3. **Data Layer**: Responsible for data retrieval and persistence. Includes repository
   implementations, data mappers (DTO ↔ Entity ↔ Domain), and local/remote data sources.

### 💾 Repository & Caching Strategy

The project implements an **Offline-First** approach. For example, `HomeRepositoryImpl` uses a *
*Cache-First** strategy:

* Requests are first checked against the local Room database.
* Data is considered valid for **15 minutes** (configurable).
* If the cache is stale or missing, fresh data is fetched from the network, persisted to the
  database, and emitted to the UI.

## ✨ Key Features & Refinements

- **Parallel & Resilient Data Loading**: The Home screen fetches categories, areas, and ingredients
  simultaneously using `supervisorScope`. This ensures that a single failure (e.g., ingredients API
  down) doesn't block the entire screen from loading.
- **Type-Safe Navigation**: Completely removed "magic strings" from the navigation graph. All routes
  and arguments are defined as `@Serializable` data classes.
- **Granular Loading & Error States**: The UI state tracks loading and errors for each data type
  independently, allowing for a more responsive and informative user experience.
- **Smart Data Mapping**: Rigorous mapping between network DTOs, database Entities, and Domain
  models ensures data integrity across layers.
- **Modern Design System**: Full Material 3 integration with support for Light/Dark modes, dynamic
  colors (Android 12+), and adaptive icons.
- **Comprehensive Documentation**: Every class, function, and property in the project is documented
  using KDoc, following standard Kotlin practices.

## 🏁 Getting Started

1. Clone the repository.
2. Open in Android Studio (Ladybug 2024.2.1 or newer recommended).
3. Build the project: `./gradlew assembleDebug`
4. Run on an emulator or physical device.

---
Built with ❤️ for cooking and modern Android development.
