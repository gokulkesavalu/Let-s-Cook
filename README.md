# Let's Cook 🍳

A modern Android application for cooking enthusiasts, built with the latest Android technologies and a modular, clean architecture.

## 🚀 Overview

Let's Cook is designed to showcase modern Android development practices, including a multi-module project structure, Jetpack Compose, and robust offline-first capabilities. It provides an intuitive platform for exploring global meal categories, regional cuisines, and diverse ingredients.

## 🏗 Project Structure

The project follows a **Modular Architecture** to ensure scalability, maintainability, and strict separation of concerns.

- **:app**: The main entry point. Orchestrates top-level navigation, dependency injection configuration, and theme initialization.
- **:feature**: Contains isolated, feature-specific modules following the MVVM pattern.
    - **:feature:home**: The main landing screen. Features categorized lists, regional cuisines, and ingredients loaded in parallel.
    - **:feature:meals**: Displays a list of recipes filtered by category, area, or ingredient.
    - **:feature:mealdetails**: Provides a comprehensive view of a specific recipe, including instructions and measurements.
- **:core**: Shared modules providing foundational logic and UI components.
    - **:core:common**: Low-level utilities and pure Kotlin logic (e.g., Flag Emoji mapping utilities).
    - **:core:ui**: Shared UI components (like `AreaItem` and `CardItem`), standard Material 3 themes, typography, and Coil 3 image loading configuration.
    - **:core:navigation**: Centralized, type-safe navigation routes using Kotlin Serialization to eliminate string-based routing.
    - **:core:network**: Retrofit-based networking layer with global DTO definitions and HTTP logging.
    - **:core:database**: Local persistence layer using Room, ensuring the app remains fully functional offline.

## 🛠 Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/) (2.2.10)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) (3.x) for optimized, lifecycle-aware image fetching.
- **Navigation**: [Jetpack Navigation](https://developer.android.com/guide/navigation) (Type-safe with Kotlin Serialization)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) (2.59.2)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & OkHttp (Kotlin Serialization for JSON)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Asynchronous Flow**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & Flow
- **Architecture**: Clean Architecture / MVVM / Repository Pattern

## 📐 Architecture Overview

The application is built on **Clean Architecture** principles to ensure business logic remains independent of the UI and external data sources.

1. **UI Layer**: Jetpack Compose screens and ViewModels. Uses `StateFlow` and `collectAsStateWithLifecycle()` for reactive, lifecycle-aware UI updates.
2. **Domain Layer**: Pure Kotlin layer containing domain models and repository interfaces.
3. **Data Layer**: Responsible for data retrieval and persistence. Includes repository implementations, data mappers (DTO ↔ Entity ↔ Domain), and local/remote data sources.

### 💾 Repository & Caching Strategy

The project implements an **Offline-First** approach. The `HomeRepositoryImpl` uses a **Cache-First** strategy:
* Requests are first checked against the local Room database.
* Data is considered valid for **15 minutes** (configurable).
* If the cache is stale or missing, fresh data is fetched from the network, persisted to the database, and emitted to the UI.

## ✨ Key Features & Technical Highlights

- **Parallel & Resilient Data Loading**: The Home screen fetches categories, areas, and ingredients simultaneously using `supervisorScope`. This ensures that a single failure (e.g., ingredients API down) doesn't block the entire screen from loading.
- **Type-Safe Navigation**: Completely removed "magic strings" from the navigation graph. All routes and arguments are defined as `@Serializable` data classes, providing compile-time safety.
- **Optimized UI (Lazy Loading)**: Implemented performance-optimized layouts using `LazyColumn` and `LazyRow` for smooth scrolling across large datasets, with progress indicators centered for better UX.
- **Granular Error Handling**: The UI state tracks loading and errors for each data type independently (Categories, Areas, Ingredients), allowing users to interact with successful sections even if one part fails.
- **Smart Data Mapping**: Rigorous mapping between network DTOs, database Entities, and Domain models ensures data integrity and prevents internal leakages into the UI layer.
- **Modern Design System & Branding**: 
    - Full Material 3 integration with Light/Dark modes and dynamic colors.
    - Custom stylized branding with a centered logo in the Home top bar.
    - Fully configured adaptive icons (Launcher & Play Store ready).
- **Database Integrity**: Refined Room entity naming and primary key configurations to resolve KSP conflicts and ensure schema stability.
- **Comprehensive Documentation**: Every class, function, and property in the project is documented using KDoc, following standard Kotlin practices for maximum maintainability.

## 🏁 Getting Started

1. Clone the repository.
2. Open in Android Studio (Ladybug 2024.2.1 or newer recommended).
3. Build the project: `./gradlew assembleDebug`
4. Run on an emulator or physical device.

---
Built with ❤️ for cooking and modern Android development.
