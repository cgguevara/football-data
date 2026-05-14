# Phase 6 — League: Validation Criteria

The implementation is complete and ready to merge when **all** of the following pass.

---

## 1 — Schema

- [ ] `schema.sql` contains a valid `CREATE TABLE league` with columns: `league_id`, `league_name` (NOT NULL), `country` (NOT NULL), `season`, `teams_number`.
- [ ] `schema.sql` contains `ALTER TABLE club ADD COLUMN league_id INTEGER REFERENCES league(league_id)`.
- [ ] Running the app from scratch against a blank PostgreSQL database creates both tables and the FK without errors.

---

## 2 — Model

- [ ] `League.java` is a proper JPA `@Entity` mapped to the `league` table with all five fields.
- [ ] `Club.java` has a `@ManyToOne` association to `League` via `league_id`; the column is nullable.

---

## 3 — API — League CRUD

Test each endpoint with a REST client (curl, Postman, or browser dev tools):

| Check | Expected result |
|-------|-----------------|
| `POST /api/leagues` with `{ "leagueName": "Premier League", "country": "England", "season": "2024-25", "teamsNumber": 20 }` | HTTP 201, body includes `league_id` |
| `GET /api/leagues` | HTTP 200, returns array containing the created league |
| `GET /api/leagues/{id}` with valid id | HTTP 200, returns the league |
| `GET /api/leagues/9999` | HTTP 404 |
| `PATCH /api/leagues/{id}` with `{ "season": "2025-26" }` | HTTP 200, `season` updated |
| `DELETE /api/leagues/{id}` | HTTP 200 or 204 |
| `DELETE /api/leagues/9999` | HTTP 404 |

---

## 4 — Club–League Association (API)

- [ ] `POST /api/clubs` with a valid `leagueId` creates a club linked to that league.
- [ ] `GET /api/clubs/{id}` response includes league information (or `league_id`).
- [ ] Creating a club without `leagueId` succeeds (nullable FK).

---

## 5 — Club UI — League Dropdown

- [ ] Opening `createOrUpdateClub.html` shows a `<select>` dropdown populated with all leagues from the database.
- [ ] A "— No league —" placeholder option is present and selected by default.
- [ ] Selecting a league and saving a club persists the `league_id` correctly (verify in the DB or via `GET /api/clubs/{id}`).
- [ ] Editing an existing club with an assigned league shows that league pre-selected in the dropdown.

---

## 6 — Error Handling

- [ ] `LeagueNotFoundException` returns HTTP 404 with a meaningful message via the existing `@ControllerAdvice` handler.
- [ ] No unhandled exceptions reach the user for the happy path or the not-found case.

---

## 7 — Build

- [ ] `mvn clean package` (or equivalent) completes with no compilation errors.
- [ ] Existing tests (if any) still pass.
