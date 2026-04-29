# Phase 3 — Requirements: Backend Core

## Scope

This phase builds the complete Java backend: database schema, entities, DTOs, repositories, services, controllers, and global error handling. The application will be fully functional as a JSON API after this phase; the Thymeleaf frontend (Phase 4) wires the UI on top.

| Deliverable | Package / Path | Purpose |
|-------------|---------------|---------|
| `schema.sql` | `src/main/resources/db/` | DDL for `club` and `player` tables |
| `Club.java`, `Player.java` | `model/` | JPA entities mapped to DB tables |
| `ClubRequestDTO`, `ClubResponseDTO` | `model/` | Club API contract |
| `PlayerRequestDTO`, `PlayerResponseDTO`, `PlayerSummaryDTO` | `model/` | Player API contract |
| `ClubRepository`, `PlayerRepository` | `repository/` | Spring Data JPA interfaces |
| `ClubService`, `PlayerService` | `services/` | Business logic and validation |
| `ClubController`, `PlayerController` | `controller/` | REST endpoints |
| `GlobalExceptionHandler` | `controller/` | Centralised error responses |
| `ClubNotFoundException`, `PlayerNotFoundException` | `model/` | Typed domain exceptions |

## API endpoints implemented

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/clubs` | Create a club → `201` |
| `GET` | `/api/clubs` | List clubs (paginated) → `200` |
| `GET` | `/api/clubs/{id}` | Get club by id → `200` |
| `PATCH` | `/api/clubs/{id}` | Partial update club → `200` |
| `GET` | `/api/clubs/{id}/players` | List players for a club → `200` |
| `POST` | `/api/players` | Create a player → `201` |
| `GET` | `/api/players/{id}` | Get player by id → `200` |
| `PATCH` | `/api/players/{id}` | Partial update player → `200` |

## Decisions

| Decision | Choice | Rationale |
|----------|--------|-----------|
| DTOs vs entities in controllers | Separate DTOs | Entities stay in the service/repository layer; controllers accept `*RequestDTO` and return `*ResponseDTO`. Prevents accidental exposure of JPA internals and makes validation annotations clean. |
| DTO location | `model/` package | Keeps all data-shape classes in one place for this project size; a separate `dto/` package would be premature. |
| Global error handler | Included in this phase | `@ControllerAdvice` is part of the controller layer — deferring it would leave the API returning unformatted Spring error pages during development. |
| Exception types | Custom `RuntimeException` subclasses | Allows the handler to map domain errors (`ClubNotFoundException`) to specific HTTP codes cleanly, matching the error contract in `mission.md`. |
| Pagination | `limit` / `offset` query params on `GET /api/clubs` | Matches the spec in `tech-stack.md`. |
| Player-to-club association on create | `clubId` required in `PlayerRequestDTO` | A player must belong to a club at creation time (tech-stack.md pipeline: "search for existing club first"). |
| Repository queries | Spring Data derived queries | `findByClub_ClubId` handles the players-by-club lookup without custom JPQL; keeps the repository layer minimal. |
| Test coverage | Spring Boot auto-test only | `mvn test` must be green; hand-written unit/integration tests are not required in this phase. |

## Data model

Defined in `mission.md` and `tech-stack.md`. Key constraints:
- `club_name` is `NOT NULL`.
- `first_name` and `last_name` are `NOT NULL`.
- `player.club_id` is a nullable FK — a player can be unattached in the DB, but the create flow requires a valid `clubId`.
- `position` is a free string; the valid values listed in `mission.md` (Goalkeeper, Centre-Back, etc.) are documented but not enforced by a DB constraint at this stage.

## Out of Scope

- Thymeleaf HTML templates (Phase 4)
- `data.sql` seed data
- Authentication / API key validation (noted in `mission.md` as MVP scope but not yet wired)
- Statistics endpoints
- Docker / Testcontainers setup
