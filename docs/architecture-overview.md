# Architecture Overview

Our application adheres to the principles of Clean Architecture, coupled with the Model-View-ViewModel (MVVM) pattern, to ensure that our codebase is scalable, maintainable, and testable. This document outlines the high-level architecture of our project, detailing how different components interact within our codebase.

## Key Components

- **UI Layer (Presentation Layer)**: Our UI is built using Jetpack Compose, Google's modern toolkit for building native UIs. This layer is responsible for presenting information to the user and capturing user inputs. The UI components communicate with the ViewModel to trigger actions or to subscribe to data changes.

- **ViewModel**: Located within the Presentation Layer, ViewModels act as the bridge between the UI and the business logic of the application. They manage UI-related data and handle UI actions by making calls to Use Cases. Dependency injection is utilized here to provide the ViewModel with the necessary Use Cases.

- **Use Cases**: Use Cases encapsulate specific business logic tasks. They are called from the ViewModel and, in turn, make calls to the appropriate Repositories. This separation ensures that the business logic is decoupled from UI logic.

- **Repositories**: Repositories abstract the data layer, providing a clean API for the use cases to retrieve data. They decide whether to fetch data from a local DataSource or a remote DataSource, abstracting the origin of the data from the rest of the application.

- **Data Layer**: This layer includes implementations of Repositories and potentially DataSources. It's where the application interacts with external services or databases. The Data Layer is strictly Android-specific, ensuring that our domain logic remains platform-agnostic.

- **Dependency Injection (DI) Layer**: We leverage Dependency Injection to manage the creation and lifetimes of our objects, especially for ViewModels, Use Cases, and Repositories. This approach decouples the instantiation of dependencies from the classes using them, facilitating easier testing and maintenance.

## Layered Architecture

1. **Presentation Layer (UI and ViewModels)**: This is the layer closest to what the user interacts with. It contains our screens built with Jetpack Compose and the ViewModels that manage the state of these screens.

2. **Domain Module**: It houses our Use Cases and Models. The domain layer sits at the core of our application, orchestrating the flow of data between the UI and the data sources. It is designed to be entirely independent of the Android framework.

3. **Data Layer**: This layer implements the interfaces defined in the domain layer and is responsible for managing application data. It communicates with the network, database, or other data sources to fetch or save data.

4. **Dependency Injection (DI) Layer**: This layer is responsible for wiring up the various components of our application, providing them with their dependencies.

## Flow of Control

1. The user interacts with the UI, triggering events in the corresponding ViewModel.
2. The ViewModel executes Use Cases to perform specific business logic operations.
3. Use Cases interact with Repositories to retrieve or persist data.
4. Repositories manage data sources, deciding whether to fetch data from local storage or remote servers.

By adhering to Clean Architecture and the MVVM pattern, our application is structured in a way that separates concerns, promotes testability, and allows for scalable development. This architecture ensures that our UI is decoupled from the core logic of the application, facilitating easier updates and maintenance.