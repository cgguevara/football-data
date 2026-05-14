# Phase 7 — League UI: Validation Criteria

The implementation is complete and ready to merge when **all** of the following pass.

---

## 1 — welcome.html

- [ ] Opening `/` in the browser shows a Leagues section with all leagues currently in the database.
- [ ] Each league row shows the league name as a clickable link and the country name.
- [ ] Clicking a league name navigates to `listClubs.html` (`/leagues/{id}/clubs`).
- [ ] When no leagues exist, the section shows: "No leagues yet." and a "Create one!" link that navigates to `/leagues/new`.

---

## 2 — header.html

- [ ] The nav bar contains a "Create League" link that navigates to `/leagues/new`.
- [ ] The nav bar shows "Create Club" (was "Add Club") and still navigates to the club creation form.
- [ ] The "Find Clubs" link is still present and functional.

---

## 3 — createOrUpdateLeague.html (Create)

- [ ] Navigating to `/leagues/new` renders the form with the title "Create League" and empty fields.
- [ ] Submitting with a valid payload (League Name, Country; Season and Number of Teams are optional) creates the league and redirects to `leagueDetails.html`.
- [ ] Submitting with League Name or Country blank shows an inline validation error and does not create the league.

---

## 4 — createOrUpdateLeague.html (Edit)

- [ ] Navigating to `/leagues/{id}/edit` renders the form with the title "Edit League" and fields pre-populated with the existing league data.
- [ ] Submitting the edit form saves the changes and redirects to `leagueDetails.html` for that league.
- [ ] Navigating to `/leagues/9999/edit` for a non-existent league redirects gracefully (no 500 error).

---

## 5 — leagueDetails.html

- [ ] Navigating to `/leagues/{id}` shows a read-only table with Name, Country, Season, and Number of Teams.
- [ ] No edit or delete buttons are present.
- [ ] Navigating to `/leagues/9999` for a non-existent league redirects gracefully.

---

## 6 — listClubs.html

- [ ] Navigating to `/leagues/{id}/clubs` shows the heading "Clubs in [League Name]".
- [ ] The three-column table lists Club Name (link to `/clubs/{id}`), City, and a "View Details" link (also to `/clubs/{id}`).
- [ ] Clicking Club Name or "View Details" opens `clubDetails.html` for the correct club.
- [ ] When no clubs belong to the league, a friendly empty state is shown (no 500 error, no blank table).

---

## 7 — Backend

- [ ] `ClubRepository.findByLeague_LeagueId` returns only clubs whose `league_id` matches.
- [ ] `ClubService.getClubsByLeagueId` returns an empty list (not an exception) when no clubs are assigned to that league.
- [ ] `LeagueWebController` redirects to `/` (or `/leagues`) on `LeagueNotFoundException` rather than crashing.

---

## 8 — Build & Tests

- [ ] `mvn clean package` completes with no compilation errors.
- [ ] Existing tests still pass.
