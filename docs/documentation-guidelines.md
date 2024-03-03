# Documentation Guidelines for Our Project

## Introduction

In our project, we believe that good documentation is as crucial as the code itself. It enhances the readability, maintainability, and transferability of our code, making it easier for current and future team members to understand the project's structure, functionality, and rationale. This document outlines our approach to documenting both the code within our project and the broader documentation that resides in our `docs` folder.

## In-Code Documentation

### Commenting Code

  **Inline Comments**: Use inline comments sparingly to clarify complex pieces of logic or decisions that aren't immediately obvious from the code itself. Remember, the code should be as self-explanatory as possible; prefer refactoring to make the code clearer over adding comments.

### Documentation Format

- **KDoc for Kotlin**: Utilize KDoc to document classes, functions, parameters, and return values. KDoc comments start with `/**` and are placed above the declaration they're documenting.

  ```kotlin
  /**
   * Calculates the sum of two integers.
   *
   * @param a The first integer.
   * @param b The second integer.
   * @return The sum of `a` and `b`.
   */
  fun add(a: Int, b: Int): Int {
      return a + b
  }
  ```

## Structuring Documentation in the `docs` Folder

For broader documentation that doesn't fit within code comments, such as architectural overviews, testing guidelines, and coding conventions, we use the `docs` folder. This documentation should follow a structured format to ensure consistency and readability.

### Documentation Structure

1. **Introduction**: Start each document with an introduction that outlines the document's purpose and key points covered.

2. **Headings and Subheadings**: Use headings and subheadings to organize content logically. This structure helps readers quickly find the information they need.

3. **Code Samples**: Include code samples when explaining concepts or guidelines to provide clear, practical examples.

4. **Consistent Naming**: Name documentation files clearly and consistently, using lowercase letters and hyphens to separate words (e.g., `architecture-overview.md`, `coding-conventions.md`).

5. **UML Diagrams**: It's optional, but if you feel inclined, you are welcome to create UML diagrams for the feature you've implemented. Including Class Diagrams would be particularly beneficial.

### Reflecting Code Structure in Documentation

To help link the documentation to the codebase, consider organizing your `docs` folder to mirror the project's structure where applicable. For instance, if documenting specific modules or packages, you might create subdirectories within `docs` that correspond to these components, containing relevant markdown files.

## Conclusion

Documenting code and maintaining comprehensive documentation in our `docs` folder are practices that contribute significantly to our project's success. They not only aid in the current understanding and development of the project but also ensure that future contributors can easily comprehend and contribute to the work we've started. By following these guidelines, we foster a culture of clarity and collaboration, preparing our team members for successful careers in software development.