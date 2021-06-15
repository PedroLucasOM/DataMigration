DROP TABLE IF EXISTS person;
CREATE TABLE person
(
    id         INT,
    name       VARCHAR(500),
    email      VARCHAR(500),
    birth_date DATETIME,
    age        INT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS bank;
CREATE TABLE bank
(
    id        INT,
    person_id INT,
    agency    INT,
    account   INT,
    bank      INT,
    PRIMARY KEY (id)
);
