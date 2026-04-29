## Football-DataTech Stack
## System Design
Football-Data is a Spring Boot application backed by PostgreSQL. Information is registered via a REST API, entering the information of both clubs and players through the API that receives the queries via a GUI/Frontend created with Thymeleaf
**Architectural layers:**
Frontend: Thymeleaf with Tailwind CSS to style the application using utility classes
Backend: Spring Boot + Java 21 and JPA
Services: Create a football club -> create a player and associate it to an existing club. -> Register statistics about the club.
Storage: PostgreSQL
## Create Football Club Pipeline
1.	User enters information about the football club through a form
2.	The app validates that the information entered has the correct format (dates, numbers, string)
3.	If validation fails return an error
4.	If the information entered is correct, then create the football club in the database

## Create Player Pipeline
1.	Search for an existing club in the database.
2.	If the club doesn’t exist in the database return an error.
3.	If the club exists, the user enters information about the player through a form
4.	The app validates that the information entered has the correct format  (dates, numbers, string)
5.	If validation fails return an error
6.	If the information entered is correct, then create the player in the database and associate it with the club.

### Error Handling

Manage the errors with the Global Exception Handling pattern

The @ControllerAdvice Pattern: Instead of putting try-catch blocks in every controller method, use a global handler. This centralizes your logic and keeps your code clean.
Create a class annotated with @ControllerAdvice
Use @ExceptionHandler methods to catch specific exceptions (like UserNotFoundException or DatabaseException).

Handling Validation Errors

•	Use Bean Validation (JSR-380) annotations in your data models.

•	Use @NotNull, @Size, or @Email in your Entity or DTO.


•	Use th:if="${#fields.hasErrors('fieldName')}" to display the specific error message next to the input field.
Create a friendly Custom Error Page
•	Create an error.html file in your src/main/resources/templates/ folder.
•	Spring will automatically route all 4xx and 5xx errors to this page.

## API Reference
All routes live under the folder `src/main/java/football/data/controller`. And are exposed as /api/….

**Club Management**
| `POST` | `api/clubs` |
Body:     { club_name, city, founded, stadium, capacity }
Success:  201 { club_id, club_name, ... }
Conflict: 200 (existing club returned)
Error:    400 (missing club_name)

GET /api/clubs
Query:    ?&limit=20&offset=0
Success:  200 { clubs: [...], total: N }

GET /api/clubs/:id
Success:  200 { club_id, club_name, ... }
Error:    404

PATCH /api/clubs/:id
Body:     { any updatable fields: club_name, city, stadium, etc. }
Success:  200 { updated club }
Error:    404, 400 (invalid status value)

**Player Management**

| `POST` | `api/players` |
Body:     { first_name, last_name, nationality, birth_date, birth_place, position, weight, height }
Success:  201 { player_id, player_name, ... }
Conflict: 200 (existing player returned and associated to  the correct club)
Error:    400 (missing player_name)

GET /api/clubs/:id/players
Success:  200 { get all players associated to the club_id: [...], total: N }


| `GET` | `api/ players /:id` |
Success:  200 { first name, last name, age, nationality, position, date of birth, place of birth, weight, height., club name }
Error:    404

| `PATCH` | `/ players /:id` |

Body:     { any updatable fields: club, place or date of birth, etc. }
Success:  200 { updated player }
Error:    404, 400 (invalid status value)


## Project Layout
football-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── football/
│   │   │       └── data/
│   │   │           ├── controller/     # Web Layer: Handles HTTP requests
│   │   │           ├── services/       # Business Layer: Logic and validation
│   │   │           ├── repository/     # Data Layer: Spring Data JPA interfaces
│   │   │           └── model/          # Domain Layer: JPA Entities and POJOs
│   │   └── resources/
│   │       ├── db/                     # SQL scripts (Schema, Seeds, Migrations)
│   │       │   ├── schema.sql
│   │       │   └── data.sql
│   │       ├── templates/              # Thymeleaf HTML files
│   │       │   ├── fragments/          # Reusable UI components (header, footer)
│   │       │   ├── error/              # Custom error pages (404.html, 500.html)
│   │       │   └── index.html
│   │       ├── static/                 # CSS, JS, and Images
│   │       └── application.properties  # Database and App configurations
│   └── test/                           # Unit and Integration tests
├── pom.xml (or build.gradle)           # Dependency management
└── .gitignore
