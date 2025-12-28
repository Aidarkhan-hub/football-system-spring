ALTER TABLE players
    ADD CONSTRAINT fk_players_team
    FOREIGN KEY (team_id)
    REFERENCES teams(id)
    ON DELETE RESTRICT;

ALTER TABLE matches
    ADD CONSTRAINT fk_matches_home_team
    FOREIGN KEY (home_team_id)
    REFERENCES teams(id)
    ON DELETE RESTRICT;

ALTER TABLE matches
    ADD CONSTRAINT fk_matches_away_team
    FOREIGN KEY (away_team_id)
    REFERENCES teams(id)
    ON DELETE RESTRICT;

ALTER TABLE matches
    ADD CONSTRAINT chk_matches_different_teams
    CHECK (home_team_id <> away_team_id);
