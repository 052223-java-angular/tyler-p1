# P1 - MarsTown: A Restaurant Application

## Introduction

Welcome to MarsTown™! Order your favorite MarsGrass™-fed beef and steak burgers now! Also, be sure to try our MarsFruit™-infused shakes! When donating to one of our causes, keep an eye out for the MarsCandy™, which has unique effects on Earthlings!

This is a web application which will allow you to make orders of our delicious food! The application with primarily built using Java and Angular and will utilize a PostgreSQL database to store user preferences, point information, and payment information.

## User Stories

- **As a user**, I want to register an account so that I can have a personalized ordering experience.
- **As a user**, I want to be able to add a profile picture to my account.
- **As a user**, I want to log in to my account so that I can access my current order and see my order history.
- **As a user**, I want to browse through items.
- **As a user**, I want to add items to my current order, only when logging in.
- **As a user**, I want to modify the quantity or remove items from my current order so I can make changes before finalizing the purchase.
- **As a user**, I want to use the customization options of the items before adding the items to my current order.
- **As a user**, I want to check out and pay for my order securely so that my personal and financial information is safe.
- **As a user**, I want to review my order history so that I can keep track of my purchases.

## MVP (Minimum Viable Product)

- User registration and login
- Browsing for items
- Adding items to current order
- Modifying the item quantity of the current order
- Burger customization options
- Shake customization options
- Secure Payment process
- Order History

## Stretch Goals

- A rewards/points system that allows the user to purchase items using points they receive from previously placed orders or from donating to causes
- A closed store feature that allows the user to browse items, but limits adding items to the current order, viewing the current order, or placing an order
- Delivery service type
- Pizza as an item with topping customization options
- Adding items to a favorites list

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

## Requirements

- **Clean Codebase**: All code should be clean and well-documented. The repository should not include any unnecessary files or folders such as the `target/`, `.DS_Store`, etc. All files and directories should be appropriately named and organized.

- **Database Design**: The database should be designed following the principles of the 3rd Normal Form (3NF) to ensure data integrity and efficiency. An Entity Relationship Diagram (ERD) should be included in the documentation.

- **Secure**: All sensitive user data such as passwords must be securely hashed before storing it in the database. The application should not display any sensitive information in error messages.

- **Error Handling**: The application should handle potential errors gracefully and provide clear and helpful error messages to the users.

- **Testing**: The application should have a high test coverage. Unit tests and integration tests should be implemented using JUnit, Mockito, and PowerMock.

- **Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting progress.

- **Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should be well-commented to allow for easy understanding and maintenance.

- **Scalable**: The design of the application should be scalable, allowing for easy addition of new features or modifications in the future.
