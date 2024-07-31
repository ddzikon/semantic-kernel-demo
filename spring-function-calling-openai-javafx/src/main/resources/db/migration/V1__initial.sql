CREATE SCHEMA IF NOT EXISTS test_schema;

CREATE TABLE IF NOT EXISTS test_schema.person(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name CHARACTER VARYING,
    info CHARACTER VARYING
);

CREATE TABLE IF NOT EXISTS test_schema.chat_entry(
    id INT AUTO_INCREMENT PRIMARY KEY,
    entry CHARACTER VARYING,
    role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS test_schema.audio_chat_entry(
    id INT AUTO_INCREMENT PRIMARY KEY,
    entry CHARACTER VARYING
);

INSERT INTO test_schema.person(name, info)
VALUES ('Areczek', 'Stażysta');

INSERT INTO test_schema.person(name, info)
VALUES ('Miro', 'Handlarz');
