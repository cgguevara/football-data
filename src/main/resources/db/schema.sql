CREATE TABLE IF NOT EXISTS league (
    league_id    SERIAL PRIMARY KEY,
    league_name  VARCHAR(255) NOT NULL,
    country      VARCHAR(100) NOT NULL,
    season       VARCHAR(20),
    teams_number INTEGER
);

CREATE TABLE IF NOT EXISTS club (
    club_id   SERIAL PRIMARY KEY,
    club_name VARCHAR(255) NOT NULL,
    city      VARCHAR(100),
    founded   INTEGER,
    stadium   VARCHAR(255),
    capacity  INTEGER
);

ALTER TABLE club
    ADD COLUMN IF NOT EXISTS league_id INTEGER REFERENCES league(league_id);

CREATE TABLE IF NOT EXISTS player (
    player_id   SERIAL PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    nationality VARCHAR(100),
    birth_date  DATE,
    birth_place VARCHAR(255),
    position    VARCHAR(50),
    weight      REAL,
    height      REAL,
    club_id     INTEGER REFERENCES club(club_id)
);
