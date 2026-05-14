# Phase 6 — League: Requirements

## Scope

Introduce the `league` domain into Football-Data: database schema, JPA entity, full CRUD backend, and the club–league association in the existing club UI form.

No Thymeleaf templates are created for League in this phase; league management is API-only. A dedicated UI phase follows.

---

## Context

Clubs compete in leagues. The relationship is many-clubs-to-one-league. The `league` table is defined in `mission.md` and extended here with two additional fields (`season`, `teams_number`) to capture the competition context.

---

## Decisions

| # | Decision | Reason |
|---|----------|--------|
| 1 | Full CRUD backend for League in this phase | League data needs to exist and be manageable via API before the UI phase can reference it. |
| 2 | No League UI templates yet | Keeps the phase small and shippable; club UI already covers the association via dropdown. |
| 3 | Add `season` (String) and `teams_number` (int) to `league` | Richer data model requested; `season` captures e.g. "2024-25"; `teams_number` captures participation size. |
| 4 | Club–league link exposed as dropdown on club create/edit form | Users need to assign a league when registering or editing a club without leaving the club form. |
| 5 | `league_id` FK on `club` is nullable | A club can exist before being assigned to a league. |

---

## Database Changes

### New table

```sql
CREATE TABLE league (
    league_id    SERIAL PRIMARY KEY,
    league_name  VARCHAR(255) NOT NULL,
    country      VARCHAR(100) NOT NULL,
    season       VARCHAR(20),
    teams_number INTEGER
);
```

### Alter existing table

```sql
ALTER TABLE club
    ADD COLUMN league_id INTEGER REFERENCES league(league_id);
```

---

## Backend Layer

### Model
- `League.java` — JPA entity mapping the `league` table.  
- `Club.java` — add `@ManyToOne` / `league_id` FK field.

### Repository
- `LeagueRepository.java` — extends `JpaRepository<League, Integer>`.

### Service
- `LeagueService.java` — business logic for create, find-all, find-by-id, update, delete.

### Controller
- `LeagueController.java` — REST endpoints under `/api/leagues`:

| Method | Path | Description |
|--------|------|-------------|
| POST   | `/api/leagues` | Create a new league |
| GET    | `/api/leagues` | List all leagues |
| GET    | `/api/leagues/:id` | Get league by ID |
| PATCH  | `/api/leagues/:id` | Update league fields |
| DELETE | `/api/leagues/:id` | Delete a league |

Error handling follows the existing `@ControllerAdvice` pattern in `tech-stack.md`.

---

## Frontend Change (Club form)

- `createOrUpdateClub.html` — add a `<select>` dropdown populated with all leagues (fetched from `LeagueService`).  
- The selected `league_id` is submitted with the club form and persisted via `ClubService`.
- Dropdown is optional (nullable FK); a "— No league —" placeholder option is included.

---

## Out of Scope

- Thymeleaf pages for listing, creating, or viewing leagues.
- League statistics or standings.
- Assigning multiple leagues to a club (one-to-many in the other direction).
