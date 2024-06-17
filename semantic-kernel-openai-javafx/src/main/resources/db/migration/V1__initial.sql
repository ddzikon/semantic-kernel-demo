CREATE SCHEMA IF NOT EXISTS test_schema;

CREATE TABLE test_schema.person(
    id INT AUTO_INCREMENT pRIMARY KEY,
    name VARCHAR(255),
    info VARCHAR(255)
);

CREATE TABLE test_schema.chat_entry(
    id INT AUTO_INCREMENT pRIMARY KEY,
    entry CHARACTER VARYING,
    role VARCHAR(255)
);

INSERT INTO test_schema.person(name, info)
VALUES ('Areczek', 'Zarobas');

INSERT INTO test_schema.person(name, info)
VALUES ('Miro', 'Handlarz');
