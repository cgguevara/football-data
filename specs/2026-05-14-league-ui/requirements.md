# Phase 7 — League UI: Requirements

## Scope

Build the Thymeleaf frontend for league management: create/edit form, detail page, a clubs-by-league list, and surface leagues on the welcome page. Also update the navigation header to expose league entry points.

No new REST API endpoints are required; the web layer calls the existing `LeagueService` and `ClubService` introduced in Phase 6.

---

## Context

Phase 6 delivered the full League backend (schema, entity, CRUD API). Phase 7 makes leagues visible and manageable through the browser UI, following the same Thymeleaf + Tailwind CSS patterns already in place for clubs and players.

---

## Decisions

| # | Decision | Reason |
|---|----------|--------|
| 1 | `createOrUpdateLeague.html` handles both create and edit | Consistent with `createOrUpdateClub.html` / `createOrUpdatePlayer.html` patterns; avoids duplicate templates. |
| 2 | `leagueDetails.html` is read-only (no edit/delete buttons) | User confirmed no action buttons in this phase. |
| 3 | `listClubs.html` Details column links to `clubDetails.html` | Both Club Name and the Details link navigate to the same club detail page — no secondary destination needed. |
| 4 | welcome.html shows "No leagues yet. Create one!" when the league list is empty | Prevents a confusing blank section; guides the user toward the next action. |
| 5 | Header updated: add "Create League", rename "Add Club" → "Create Club" | Keeps navigation aligned with the growing feature set. |

---

## Pages & Backend

### New templates

| Template | Route (Web) | Description |
|----------|-------------|-------------|
| `createOrUpdateLeague.html` | `GET /leagues/new`, `GET /leagues/{id}/edit` | Form: League Name, Country, Season, Number of Teams. "Save League" button. |
| `leagueDetails.html` | `GET /leagues/{id}` | Read-only detail table for a single league. |
| `listClubs.html` | `GET /leagues/{id}/clubs` | Three-column table: Club Name (link to clubDetails), City, Details (link to clubDetails). |

### Modified templates

| Template | Change |
|----------|--------|
| `welcome.html` | Add leagues section: list of all leagues (name link → listClubs, country). Empty state: "No leagues yet. Create one!" |
| `fragments/header.html` | Add "Create League" link; rename "Add Club" → "Create Club"; keep "Find Clubs". |

### New web controller

`LeagueWebController` — `@Controller`, handles all league UI routes:

| Method | Path | Action |
|--------|------|--------|
| GET | `/leagues/new` | Show empty create form |
| POST | `/leagues` | Save new league → redirect to `leagueDetails` |
| GET | `/leagues/{id}` | Show league detail page |
| GET | `/leagues/{id}/edit` | Show pre-populated edit form |
| POST | `/leagues/{id}` | Save updated league → redirect to `leagueDetails` |
| GET | `/leagues/{id}/clubs` | Show all clubs belonging to this league |

### ClubService addition

Add `getClubsByLeagueId(Integer leagueId)` — returns all clubs whose `league_id` matches, used by `listClubs.html`.

### ClubRepository addition

Add `findByLeague_LeagueId(Integer leagueId)` — Spring Data derived query for the above.

---

## Out of Scope

- Edit or delete actions on `leagueDetails.html`.
- Pagination on the clubs list.
- League search / filter.
