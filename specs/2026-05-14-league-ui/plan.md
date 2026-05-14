# Phase 7 — League UI: Implementation Plan

Tasks are grouped by layer. Complete each group before moving to the next; steps within a group can be done in any order unless noted.

---

## Group 1 — Repository

1.1 Add `findByLeague_LeagueId(Integer leagueId)` to `ClubRepository.java`.

---

## Group 2 — Service

2.1 Add `getClubsByLeagueId(Integer leagueId)` to `ClubService.java`:
   - Call `clubRepository.findByLeague_LeagueId(leagueId)`.
   - Map each result to `ClubResponseDTO` (without players list).
   - Throw `ClubNotFoundException` if the league has no clubs is acceptable (return empty list instead).

---

## Group 3 — Web Controller

3.1 Create `src/main/java/football/data/controller/LeagueWebController.java`:
   - Inject `LeagueService` and `ClubService`.
   - `GET /leagues/new` → add empty `LeagueRequestDTO` and `leagueId = null` to model, return `createOrUpdateLeague`.
   - `POST /leagues` → validate, call `leagueService.createLeague(dto)`, redirect to `/leagues/{id}`.
   - `GET /leagues/{id}` → call `leagueService.getLeagueById(id)`, add to model, return `leagueDetails`.
   - `GET /leagues/{id}/edit` → call `leagueService.getLeagueById(id)`, populate `LeagueRequestDTO`, add `leagueId` to model, return `createOrUpdateLeague`.
   - `POST /leagues/{id}` → validate, call `leagueService.updateLeague(id, dto)`, redirect to `/leagues/{id}`.
   - `GET /leagues/{id}/clubs` → call `leagueService.getLeagueById(id)` and `clubService.getClubsByLeagueId(id)`, add both to model, return `listClubs`.

---

## Group 4 — New Templates

4.1 Create `src/main/resources/templates/createOrUpdateLeague.html`:
   - Extend layout with `th:replace`.
   - Title: "Edit League" if `leagueId != null`, else "Create League".
   - Form action: `/leagues/{id}` if editing, `/leagues` if creating.
   - Fields: League Name, Country, Season, Number of Teams.
   - "Save League" submit button.
   - Inline validation error messages using `th:errors`.

4.2 Create `src/main/resources/templates/leagueDetails.html`:
   - Extend layout.
   - Heading: "League Details".
   - Read-only two-column table: Name, Country, Season, Number of Teams.

4.3 Create `src/main/resources/templates/listClubs.html`:
   - Extend layout.
   - Heading: league name (e.g. "Clubs in Premier League").
   - Three-column table: Club Name (link to `/clubs/{id}`), City, Details (link to `/clubs/{id}` with text "View Details").
   - Empty state row if no clubs belong to the league.

---

## Group 5 — Modified Templates

5.1 Update `src/main/resources/templates/welcome.html`:
   - Add a "Leagues" section below the existing content.
   - If the `leagues` list is non-empty: render a two-column table (League Name as link to `/leagues/{id}/clubs`, Country).
   - If `leagues` is empty: show the message "No leagues yet." with a "Create one!" link to `/leagues/new`.
   - Inject `leagues` from `WelcomeController` (or the controller currently serving `/`) — update `ClubWebController.welcome()` to add the leagues list to the model.

5.2 Update `src/main/resources/templates/fragments/header.html`:
   - Add "Create League" nav link pointing to `/leagues/new`.
   - Rename the existing "Add Club" link text to "Create Club".
   - Keep "Find Clubs" link unchanged.
