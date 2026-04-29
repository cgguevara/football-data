# Roadmap

Phases are intentionally small — each one is a shippable slice of work, independently reviewable and testable.

---

## Phase 1 — Create pom.xml file and download dependencies
- Create the pom.xml file with groupId, artifactId, properties, dependencies, etc
- Add dependencies required for thymeleaf, data-jpa, validation, webmvc, postgresql, etc

## Phase 2 — create the project layout according to tech-stack.md
- Create all the folders structured in the project layout


## Phase 3 — Build the backend: schema, models, controllers, services, and repositories
- Create the schema.sql file with the scripts to create the tables
- Create model/pojo classes
- Create all the controller classes with the routes for the endpoints defined in mission.md and tech-stack.md
- Create all the service classes with the business logic necessary to be aligned with defined in mission.md and tech-stack.md
- Create all the repository classes to be able to communicate with the database to be able to save, update and retrieve all the information required

## Phase 4 — Create the thymeleaf templates
- create the templates: createOrUpdateClub.html, findClubs.html, clubDetails.html, playersByClubList.html, createOrUpdatePlayer.html, etc.
