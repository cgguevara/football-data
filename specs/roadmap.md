# Roadmap

Phases are intentionally small — each one is a shippable slice of work, independently reviewable and testable.

---

## Phase 1 — Create pom.xml file and download dependencies
- Create the pom.xml file with groupId, artifactId, properties, dependencies, etc
- Add dependencies required for thymeleaf, data-jpa, validation, webmvc, postgresql, etc

## Phase 2 — create the project layout according to tech-stack.md
- Create all the folders structured in the project layout


## Phase 3 — Create the schema.sql file with the scripts to create the tables


## Phase 4 — Create model/pojo classes 


## Phase 5 — Create the controller classes
- create all the controller classes with the routes for the endpoints defined in mission.md and tech-stack.md

## Phase 6 — Create the service classes
- create all the service classes with the business logic necessary to be aligned with defined in mission.md and tech-stack.md

## Phase 7 — Create the repository classes
- create all the repository classes to be able to communicate with the database to be able to save, update and retrieve all the information required

## Phase 7 — Create the thymeleaf templates
- create the templates: createOrUpdateClub.html, findClubs.html, clubDetails.html, playersByClubList.html, createOrUpdatePlayer.html, etc.
