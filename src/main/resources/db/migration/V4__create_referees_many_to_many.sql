CREATE TABLE referees (
                          id BIGSERIAL PRIMARY KEY,
                          full_name VARCHAR(255) NOT NULL,
                          category VARCHAR(100)
);

CREATE TABLE match_referees (
                                match_id BIGINT NOT NULL,
                                referee_id BIGINT NOT NULL,
                                PRIMARY KEY (match_id, referee_id),
                                CONSTRAINT fk_match_referee_match
                                    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE,
                                CONSTRAINT fk_match_referee_ref
                                    FOREIGN KEY (referee_id) REFERENCES referees(id) ON DELETE CASCADE
);
