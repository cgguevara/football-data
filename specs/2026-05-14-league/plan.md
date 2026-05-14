# Phase 6 — League: Implementation Plan

Tasks are grouped by layer. Complete each group before moving to the next; groups within a layer can be done in any order.

---

## Group 1 — Database Schema

1.1 Open `src/main/resources/db/schema.sql` and append the `CREATE TABLE league` statement with fields: `league_id`, `league_name`, `country`, `season`, `teams_number`.

1.2 Append `ALTER TABLE club ADD COLUMN league_id INTEGER REFERENCES league(league_id)` to `schema.sql`.

---

## Group 2 — Domain Model

2.1 Create `src/main/java/football/data/model/League.java`:
   - Annotate with `@Entity`, `@Table(name = "league")`.
   - Fields: `leagueId` (PK, auto-generated), `leagueName`, `country`, `season`, `teamsNumber`.
   - Add getters and setters (or use Lombok if already in the project).

2.2 Update `src/main/java/football/data/model/Club.java`:
   - Add a `@ManyToOne @JoinColumn(name = "league_id")` field of type `League`.

---

## Group 3 — Repository

3.1 Create `src/main/java/football/data/repository/LeagueRepository.java`:
   - Extends `JpaRepository<League, Integer>`.
   - No custom queries needed at this stage.

---

## Group 4 — Service

4.1 Create `src/main/java/football/data/services/LeagueService.java`:
   - `createLeague(League league)` — save and return the new league.
   - `getAllLeagues()` — return all leagues.
   - `getLeagueById(Integer id)` — return league or throw `LeagueNotFoundException`.
   - `updateLeague(Integer id, League updated)` — patch allowed fields, save, return.
   - `deleteLeague(Integer id)` — delete by id; throw `LeagueNotFoundException` if missing.

4.2 Add `LeagueNotFoundException` to the existing exception package and register it in the `@ControllerAdvice` handler with HTTP 404.

---

## Group 5 — Controller

5.1 Create `src/main/java/football/data/controller/LeagueController.java`:
   - `POST /api/leagues` → `createLeague`
   - `GET /api/leagues` → `getAllLeagues`
   - `GET /api/leagues/{id}` → `getLeagueById`
   - `PATCH /api/leagues/{id}` → `updateLeague`
   - `DELETE /api/leagues/{id}` → `deleteLeague`

---

## Group 6 — Club Service & Controller Updates

6.1 Update `ClubService.java` so that `createClub` and `updateClub` accept and persist an optional `leagueId`.

6.2 Update `ClubController.java` so the create and update endpoints forward `leagueId` to the service.

---

## Group 7 — Club UI: League Dropdown

7.1 Update `createOrUpdateClub.html`:
   - Inject the list of leagues into the model from `ClubController` (call `LeagueService.getAllLeagues()`).
   - Render a `<select name="leagueId">` dropdown with a "— No league —" placeholder option and one `<option>` per league.
   - Bind the selected value to the club form submission.
