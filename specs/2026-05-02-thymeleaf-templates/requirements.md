---
phase: 4
feature: Thymeleaf Templates — Club Flow
branch: phase-4-thymeleaf-templates
date: 2026-05-02
---

# Requirements — Thymeleaf Templates (Club Flow)

## Scope

Deliver the full Thymeleaf-based frontend for the club management flow, exactly as specified in `roadmap.md`. Player-facing templates are out of scope for this phase.

### In scope
| Deliverable | Description |
|---|---|
| `layout.html` | Shared base layout with top navigation (Home, Find Clubs) |
| `welcome.html` | Landing page — renders "Welcome to Football Data!" |
| `findClubs.html` | Search form with Find Club and Add Club actions |
| `createOrUpdateClub.html` | Create-club form (Club Name, City, Year Founded, Stadium, Capacity) + Save Club button |
| `clubDetails.html` | Club detail view — table of Name, City, Year Founded, Stadium, Capacity |
| `GET /api/clubs/{clubName}` | New endpoint on `ClubController` — search club by name |
| `ClubWebController` | Spring MVC controller routing browser requests to Thymeleaf views |
| Tailwind CSS (CDN) | Loaded via `<script>` tag in `layout.html`; no Node.js build step |

### Out of scope
- Player templates (`findPlayers`, `createOrUpdatePlayer`, `playerDetails`) — future phase
- Authentication / API key enforcement on the web layer
- Pagination on search results

## Context

- Tech stack: Spring Boot + Thymeleaf, Java 21, PostgreSQL (see `specs/tech-stack.md`)
- Templates live under `src/main/resources/templates/`
- Reusable fragments (nav) go under `src/main/resources/templates/fragments/`
- Error pages go under `src/main/resources/templates/error/`
- All REST API routes remain under `/api/…`; new web routes are separate (e.g., `/`, `/clubs`, `/clubs/new`, `/clubs/{id}`)

## Key Decisions

| Decision | Choice | Reason |
|---|---|---|
| Tailwind delivery | CDN `<script>` tag | Avoids Node.js build tooling for MVP; fastest to ship |
| Player templates | Excluded | Phase 4 is strictly the club flow per roadmap; players are next |
| Search endpoint path | `GET /api/clubs/clubName` (uses request param `?name=…`) | Consistent with existing REST convention; avoids path-variable collision with `GET /api/clubs/{id}` |
| Form submission | Standard HTML `POST` through `ClubWebController` | Keeps the web layer decoupled from the REST API layer |

## Error Handling

- Validation errors on the create-club form: display inline messages using `th:if="${#fields.hasErrors('fieldName')}"` per `tech-stack.md`
- Club not found on search: display a "No club found" message in `findClubs.html` without redirecting
- Unhandled exceptions: routed to `error.html` (Spring auto-routes 4xx/5xx)
