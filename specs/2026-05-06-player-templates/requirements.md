# Phase 5 — Player Templates: Requirements

## Scope

Add Thymeleaf templates and supporting backend routes to create, edit, view, and delete players, and associate them to an existing club. Also extend `clubDetails.html` with per-player action columns.

## New templates

| Template | Purpose |
|---|---|
| `createOrUpdatePlayer.html` | Form to create a new player or edit an existing one |
| `playerDetails.html` | Read-only view of a single player's details |

## Modified templates

| Template | Change |
|---|---|
| `clubDetails.html` | Add three action columns to the players table: Edit, View Details, Delete |

---

## Decisions

### Position field — dropdown
Position is rendered as a `<select>` element populated from the fixed list of 10 positions defined in mission.md:
Goalkeeper, Centre-Back, Left-Back, Right-Back, Defensive Midfield, Central Midfield, Attacking Midfield, Left Winger, Right Winger, Centre-Forward.

### Club association — URL path
The club is passed via the URL: `/clubs/{clubId}/players/new` for creation and `/clubs/{clubId}/players/{playerId}/edit` for editing. The `clubId` is embedded as a hidden field in the form and sent with the POST so the player is always linked to the correct club without the user choosing it.

### Delete action
The players table in `clubDetails.html` gets three action columns: **Edit**, **View Details**, and **Delete**. Delete sends a POST to `/clubs/{clubId}/players/{playerId}/delete` (POST-with-override pattern, no JavaScript required).

### Weight and Height — labeled inputs
The form labels read **Weight (kg)** and **Height (m)**. The inputs accept raw decimal numbers; the display templates append the unit string when rendering.

### Birth date — date picker
`birth_date` is an `<input type="date">` field. The browser renders a native calendar picker. The value is submitted and stored as `yyyy-MM-dd`.

---

## Backend changes required

| Layer | Change |
|---|---|
| `PlayerWebController` | Add GET/POST routes for create, edit, delete, and detail view under `/clubs/{clubId}/players/...` |
| `PlayerService` | Add `deletePlayer(Integer playerId)` if not present |
| `PlayerRepository` | No new queries expected — existing `findById` and `findAll` are sufficient |

---

## Context

- Follows the same layout/fragment conventions established in Phase 4 (`layout.html`, `fragments/header.html`, `fragments/footer.html`).
- Tailwind CSS utility classes for styling (consistent with Phase 4 templates).
- Club must already exist in the database; the `clubId` in the URL is validated server-side.
- After saving or deleting a player, the user is redirected to `clubDetails.html` for the same club.
