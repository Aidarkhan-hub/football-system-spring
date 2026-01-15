CREATE TABLE IF NOT EXISTS tickets (
                                       id BIGSERIAL PRIMARY KEY,
                                       match_id BIGINT NOT NULL,

                                       sector VARCHAR(50),
    seat VARCHAR(50),

    price INT NOT NULL,
    status VARCHAR(30) NOT NULL,
    buyer_name VARCHAR(120),

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_tickets_match
    FOREIGN KEY (match_id) REFERENCES matches(id)
    );
