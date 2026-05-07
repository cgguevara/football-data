# Phase 5 — Player Templates: Validation

## How to know the implementation succeeded

### Create player
- [ ] Navigating to a club's detail page shows an "Add Player" button.
- [ ] Clicking "Add Player" loads `createOrUpdatePlayer.html` with an empty form and the correct `clubId` in the URL.
- [ ] The Position field is a dropdown containing exactly the 10 positions from mission.md.
- [ ] The Birth Date field shows a calendar picker when clicked.
- [ ] Weight and Height labels read "Weight (kg)" and "Height (m)".
- [ ] Submitting the form with all valid fields creates the player in the database and redirects to `clubDetails.html` for that club.
- [ ] The new player appears in the players table on `clubDetails.html`.

### Validation errors
- [ ] Submitting the form with a missing required field (e.g. First Name) re-renders the form with an inline error message next to the offending field.
- [ ] Valid fields already filled in are preserved on the re-rendered form (no data loss).

### Edit player
- [ ] Clicking "Edit" in the players table loads `createOrUpdatePlayer.html` pre-populated with the player's current data.
- [ ] Updating a field and clicking "Save Player" persists the change and redirects to `clubDetails.html`.
- [ ] The players table reflects the updated data.

### View player details
- [ ] Clicking "View Details" loads `playerDetails.html` with all fields shown as read-only.
- [ ] Weight is displayed with "kg" and Height with "m." appended.
- [ ] A back link returns to the club's `clubDetails.html`.

### Delete player
- [ ] Clicking "Delete" in the players table removes the player from the database.
- [ ] The page redirects to `clubDetails.html` for the same club.
- [ ] The deleted player no longer appears in the players table.

### clubDetails.html integrity
- [ ] The players table now has three action columns: Edit, View Details, Delete.
- [ ] Clubs with no players show the table with headers but no rows and no errors.
- [ ] All pre-existing club detail fields (Name, City, Year Founded, Stadium, Capacity) still render correctly.

## Merge criteria
All checklist items above pass against a locally running instance connected to a real PostgreSQL database. No compile errors or unresolved Thymeleaf template exceptions in the application log.
