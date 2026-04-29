# Phase 2 — Requirements: Project Layout

## Scope

This phase creates the complete directory skeleton for the Spring Boot application. No Java source files, SQL scripts, or HTML templates are written here — only the folder structure that every subsequent phase will populate.

| Directory | Layer | Populated in phase |
|-----------|-------|--------------------|
| `src/main/java/football/data/controller/` | Web | Phase 5 |
| `src/main/java/football/data/services/` | Business | Phase 6 |
| `src/main/java/football/data/repository/` | Data | Phase 7 |
| `src/main/java/football/data/model/` | Domain | Phase 4 |
| `src/main/resources/db/` | SQL scripts | Phase 3 |
| `src/main/resources/templates/fragments/` | UI components | Phase 8 |
| `src/main/resources/templates/error/` | Error pages | Phase 8 |
| `src/main/resources/static/css/` | Tailwind output | Phase 8 |
| `src/main/resources/static/js/` | JS assets | Phase 8 |
| `src/main/resources/static/images/` | Image assets | Phase 8 |
| `src/test/java/football/data/` | Tests | Phases 5–7 |

## Decisions

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Base package | `football.data` | Matches `groupId` set in Phase 1 and the layout in `tech-stack.md` |
| Git tracking of empty dirs | No `.gitkeep` files | Directories will receive real files in the very next phases; placeholder files add noise |
| `static/` subdirectories | `css/`, `js/`, `images/` | Tailwind CSS (specified in `tech-stack.md`) produces a compiled output file; a dedicated `css/` dir keeps assets organised from the start |
| Test package root | `src/test/java/football/data/` | Mirrors the main package so Spring's test context scanning works without extra configuration |
| Scope of this phase | Directories only | All file content (SQL, Java, HTML, properties) is handled in dedicated later phases to keep each phase independently reviewable |

## Context

`tech-stack.md` defines the canonical layout. This phase implements it exactly, with one agreed addition: `static/css/`, `static/js/`, and `static/images/` subdirectories under the flat `static/` shown in the spec.

The `application.properties` file and `pom.xml` were created in Phase 1 and already sit at their correct paths. This phase does not touch them.

## Out of Scope

- Any `.java` source files
- `schema.sql`, `data.sql`
- Thymeleaf HTML templates
- `application.properties` changes
- Docker Compose or CI/CD configuration
