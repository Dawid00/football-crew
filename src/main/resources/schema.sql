CREATE TABLE teams (
    id   VARCHAR(1000000000) NOT NULL,
    name varchar(100)        not null
);

CREATE TABLE footballers_instance (
    id VARCHAR(1000000000) NOT NULL
);

CREATE TABLE footballers (
    id                     VARCHAR(1000000000) NOT NULL,
    footballer_instance_id VARCHAR(1000000000) NOT NULL,
    born_date              DATE                NOT NULL,
    yellow_cards           INT,
    red_cards              INT,
    kind                   varchar(200)
);
CREATE TABLE injuries (
    id                     VARCHAR(1000000000) NOT NULL,
    footballer_id VARCHAR(1000000000) NOT NULL,
    till                   date                NOT NULL,
    name                   varchar(200),
    reason                 varchar(200),
    status                 varchar(200)
);
CREATE TABLE exclusions (
    id            VARCHAR(1000000000) NOT NULL,
    footballer_id VARCHAR(1000000000) NOT NULL,
    type          VARCHAR(100)        NOT NULL,
    quantity      int,
    active        boolean,
    till          date
);

CREATE TABLE teams_footballers (
    team_id                VARCHAR(1000000000) not null,
    footballer_instance_id VARCHAR(1000000000) NOT NULL
);
CREATE TABLE league (
    id VARCHAR(1000000000) NOT NULL
);
CREATE TABLE league_matches (
    id        VARCHAR(1000000000) NOT NULL,
    league_id VARCHAR(1000000000) NOT NULL,
    at        DATE                NOT NULL,
    game_id   int                 NOT NULL,
    status    varchar(100)        NOT NULL,
    a_minute  varchar(1000)
);
CREATE TABLE league_matches_footballers (
    league_match_id VARCHAR(1000000000) NOT NULL,
    footballer_id   VARCHAR(1000000000) NOT NULL,
    place           varchar(100)
);
CREATE TABLE games (
    id       int           not null,
    opponent varchar(2000) not null
);