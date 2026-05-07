# Phase 5 — Player Templates: Plan

## Task Group 1 — Backend routes in PlayerWebController

1.1 Add `GET /clubs/{clubId}/players/new` — returns `createOrUpdatePlayer.html` with an empty player form and the `clubId` in the model.
1.2 Add `POST /clubs/{clubId}/players/new` — binds form data to `PlayerRequestDTO`, calls `playerService.createPlayer(clubId, dto)`, redirects to `/clubs/{clubId}`.
1.3 Add `GET /clubs/{clubId}/players/{playerId}/edit` — loads the existing player, populates the form, returns `createOrUpdatePlayer.html`.
1.4 Add `POST /clubs/{clubId}/players/{playerId}/edit` — binds updated form data, calls `playerService.updatePlayer(playerId, dto)`, redirects to `/clubs/{clubId}`.
1.5 Add `GET /clubs/{clubId}/players/{playerId}` — loads player, returns `playerDetails.html`.
1.6 Add `POST /clubs/{clubId}/players/{playerId}/delete` — calls `playerService.deletePlayer(playerId)`, redirects to `/clubs/{clubId}`.

## Task Group 2 — Service and repository

2.1 Add `deletePlayer(Integer playerId)` to `PlayerService` (verify it does not exist already).
2.2 Confirm `playerService.createPlayer` accepts a `clubId` and sets the club association before saving.

## Task Group 3 — createOrUpdatePlayer.html

3.1 Extend `layout.html` if needed — no new nav items required.
3.2 Create `src/main/resources/templates/createOrUpdatePlayer.html`:
  - Fields: First Name, Last Name, Nationality, Birth Date (`type="date"`), Birth Place, Position (dropdown), Weight (kg), Height (m).
  - Hidden field: `clubId`.
  - Save Player button — POST to the correct route depending on create vs. edit mode.
  - Inline validation error messages using `th:if="${#fields.hasErrors('fieldName')}"`.

## Task Group 4 — playerDetails.html

4.1 Create `src/main/resources/templates/playerDetails.html`:
  - Heading: "Player Information".
  - Read-only table: Name, Nationality, Birth Date, Birth Place, Position, Weight (with "kg"), Height (with "m.").
  - Back link to the club details page.

## Task Group 5 — Update clubDetails.html

5.1 Add three columns to the players table:
  - **Edit** — link to `GET /clubs/{clubId}/players/{playerId}/edit`.
  - **View Details** — link to `GET /clubs/{clubId}/players/{playerId}`.
  - **Delete** — form with `POST /clubs/{clubId}/players/{playerId}/delete` and a confirm button.
5.2 Ensure the "Add Player" button links to `GET /clubs/{clubId}/players/new`.

## Task Group 6 — Smoke test

6.1 Start the app and verify the full create-player flow end-to-end.
6.2 Verify edit player pre-populates all fields correctly.
6.3 Verify view details shows all fields with units.
6.4 Verify delete removes the player and returns to club details.
6.5 Verify validation errors display on the form for missing required fields.
