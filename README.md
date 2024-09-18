# Notification Service

## Overview

The **Notification Service** is a Spring Boot-based project designed to manage and send various types of notifications to users. It supports multiple notification strategies, including marketing, news, and status notifications, while also ensuring rate-limiting functionality to avoid sending excessive notifications within a specific time frame.

## Features

- **Notification Strategies**: Supports multiple notification types like Marketing, News, and Status.
- **Rate Limiting**: Ensures that users are not overwhelmed with notifications by limiting the number of messages sent within a defined period.
- **Persistence**: Stores notification details using JPA with an H2 in-memory database.
- **Extensible Architecture**: Easily add new notification types by implementing the `NotificationStrategy` interface.

## Technologies Used

- **Java 22**
- **Spring Boot 3.3.3**
- **Spring Data JPA**
- **H2 Database**
- **Hibernate ORM 6.4.1**
- **SLF4J for Logging**

## Setup and Installation

### Prerequisites

- JDK 22 or later
- Maven
- Git

### Cloning the Repository

```bash
git clone https://github.com/your-username/notificationservice.git
cd notificationservice
```

### Running the Application

1. **Configure the Database (Optional)**: By default, the application uses an H2 in-memory database. You can modify the configuration in `application.properties` to use a different database.

2. **Build the Project**:
   
   ```bash
   mvn clean install
   ```

3. **Run the Application**:

   ```bash
   mvn spring-boot:run
   ```

   The application will be available at `http://localhost:8080`.

### H2 Database Console

The H2 database console can be accessed at: `http://localhost:8080/h2-console`. Use the credentials provided in `application.properties` for access.

## Usage

### Sending Notifications

Notifications are sent based on the type and user ID provided. The service determines the appropriate notification strategy and ensures rate limits are respected.

Example of notification types supported:

- `MARKETING`
- `NEWS`
- `STATUS`

### Example Request

```json
{
  "type": "MARKETING",
  "userId": 123,
  "message": "Your marketing notification goes here."
}
```

### Adding a New Notification Type

To add a new notification type:

1. Create a new class that implements the `NotificationStrategy` interface.
2. Implement the `send` method with the logic for the new notification type.
3. Add the new strategy to the `NotificationServiceImpl` class.

## Project Structure

- **src/main/java/com/javaproject/notificationservice**: Contains all the main source files.
  - `services/strategy`: Contains different strategies for notification types.
  - `repository`: JPA repositories for interacting with the database.
  - `gateway`: Responsible for sending notifications (e.g., via email, SMS, etc.).

## Configuration

You can configure the application properties in `src/main/resources/application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=admin
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
```

## Future Enhancements

- **Support for additional notification channels** (e.g., Email, SMS, Push).
- **Integration with external notification gateways** like Twilio or SendGrid.
- **Customizable rate-limiting configuration** per user or notification type.

## Contributing

Contributions are welcome! Please create an issue before submitting a pull request.
