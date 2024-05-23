# Eventify - Event Discovery Platform

Welcome to **Eventify**, an innovative web application that empowers users to discover, share, and RSVP to events in various cities. Whether you're looking for a music concert, a comedy show, or a career fair, Eventify has got you covered!

## Table of Contents

- [About Eventify](#about-eventify)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)

## About Eventify

Eventify is a web application built to help users discover events in four major cities: Boston, New York, Los Angeles, and Miami. Users can search for events based on categories and locations, RSVP, and communicate directly with event organizers. Additionally, users can become event organizers and publish their events on the platform.

## Features

### User Registration and Authentication
- **Secure Registration and Login:** Users can securely register and log in to the platform.
- **Authentication:** Robust authentication mechanisms ensure the security of user accounts.

### Event Search and RSVP
- **Event Search:** Users can search for events by categories and locations.
- **RSVP:** Users can RSVP to events and receive email notifications with event details.

### Messaging System
- **Direct Communication:** Users can message event organizers to ask questions and receive event information.

### Event Organizer Functionality
- **Publish Events:** Users can become event organizers and publish their events.
- **Event Details:** Organizers can post event details, including descriptions, dates, times, and locations.
- **Discoverability:** Published events will be visible to all users on the platform.

## Technologies Used

- **Backend:** Spring Boot, Spring MVC, Spring Framework, JPA (Hibernate), MySQL
- **Frontend:** HTML, CSS, jQuery, Ajax, Thymeleaf

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL Database

### Steps

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yashlimbodiya/eventify.git
   cd eventify
   ```

2. **Backend Setup:**

   - **Configure MySQL Database:**
     Create a database named `eventify` and update the database configuration in `src/main/resources/application.properties`.

   - **Build and Run the Application:**
     ```sh
     mvn clean install
     mvn spring-boot:run
     ```

## Usage

1. **Register and Log In:**
   - Visit the registration page to create an account.
   - Log in using your credentials.

2. **Search for Events:**
   - Use the search functionality to find events by category and location.

3. **RSVP to Events:**
   - Click on an event to view details and RSVP. You will receive an email with event information.

4. **Message Organizers:**
   - Use the messaging system to communicate directly with event organizers.

5. **Become an Event Organizer:**
   - Publish your events by providing the necessary details. Your events will be visible to other users on the platform.

## Project Structure

```
eventify/
├── src/
│   ├── main/
│   │   ├── java/com/eventify/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── dao/
│   │   │   ├── service/
│   │   │   ├── util/
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   ├── templates/
│   │   │   └── application.properties
│   ├── test/
│   ├── ...
├── pom.xml
├── README.md
└── ...
```

## Screenshots

![image](https://github.com/yashlimbodiya/Eventify/assets/65219027/5c341041-f352-4904-8a3b-5a5a663a4b75)


---

Thank you for using Eventify! We hope you have a fantastic time discovering and attending events. Happy event hunting!
