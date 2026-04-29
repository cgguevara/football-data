# Phase 1 — Requirements: Maven Setup

## Scope

This phase produces the project skeleton needed for every subsequent phase to build on. No application source code is written here. The deliverables are:

| File | Purpose |
|------|---------|
| `pom.xml` | Maven build descriptor — declares all runtime and dev dependencies |
| `src/main/resources/application.properties` | Spring Boot configuration with placeholder DB credentials |
| `.gitignore` | Prevents build artifacts and IDE files from being committed |

## Decisions

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Build tool | Maven | Roadmap specifies `pom.xml`; standard in Java enterprise projects |
| Spring Boot version | Latest 3.x (3.4.x at time of writing) | LTS-ready, requires Java 17+, aligns with Java 21 target |
| Java version | 21 | Specified in tech-stack.md; LTS release |
| `groupId` | `football.data` | Matches the package structure in tech-stack.md |
| `artifactId` | `football-data` | Matches the project name |
| Lombok | Included | Reduces boilerplate on model and DTO classes (later phases) |
| Spring DevTools | Included (runtime scope) | Hot reload during local development |
| DB migration tool | None (out of scope) | `schema.sql` will be used directly; migrations left for a later decision |
| Test framework | `spring-boot-starter-test` only | No integration test infrastructure needed in this phase |

## Dependencies

| Dependency | Starter / Artifact | Why |
|---|---|---|
| Spring Web MVC | `spring-boot-starter-web` | REST controllers and HTTP layer |
| Thymeleaf | `spring-boot-starter-thymeleaf` | Server-side HTML templates |
| Spring Data JPA | `spring-boot-starter-data-jpa` | Repository layer and ORM |
| Bean Validation | `spring-boot-starter-validation` | `@NotNull`, `@Size`, etc. on entities and DTOs |
| PostgreSQL Driver | `postgresql` (runtime) | JDBC driver for the PostgreSQL database |
| Lombok | `lombok` (optional) | Code generation — getters, setters, builders |
| Spring DevTools | `spring-boot-devtools` (runtime, optional) | Live reload in development |
| Test | `spring-boot-starter-test` (test scope) | JUnit 5 + Mockito + Spring test context |

## application.properties — Required Keys

```
spring.datasource.url
spring.datasource.username
spring.datasource.password
spring.datasource.driver-class-name
spring.jpa.hibernate.ddl-auto
spring.jpa.show-sql
spring.jpa.properties.hibernate.format_sql
spring.sql.init.mode
server.port
```

Values are placeholders (e.g. `${DB_URL}`) — real credentials are supplied via environment variables and never committed.

## Out of Scope

- Any Java source files (model, controller, service, repository)
- Thymeleaf templates
- `schema.sql` / `data.sql`
- Docker Compose or containerisation config
- CI/CD configuration
