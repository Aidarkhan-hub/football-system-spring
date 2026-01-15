CREATE TABLE IF NOT EXISTS player_ratings (
                                              id BIGSERIAL PRIMARY KEY,
                                              player_id BIGINT NOT NULL UNIQUE,

                                              overall INT NOT NULL,
                                              speed INT NOT NULL,
                                              stamina INT NOT NULL,
                                              shooting INT NOT NULL,

                                              updated_at TIMESTAMP NOT NULL,

                                              CONSTRAINT fk_player_ratings_player
                                              FOREIGN KEY (player_id) REFERENCES players(id)
    );
