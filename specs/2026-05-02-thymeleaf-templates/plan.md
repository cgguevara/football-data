---
phase: 4
feature: Thymeleaf Templates — Club Flow
branch: phase-4-thymeleaf-templates
date: 2026-05-02
---

# Plan — Thymeleaf Templates (Club Flow)

Tasks are numbered and grouped by layer. Complete each group before moving to the next — later groups depend on earlier ones being stable.

---

## Group 1 — Backend: New Search Endpoint

1. Add `findByClubNameIgnoreCase(String clubName)` to `ClubRepository` (Spring Data derived query).
2. Add `findClubByName(String clubName)` to `ClubService` — delegates to repository, throws `ClubNotFoundException` if absent.
3. Add `GET /api/clubs/search?name={clubName}` mapping to `ClubController` — returns `ClubDTO` or 404.

> This endpoint must be registered before the Thymeleaf controller calls it, and must coexist with `GET /api/clubs/{id}`.

---

## Group 2 — Web Controller

4. Create `ClubWebController.java` in `controller/` with `@Controller` (not `@RestController`).
5. Map routes to view names:
   - `GET /` → `welcome`
   - `GET /clubs` → `findClubs` (with optional `?name=` query param for post-search results)
   - `GET /clubs/new` → `createOrUpdateClub` (blank form)
   - `POST /clubs` → save club via `ClubService`, redirect to `GET /clubs/{id}`
   - `GET /clubs/{id}` → `clubDetails` (populate model with club data)

---

## Group 3 — Base Layout and Static Assets

6. Create `src/main/resources/templates/fragments/layout.html`:
   - Standard HTML5 boilerplate with Tailwind CDN `<script>` tag.
   - Top navigation bar with links: **Home** (`/`) and **Find Clubs** (`/clubs`).
   - A `<main>` content block using Thymeleaf `th:fragment` / `th:replace` pattern.
7. Create `src/main/resources/templates/error/error.html`:
   - Generic error page showing HTTP status and message.
   - Extends `layout.html`.

---

## Group 4 — Thymeleaf Templates

8. Create `welcome.html` — extends layout; renders heading "Welcome to Football Data!".
9. Create `findClubs.html` — extends layout; contains:
   - Heading "Find a Club".
   - Text input bound to `name` query param.
   - **Find Club** button: submits `GET /clubs?name=…`.
   - Results area: shows club name if found, "No club found" if not.
   - **Add Club** button: links to `GET /clubs/new`.
10. Create `createOrUpdateClub.html` — extends layout; contains:
    - Form fields: Club Name, City, Year Founded, Stadium, Capacity.
    - Inline validation error messages per field (`th:if="${#fields.hasErrors(…)}"`).
    - **Save Club** button: submits `POST /clubs`.
11. Create `clubDetails.html` — extends layout; contains:
    - Heading "Club Details".
    - Table rows: Name, City, Year Founded, Stadium, Capacity populated from model.

---

## Group 5 — Integration and Smoke Test

12. Start the app (`mvn spring-boot:run`) and manually verify each page loads without exceptions.
13. Run `mvn test` and confirm all existing Phase 3 tests are still green.
14. Fix any Thymeleaf template-resolution or binding errors surfaced during smoke test.
