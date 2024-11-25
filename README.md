# Financial House Project - Backend
This is the backend service for the Financial House Project, developed as part of a case study. The project includes various endpoints to manage transactions, generate transaction reports, and handle transaction lists. The service handles authentication, request validation, and error handling, following industry best practices.

Table of Contents
- [Overview](#overview)
- [Technologies](#technologies)
- [Setup](#setup)
- [Authentication](#authentication)

##  Overview
The Financial House Project - Backend provides a set of services to interact with transaction data. It includes endpoints to fetch transaction reports, lists, and individual transactions, as well as the ability to authenticate users with tokens.

This backend service was created for a case study, and aims to demonstrate proper use of RESTful API design, token-based authentication, exception handling, and integration with external systems for fetching transaction data.

## Technologies
Java 17
Spring Boot: For building the backend API.
Spring Web: For handling HTTP requests and responses.
Spring Security: For implementing token-based authentication.
RestTemplate: For making HTTP requests to external services.
Lombok: To reduce boilerplate code.
Jackson: For JSON serialization and deserialization.
Junit (for unit testing, optional).

## Setup
Prerequisites
Make sure you have the following installed:

JDK 17 or later.
Gradle for dependency management.
An IDE like IntelliJ IDEA or VSCode (with Java support).

## Installation
- Clone the repository:
   ```bash
  git clone https://github.com/busecnky/FinancialHouseProject.git
  cd FinancialHouseProject

- Build and run the application:
     ```bash
    ./gradlew build
    ./gradlew bootRun

The application will be accessible on http://localhost:8080.

## Authentication
This service uses Token Authentication. You need to pass the token in the Authorization header of each request:

    ```
    Authorization: <your-token-here>
    The token can be obtained after a successful login. If the token is invalid or expired,
    the server will respond with a 401 Unauthorized error.

The token can be obtained after a successful login. If the token is invalid or expired, the server will respond with a 401 Unauthorized error.


