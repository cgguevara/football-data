---
phase: 4
feature: Thymeleaf Templates — Club Flow
branch: phase-4-thymeleaf-templates
date: 2026-05-02
---

# Validation — Thymeleaf Templates (Club Flow)

This phase is complete and ready to merge when **all three gates** below pass.

---

## Gate 1 — App Starts Without Errors

Run:
```bash
mvn spring-boot:run
```

Pass criteria:
- No exceptions in startup log (no `BeanCreationException`, no `TemplateInputException`, no `IllegalArgumentException` from route mapping).
- Spring context loads fully; the line `Started FootballDataApplication` appears in the log.
- All five URLs below return HTTP 200 in a browser (no white-label error page):

| URL | Expected page |
|---|---|
| `http://localhost:8080/` | Welcome page |
| `http://localhost:8080/clubs` | Find Clubs page (empty state) |
| `http://localhost:8080/clubs?name=Arsenal` | Find Clubs page with search result or "No club found" |
| `http://localhost:8080/clubs/new` | Create Club form |
| `http://localhost:8080/clubs/{id}` (any valid id) | Club Details page |

---

## Gate 2 — Thymeleaf Renders Correctly

Verify each template renders its expected content with no Thymeleaf engine errors.

| Template | Must render |
|---|---|
| `welcome.html` | Heading "Welcome to Football Data!" visible |
| `findClubs.html` | Heading "Find a Club", text input, Find Club button, Add Club button |
| `createOrUpdateClub.html` | Form with fields: Club Name, City, Year Founded, Stadium, Capacity; Save Club button |
| `clubDetails.html` | Heading "Club Details"; table with at least 5 rows (one per field) populated from the model |
| `layout.html` | Top nav visible on every page above; Home and Find Clubs links resolve correctly |

Inline validation check (on `createOrUpdateClub.html`):
- Submit the form with Club Name left blank.
- Validation error message appears next to the Club Name field without a 500 error.

Search result check (on `findClubs.html`):
- Search for a name that exists → club name is displayed.
- Search for a name that does not exist → "No club found" message is displayed (no exception).

---

## Gate 3 — Existing API Tests Still Pass

Run:
```bash
mvn test
```

Pass criteria:
- All Phase 3 tests are green.
- Zero test regressions introduced by new web controllers or the new search endpoint.
- If `mvn test` was already failing before Phase 4 work began, document the pre-existing failures and confirm no new failures were added.

---

## Merge Checklist

- [ ] Gate 1 passed — app starts, all 5 URLs return 200
- [ ] Gate 2 passed — all templates render expected content; validation and search edge cases verified
- [ ] Gate 3 passed — `mvn test` green (or pre-existing failures documented and unchanged)
- [ ] `specs/roadmap.md` updated to mark Phase 4 as complete
- [ ] PR description references this validation file
