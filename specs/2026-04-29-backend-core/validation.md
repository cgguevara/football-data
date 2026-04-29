# Phase 3 — Validation: Backend Core

All checks must pass before this phase is considered complete and the branch is mergeable.

---

## 1. Clean Compile

```bash
mvn clean compile
```

**Pass:** `BUILD SUCCESS` with zero errors.
**Checks:** all entities, DTOs, repositories, services, controllers, and the exception handler compile against the declared dependencies.

---

## 2. Test Suite Green

```bash
mvn test
```

**Pass:** `BUILD SUCCESS`; the Spring Boot application context loads without errors. The auto-generated `FootballDataApplicationTests` (or equivalent) must pass.
**Checks:** no missing beans, no circular dependencies, all `@Autowired` / constructor injection points are satisfied.

---

## 3. Schema File Present and Correct

Open `src/main/resources/db/schema.sql` and confirm:

- [ ] `CREATE TABLE club` is present with all columns from `mission.md`: `club_id`, `club_name`, `city`, `founded`, `stadium`, `capacity`.
- [ ] `CREATE TABLE player` is present with all columns: `player_id`, `first_name`, `last_name`, `nationality`, `birth_date`, `birth_place`, `position`, `weight`, `height`, `club_id`.
- [ ] `club_id` in `player` has a `REFERENCES club(club_id)` foreign key.
- [ ] `club_name`, `first_name`, `last_name` are `NOT NULL`.

---

## 4. Endpoint Coverage

Confirm every route from `requirements.md` has a corresponding `@RequestMapping` method in the controllers:

| Method | Path | Controller method |
|--------|------|-------------------|
| `POST` | `/api/clubs` | `ClubController.createClub` |
| `GET` | `/api/clubs` | `ClubController.getAllClubs` |
| `GET` | `/api/clubs/{id}` | `ClubController.getClubById` |
| `PATCH` | `/api/clubs/{id}` | `ClubController.updateClub` |
| `GET` | `/api/clubs/{id}/players` | `ClubController.getPlayersByClub` |
| `POST` | `/api/players` | `PlayerController.createPlayer` |
| `GET` | `/api/players/{id}` | `PlayerController.getPlayerById` |
| `PATCH` | `/api/players/{id}` | `PlayerController.updatePlayer` |

**Pass:** each row has a matching method with the correct HTTP verb and path annotation.

---

## 5. DTO / Entity Separation

Confirm no controller method signature references a JPA entity class directly (neither as a `@RequestBody` parameter nor as a return type). Only `*DTO` classes should appear in controller signatures.

---

## 6. GlobalExceptionHandler Coverage

Open `GlobalExceptionHandler.java` and confirm it has `@ExceptionHandler` methods for:

- [ ] `ClubNotFoundException` → returns `404`
- [ ] `PlayerNotFoundException` → returns `404`
- [ ] `MethodArgumentNotValidException` → returns `400`
- [ ] Generic `Exception` fallback → returns `500`

---

## 7. Bean Validation Annotations

Confirm the following annotations are present:

| DTO | Field | Annotation |
|-----|-------|------------|
| `ClubRequestDTO` | `clubName` | `@NotBlank` |
| `PlayerRequestDTO` | `firstName` | `@NotBlank` |
| `PlayerRequestDTO` | `lastName` | `@NotBlank` |
| `PlayerRequestDTO` | `clubId` | `@NotNull` |

And that controller methods accepting these DTOs use `@Valid` on the `@RequestBody` parameter.

---

## Definition of Done

- [ ] `mvn clean compile` → `BUILD SUCCESS`
- [ ] `mvn test` → `BUILD SUCCESS`, context loads, auto-test passes
- [ ] `schema.sql` reviewed against data model in `mission.md`
- [ ] All 8 endpoints present in controllers
- [ ] No JPA entity in any controller signature
- [ ] `GlobalExceptionHandler` covers all 4 cases
- [ ] Bean Validation annotations in place on DTOs
- [ ] PR reviewed and approved
- [ ] Branch merged into `main`
