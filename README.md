# Let's Cook 🍳

A modern Android application for cooking enthusiasts, built with the latest Android technologies and a modular, clean architecture.

## 🚀 Overview

Let's Cook is designed to showcase modern Android development practices, including a multi-module project structure, Jetpack Compose, and robust offline-first capabilities. It provides an intuitive platform for exploring global meal categories, regional cuisines, and diverse ingredients.

## 🏗 Project Structure

The project follows a **Modular Architecture** to ensure scalability, maintainability, and strict separation of concerns.

- **:app**: The main application entry point. Orchestrates top-level navigation, dependency injection configuration, and theme initialization.
- **:feature**: Contains isolated, feature-specific modules following the MVVM pattern.
    - **:feature:home**: The main landing screen. Features categorized lists, regional cuisines, and ingredients loaded in parallel.
    - **:feature:meals**: Displays a list of recipes filtered by category, area, or ingredient.
    - **:feature:mealdetails**: Provides a comprehensive view of a specific recipe, including instructions and measurements.
- **:core**: Shared modules providing foundational logic and UI components.
    - **:core:common**: Low-level utilities and pure Kotlin logic (e.g., Flag Emoji mapping utilities).
    - **:core:ui**: Reusable UI components (like `CardItem` and `CircleCardItem`), standard Material 3 themes, typography, and Coil 3 image loading configuration.
    - **:core:navigation**: Centralized, type-safe navigation routes using Kotlin Serialization to eliminate string-based routing.
    - **:core:network**: Retrofit-based networking layer with global DTO definitions and HTTP logging.
    - **:core:database**: Local persistence layer using Room, ensuring the app remains fully functional offline. It features a robust caching system for categories, areas, ingredients, and detailed meal data.

## 🛠 Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/) (2.2.10)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) (3.x) for optimized, lifecycle-aware image fetching.
- **Navigation**: [Jetpack Navigation](https://developer.android.com/guide/navigation) (Type-safe with Kotlin Serialization)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) (2.59.2)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & OkHttp (Kotlin Serialization for JSON)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room) (v2 schema with destructive migration enabled for development)
- **Asynchronous Flow**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & Flow
- **Annotation Processing**: [KSP](https://kotlinlang.org/docs/ksp-overview.html) for fast and efficient code generation.
- **Architecture**: Clean Architecture / MVVM / Repository Pattern

## 📐 Architecture Overview

The application is built on **Clean Architecture** principles to ensure business logic remains independent of the UI and external data sources.

1. **UI Layer**: Jetpack Compose screens and ViewModels. Uses `StateFlow` and `collectAsStateWithLifecycle()` for reactive, lifecycle-aware UI updates.
2. **Domain Layer**: Pure Kotlin layer containing domain models and repository interfaces.
3. **Data Layer**: Responsible for data retrieval and persistence. Includes repository implementations, data mappers (DTO ↔ Entity ↔ Domain), and local/remote data sources.

### 💾 Repository & Caching Strategy

The project implements an **Offline-First** approach with a robust **Cache-First** strategy:
* **SSOT (Single Source of Truth)**: Data is always served from the local Room database if the cache is valid.
* **Cache Validation**: Data is considered valid for **15 minutes**.
* **Generic Data Fetching & Enrichment**: Implemented a refactored, generic `fetchData` helper in the repositories to handle the complex flow of checking cache, fetching from network, and "enriching" the data with missing filter metadata (like categories/areas) before persisting to the database. This ensures high code reuse and a searchable offline cache.

## ✨ Key Features & Technical Highlights

- **Parallel & Resilient Data Loading**: The Home screen fetches categories, areas, and ingredients simultaneously using `supervisorScope`. This ensures that a single failure (e.g., ingredients API down) doesn't block the entire screen from loading.
- **Deep Ingredient Search**: Implemented a comprehensive search query in `MealDao` that scans all 20 ingredient slots in the database, ensuring that recipes are correctly discovered regardless of where the search ingredient appears in the list.
- **Centralized Asynchrony**: Utilizes a single, Hilt-provided `CoroutineScope` with a `SupervisorJob` for persistent background tasks (like cache updates), preventing duplicate bindings and ensuring consistent lifecycle management across modules.
- **Type-Safe Navigation**: Completely removed "magic strings" from the navigation graph. All routes and arguments are defined as `@Serializable` data classes, providing compile-time safety and simplified argument passing.
- **Process Death Resilience**: ViewModels utilize `SavedStateHandle` combined with `Parcelable` domain models to persist UI state (like active filters) across process death, ensuring a seamless user experience even if the system reclaims resources.
- **Robust API Handling**: The `MealDto` is designed with default values and nullability to gracefully handle both partial "Filter API" responses and full "Detail API" responses without serialization errors.
- **Strict Documentation Standards**: The entire codebase follows rigorous KDoc standards, ensuring that all public APIs, data models, and complex internal logic are fully documented for maximum maintainability.
- **Optimized UI (Lazy Loading)**: Implemented performance-optimized layouts using `LazyColumn` for the root container and `LazyRow` for carousels, ensuring smooth scrolling and centered progress indicators.
- **Visual Components**:
    - **`CircleCardItem`**: Specialized circular component for displaying ingredients, cuisines, or categories with high-impact visuals.
    - **`CardItem`**: Versatile card component with Coil-powered image loading.
- **Performance Optimizations**: 
    - Used `@Immutable` on UI state classes to assist Compose compiler optimizations.
    - Utilized `collectAsStateWithLifecycle()` for lifecycle-aware state collection, reducing unnecessary resources when the app is in the background.
- **Granular Error Handling**: The UI state tracks loading and errors for each data type independently, allowing users to interact with successful sections even if one part fails.
- **Smart Data Mapping**: Rigorous mapping between network DTOs, database Entities, and Domain models ensures data integrity.
- **Modern Design System & Branding**: 
    - Full Material 3 integration with Light/Dark modes.
    - Custom branding with a centered logo in the top bar.
    - Fully configured adaptive icons.

## 🛠 Best Practices Followed

- **SOLID Principles**: Focused on single responsibility and interface segregation (especially in Repository and Service layers).
- **DRY (Don't Repeat Yourself)**: Leveraged generic helper functions and shared core modules to minimize code duplication.
- **Fail-Fast & Resilience**: Used `supervisorScope` and comprehensive `Result` wrapping to handle failures gracefully without crashing the application.
- **Dependency Inversion**: High usage of Hilt for constructor injection, ensuring the project remains testable and decoupled.

## 🏁 Getting Started

1. Clone the repository.
2. Open in Android Studio (Ladybug 2024.2.1 or newer recommended).
3. Build the project: `./gradlew assembleDebug`
4. Run on an emulator or physical device.

---
Built with ❤️ for cooking and modern Android development.
