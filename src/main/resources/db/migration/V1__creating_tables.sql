CREATE TABLE IF NOT EXISTS teams (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL UNIQUE,
    city VARCHAR(120),
    coach_name VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS players (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    position VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    jersey_number INT NOT NULL,
    goals INT NOT NULL DEFAULT 0,
    team_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS matches (
    id BIGSERIAL PRIMARY KEY,
    home_team_id BIGINT NOT NULL,
    away_team_id BIGINT NOT NULL,
    home_goals INT NOT NULL DEFAULT 0,
    away_goals INT NOT NULL DEFAULT 0,
    match_time TIMESTAMP NOT NULL,
    stadium VARCHAR(200)
);
