CREATE TABLE IF NOT EXISTS phones
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    brand           VARCHAR(50) NOT NULL,
    series          VARCHAR(50) NOT NULL,
    model           VARCHAR(50) NOT NULL,
    color           VARCHAR(50) NOT NULL,
    os              VARCHAR(50) NOT NULL,
    processor_model VARCHAR(50) NOT NULL,
    release_date    DATE        NOT NULL
);