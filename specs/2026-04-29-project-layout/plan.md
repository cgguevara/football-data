# Phase 2 — Plan: Project Layout

Each task group is independently committable. Complete them in order.

---

## Group 1 — Create Java source package directories

1. Create `src/main/java/football/data/controller/` — web layer; handles HTTP requests.
2. Create `src/main/java/football/data/services/` — business layer; logic and validation.
3. Create `src/main/java/football/data/repository/` — data layer; Spring Data JPA interfaces.
4. Create `src/main/java/football/data/model/` — domain layer; JPA entities and POJOs.

---

## Group 2 — Create resources directories

1. Create `src/main/resources/db/` — SQL scripts (schema.sql, data.sql land here in Phase 3).
2. Create `src/main/resources/templates/fragments/` — reusable Thymeleaf UI components (header, footer).
3. Create `src/main/resources/templates/error/` — custom error pages (404.html, 500.html).
4. Create `src/main/resources/static/css/` — compiled Tailwind CSS output.
5. Create `src/main/resources/static/js/` — JavaScript assets.
6. Create `src/main/resources/static/images/` — image assets.

---

## Group 3 — Create test directory structure

1. Create `src/test/java/football/data/` — mirrors the main package root; unit and integration tests go here.

---

## Group 4 — Verify the full tree

1. Run `mvn compile` from the project root and confirm `BUILD SUCCESS`.
2. Visually confirm the directory tree matches the layout in `specs/tech-stack.md` (plus the `static/` subdirs agreed in the feature spec).
