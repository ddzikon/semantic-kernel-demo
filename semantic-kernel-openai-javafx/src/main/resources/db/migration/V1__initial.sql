CREATE SCHEMA IF NOT EXISTS test_schema;

CREATE TABLE IF NOT EXISTS test_schema.person(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name CHARACTER VARYING,
    preferred_weather CHARACTER VARYING
);

CREATE TABLE IF NOT EXISTS test_schema.city_hotel(
    id INT AUTO_INCREMENT PRIMARY KEY,
    city_name CHARACTER VARYING,
    hotel_name CHARACTER VARYING,
    email CHARACTER VARYING
);

CREATE TABLE IF NOT EXISTS test_schema.chat_entry(
    id INT AUTO_INCREMENT PRIMARY KEY,
    entry CHARACTER VARYING,
    role VARCHAR(255)
);

INSERT INTO test_schema.person(name, preferred_weather)
VALUES ('Thomas Anderson', 'Sunny');

INSERT INTO test_schema.person(name, preferred_weather)
VALUES ('Max Rockatansky', 'Rainy');

INSERT INTO test_schema.person(name, preferred_weather)
VALUES ('Sam Hall', 'Sunny');

INSERT INTO test_schema.city_hotel(city_name, hotel_name, email)
VALUES ('Warsaw', 'Neat Hotel', 'neat@hotel.pl');

INSERT INTO test_schema.city_hotel(city_name, hotel_name, email)
VALUES ('Oslo', 'Kings Apartments', 'contact@kings.se');

INSERT INTO test_schema.city_hotel(city_name, hotel_name, email)
VALUES ('Madrid', 'City Central Hotel', 'contact@cch.es');
