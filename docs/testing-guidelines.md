# Testing Guidelines for Our Project

## Introduction to Testing in Our Project

Welcome to the testing guidelines. Testing plays a crucial role in achieving this goal by ensuring code reliability and functionality. We aim to adopt a practical approach to testing, focusing mainly on unit testing to cover the core functionalities of our application.

## Understanding the Testing Pyramid

While our project does not strictly enforce every level of the Testing Pyramid, it's beneficial to have an understanding of its structure. The pyramid emphasizes the importance of having more low-level unit tests than high-level UI tests, suggesting an efficient method to allocate testing resources. For our purposes, focusing on unit tests will be our primary strategy, with a brief touch on integration and UI testing as needed.

## Unit Testing Guidelines

### Libraries and Tools

We will primarily use JUnit, Mockk, and Robolectric for our unit testing:

- **JUnit** is our foundational testing framework, allowing us to define test cases and various assertions to verify our code's correctness.
- **Mockk** is used for mocking dependencies, enabling us to isolate the unit of code we are testing.
- **Robolectric** allows us to unit test Android-specific code without the need for an actual device or emulator.

### Writing Unit Tests

When writing unit tests, consider the following structure and naming conventions:

1. **Test Naming**: We adopt two naming strategies for our tests:
    - Human-readable: `fun When_Condition_Expectation()`
    - Camel case with underscores: `fun methodName_useCase_expectedBehavior()`

2. **Test Structure**: Each test should follow the Arrange-Act-Assert (AAA) pattern:
    - **Arrange**: Set up the conditions for the test.
    - **Act**: Perform the action that you're testing.
    - **Assert**: Verify the result.

### Example of a Unit Test

```kotlin
// Pseudocode!
@Test
fun loadTasks_emptyTaskList_returnsEmpty() {
    // Arrange
    val tasksRepository = mock(TasksRepository::class.java)
    `when`(tasksRepository.getTasks()).thenReturn(emptyList())
    val tasksViewModel = TasksViewModel(tasksRepository)

    // Act
    tasksViewModel.loadTasks()

    // Assert
    assertTrue(tasksViewModel.tasks.isEmpty())
}
```

### Integration Testing

While our focus is on unit tests, integration testing can be valuable for testing the interactions between integrated components or systems. For our project, simple integration tests can be introduced as needed.

### UI Testing

UI testing is less of a priority but can be explored to ensure the application's user interface works as expected. Utilizing tools like Espresso for testing Android UI components can be beneficial for critical user flows.

### Test Coverage

Aim for a pragmatic test coverage that balances project learning goals with efficiency. For our project, targeting around 70-80% coverage for critical paths can be a reasonable goal. Android Studio provides built-in tools like Code Coverage to help assess test coverage.

### Mocking and Test Doubles

Utilize Mockk to create mock objects and test doubles, allowing you to simulate the behavior of complex objects in a controlled way. This is particularly useful for isolating the unit of work during testing.

### Writing Testable Code

Encourage practices that enhance code testability:
- Use dependency injection to decouple classes and interfaces.
- Favor small, single-responsibility methods.

### Test Review Process

All new features should be accompanied by corresponding unit tests, if applicable. During code reviews:
- Ensure tests are present and cover key functionality.
- Review tests for clarity, correctness, and adherence to our guidelines.