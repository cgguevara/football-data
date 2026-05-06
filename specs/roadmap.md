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
- Create the following templates for the frontend of the app:


layout.html -> This template will be a superior menu with several items: home page (link to welcome.html page),
find clubs (link to findClubs.html page)

welcome.html -> This template is the welcome page, the page that is loaded when starting or calling football-data. This page shows the
text Welcome to Football Data!

findClubs.html -> This template show a form with a text Find a Club on the top.
below there is a text field where the user should
enter the name of the club that want to find.

Below there is a button with the text Find Club -> when the user click on the Find Club button, the system search in clubs table for the 
name of the club (use the endpoint /api/clubs/clubName to get the club)
	
Below there is a button with the text Add Club -> when the user click on that button, the system loads the createOrUpdateClub.html page/template

Additionally in the ClubController.java file, create an	endpoint: /api/clubs/clubName with @GetMapping that should search a club by the club name

createOrUpdateClub.html -> This template show a form with a list of text fields to create a Club. The list of text fields are:
Club Name, City, Year Founded, Stadium, Capacity
Below the text fields should be a button called Save Club.

After the user click on "Save Club" button the club is created in the database and the clubDetails.html page/template is displayed

clubDetails.html -> This template show the text "Club Details"
Above  shows a table with the main details of the Club, for example:
Name:    		Arsenal FC
City:    		London
Year Founded:	1886
Stadium:		Emirates Stadium
Capacity:		60.000
					