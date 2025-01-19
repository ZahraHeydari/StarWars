About:
========
### App Flow
- Start Screen: User is on the search page. They can search for a character. Results are displayed as a list.
- Character Details: Tapping on a character opens a detailed view with all the required information.
- Error Handling: If the character is not found or if there’s an error, show a message on the screen.

### Project Structure

Use Clean Architecture and MVVM (Model-View-ViewModel) to ensure scalability and separation of concerns.

### Implementation Details

#### Network (API Integration)

- Use Retrofit for API interaction.
- Use Gson for JSON parsing.

#### Dependency Injection

Use Hilt for dependency injection. Define separate modules for ViewModel, Repository, API, and other dependencies.

#### Asynchronous Operations

- Use Coroutines for handling API calls and background operations.
- Implement error handling (e.g., showing error messages on the UI).

#### Navigation

Use Jetpack Compose Nav for navigating between the search and detail pages.

#### Modern Architecture

- Use Use Cases to encapsulate business logic.
- Repositories should abstract data sources (e.g., remote API).
- Expose UI state via StateFlow.

#### Scalability Considerations:
As new features are added (like filters, sorting, or more data points), the architecture will scale.
The ViewModel, repository, and use case layers allow for easy changes.
The app could later support pagination for the character list if needed 
(through the use of pagination components in Jetpack).