# Task Manager API

## Project Overview

This project is a Task Manager API built with Spring Boot. It provides RESTful endpoints for managing tasks, allowing users to create, read, update, and delete tasks. A key feature of this API is the ability to manage task dependencies, enabling users to create complex task hierarchies and workflows.

The API can be used for:
- Managing tasks in a to-do list or project management system
- Storing and retrieving task information
- Organizing and tracking work items
- Creating and managing dependencies between tasks

## Core Features

1. **Basic CRUD Operations**: Create, Read, Update, and Delete tasks.
2. **Task Dependencies**: 
   - Add dependencies between tasks
   - Remove dependencies
   - Retrieve dependencies for a specific task
   This feature allows for creating task hierarchies, where certain tasks must be completed before others can begin.

## Core Technologies

1. **Java 17**: The primary programming language used for the project.
2. **Spring Boot 3.2.0**: Provides the framework for creating stand-alone, production-grade Spring-based Applications.
3. **Spring Web**: Used for building the web layer and REST API.
4. **Spring Data JPA**: For object-relational mapping and database interactions.
5. **HSQLDB**: An in-memory database used for data storage during runtime.
6. **Lombok**: Used to reduce boilerplate code in Java classes.
7. **Spring Boot DevTools**: Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
8. **SpringDoc OpenAPI**: For generating OpenAPI 3 documentation of the REST API.

## Core Components

1. **TaskController**: Handles HTTP requests and defines API endpoints, including those for managing task dependencies.
2. **TaskService**: Contains business logic for task operations, including dependency management.
3. **TaskRepository**: Interfaces with the database for data persistence.
4. **Task Entity**: Represents the data model, including properties for managing dependencies between tasks.

## How to Build and Run

To build and run this project:

1. Ensure you have Java JDK 17 or higher installed.
2. Install Maven if not already present.
3. Clone the repository to your local machine.
4. Navigate to the project root directory.
5. Run `mvn clean install` to build the project.
6. Run `mvn spring-boot:run` to start the application.

The API should now be accessible at `http://localhost:8080` (assuming default Spring Boot settings).

You can access the OpenAPI documentation at `http://localhost:8080/swagger-ui.html` to explore and test the API endpoints.

## Database Configuration

The application uses an in-memory HSQLDB database with the following configuration:

- URL: jdbc:hsqldb:mem:testdb
- Username: sa
- Password: (empty)

The JPA settings are configured to automatically update the database schema and show SQL statements in the logs.

## API Endpoints

The TaskController implements the following REST endpoints:

- `GET /tasks`: Retrieve all tasks
- `GET /tasks/{id}`: Retrieve a specific task by its ID
- `POST /tasks`: Create a new task
- `PUT /tasks/{id}`: Update an existing task
- `DELETE /tasks/{id}`: Delete a task
- `POST /tasks/{id}/dependencies/{dependencyId}`: Add a dependency to a task
- `DELETE /tasks/{id}/dependencies/{dependencyId}`: Remove a dependency from a task
- `GET /tasks/{id}/dependencies`: Get all dependencies for a specific task

For more detailed information about these endpoints, including request/response formats and possible status codes, please refer to the OpenAPI documentation available at `http://localhost:8080/swagger-ui.html` when running the application.


## Development

This project uses Spring Boot DevTools, which provides features like automatic restart and live reload. These features are particularly useful during development.

## Testing

The project includes Spring Boot Test for writing and running tests. You can run the tests using the command `mvn test`.

## Additional Information

For more detailed information about the API endpoints and usage, please refer to the OpenAPI documentation available when running the application. This documentation will provide a comprehensive list of all available endpoints, including those for managing task dependencies.
