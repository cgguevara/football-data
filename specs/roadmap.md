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


## Phase 5 — Create the thymeleaf templates for player

Create the following templates for the frontend of the app:

- createOrUpdatePlayer.html -> This template show a form with a list of text fields to create a Player. The list of text fields are:

  first name (String), last name(String), nationality(String), birth date(LocalDate), birth place(String), position(String), weight(Float), height(Float)

  birth date is a text field and should display a calendar when the user clicks on it and select a date, after the date is selected, the date
  should be stored in format yyyy-mm-dd

  Below the fields mentioned above should be a button called Save Player

  After the user click on "Save Player" button the player is created in the database and associated to the club
  from which the createOrUpdatePlayer.html template was called.

  Then the clubDetails.html page/template is displayed with the details of the club and the list of players belonging to the club

- playerDetails.html -> This template show the text "Player Information"
  Above  shows a table with the main details of the Player, for example:
  Name:    		Diego Maradona
  Nationality:    Argentina
  Birth Date:		1960-03-14
  Birth Place:	Buenos Aires - Argentina
  Position:		Midfielder
  Weight:			68 kg
  Height:			1,72 m.

  This fields are not editable

- modify the template clubDetails.html to add 2 colums to the table Players

    * edit player -> this will have a link or button with text "edit" when the user click on that the template createOrUpdatePlayer.html will be
      loaded and the user can edit the player information and updated it.

    * view player details -> this will have a link or button with text "view details", when the user click on that the template playerDetails will be
      loaded showing the detailed information of a player.

					