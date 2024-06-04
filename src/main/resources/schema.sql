CREATE TABLE footballers (
    id           VARCHAR(1000000000) NOT NULL,
    born_date    DATE,
    yellow_cards INT,
    red_cards    INT
);
CREATE TABLE exclusions (
    id            VARCHAR(1000000000) NOT NULL,
    footballer_id VARCHAR(1000000000) NOT NULL,
    type          VARCHAR(100) NOT NULL ,
    quantity      int,
    active        boolean,
    till          date
)
