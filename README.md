# P1 - Should I Go?

## Introduction

This is a Java-based command-line interface (CLI) eCommerce application. The application will be primarily built using Java and will utilize a PostgreSQL database to store product and user information.

## User Stories

- **As a user**, I want to register an account so that I can have a personalized experience.
- **As a user**, I want to have a list of my favorite places to go so the system can make suggestions of when I should go to them.
- **As a user**, I want to log in to my account so that I can access my search and suggestion history.
- **As a user**, I want to get a suggestion of whether I should go to a place or not based on weather, traffic, time of day, and other various preferences
- **As a user**, I want to be able to rate a suggestion so I can give feedback to system of whether or not the suggestion was useful

## MVP (Minimum Viable Product)

- User registration and login
- Search history
- Weather based suggestions
- Traffic based suggestions
- User interest based suggestions
- Search rating

## Stretch Goals

- Implementing a recommendation system based on user's previous searches
- Implementing promotional codes and discounts for places
- Adding a notification feature to notify users when the best time to go to places they'd like to go

## Tech Stacks

- **Java**: The main programming language used for building the application.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.
- **Google Maps** or **Bing Maps**: Used for traffic and popular times data
- **WeatherAPI** or **OpenWeatherMap**: Used for weather data

## Requirements

- **Clean Codebase**: All code should be clean and well-documented. The repository should not include any unnecessary files or folders such as the `target/`, `.DS_Store`, etc. All files and directories should be appropriately named and organized.

- **Database Design**: The database should be designed following the principles of the 3rd Normal Form (3NF) to ensure data integrity and efficiency. An Entity Relationship Diagram (ERD) should be included in the documentation.

- **Secure**: All sensitive user data such as passwords must be securely hashed before storing it in the database. The application should not display any sensitive information in error messages.

- **Error Handling**: The application should handle potential errors gracefully and provide clear and helpful error messages to the users.

- **Testing**: The application should have a high test coverage. Unit tests and integration tests should be implemented using JUnit, Mockito, and PowerMock.

- **Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting progress.

- **Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should be well-commented to allow for easy understanding and maintenance.

- **Scalable**: The design of the application should be scalable, allowing for easy addition of new features or modifications in the future.
