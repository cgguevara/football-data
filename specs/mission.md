Football-Data

## Overview
Football-Data is a web application and API service that:
1.	Registers the information of a football club like name, city, stadium, titles
2.	Registers the information of a football player like name, nationality, position, date birth, place of birth, weight and height
3.	Registers the information about statistics of the club like players with most matches played, players with more goals scored, etc

## Motivation

Football-Data is created to be a trusted source of information for football fans, and also for those that enjoy having the most recent statistics and data about football players and clubs around the world.

## Flow

1.	User creates a football club, example: Arsenal and enters information about the club like Name, City, Foundation date, stadium where plays the games, etc
2.	User enters information about the footballers that plays at the club, for example for Arsenal: Martin Odegaard, Declan Rice, etc.  and enter information like nationality, position, date of birth, place of birth, weight, height, and titles won
3.	User enters relevant statistics about a club like  top 10 players with matches played, top 10 players with goals scored, etc.

## Endpoints
All endpoints return JSON. Authentication is via API key in the `Authorization: Bearer <key>` header. MVP supports a single global API key configured via environment variable.
## Club Management
| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/clubs` | Register a new club Returns `club_id` |
| `GET` | `/clubs/:id` | Retrieve club information : name, city, stadium, titles.
| `PATCH` | `/clubs/:id` | Update club  information (e.g. stadium, city, etc)

## Players Management

| `POST` | `/players` | Register a new player Returns `player_id` |
| `GET` | `/ players /:id` | Retrieve player information : first name, last name, age, nationality, position, date of birth, place of birth, weight, height.
| `PATCH` | `/ players /:id` | Update player  information (e.g. club, place or date of birth, etc.)

## API Response: Club retrieve example:
Json:

{
"club_id": "101",
"club_name": "Arsenal",
"city": "London",
"founded": 1886,
"stadium": "Emirates Stadium",
"capacity": 60000,
"players": [
{
"first_name": "David",
"last_name": "Raya",
"date_of_birth": "1995-09-15",
"nationality": "Spain"
}
]
}


### Error Responses

| HTTP Status | Code | Meaning |
|-------------|------|---------|
| 400 | `INVALID_REQUEST` | Malformed body, missing required fields |
| 404 | `CLUB_NOT_FOUND` | Unknown `club_id` |
| 404 | `PLAYER_NOT_FOUND` | Unknown `player_id` |

Basic Knowledge Base

## Positions
The position of a player could be:
•	Goalkeeper
•	Centre-Back
•	Left-Back
•	Right-Back
•	Defensive Midfield
•	Central Midfield
•	Attacking Midfield
•	Left Winger
•	Right Winger
•	Centre-Forward

## Club Data Model
Club Record
| Field | Type | Description |
club_id	|int(8) | unique identifier
club_name | string  | club name
city  | string  | city where the club plays
founded  |  int  | year when the club was founded
stadium  | string  | stadium where the club plays as local
capacity  | int  | number of assistants
## Player Data Model
Player Record
| Field | Type | Description |
Player_id  | int(8) | unique identifier
First_name  | string  | first name of player
Last_name  | string  | last name of player
Nationality  | string  | country where player was born
Birth_date  | datetime  | day, month and year when player was born
Birth_place  | string  | city and country where player was born
Position  | string  | position where player playns in the field
Weight   | float  | weight in kilograms
Height  | float  | height in meters

##Dashboard
## Overview Page (Default view)
The dashboard home shows the list of the clubs created and that exists in the database
When the user click in the name of the club it request the list of the players that belong to that club.

## Database Schema
Sql
CREATE TABLE club (
club_id SERIAL PRIMARY KEY,
club_name VARCHAR(255) NOT NULL,
city VARCHAR(100),
founded INTEGER,
stadium VARCHAR(255),
capacity INTEGER
);

CREATE TABLE player (
player_id SERIAL PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
nationality VARCHAR(100),
birth_date DATE,
birth_place VARCHAR(255),
position VARCHAR(50),
weight REAL,
height REAL,
club_id INTEGER REFERENCES club(club_id)
);
