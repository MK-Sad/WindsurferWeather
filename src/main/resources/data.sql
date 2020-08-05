DROP TABLE IF EXISTS LOCATION_ENTITY;

CREATE TABLE LOCATION_ENTITY (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    city_name VARCHAR(250) NOT NULL,
    country_code VARCHAR(250) NOT NULL,
    lon DOUBLE DEFAULT 0,
    lat DOUBLE DEFAULT 0
	);

INSERT INTO LOCATION_ENTITY (city_name, country_code) VALUES
	('Jastarnia', 'PL'),
	('Bridgetown', 'BB'),
	('Fortaleza', 'BR'),
	('Pissouri', 'CY'),
	('Le Morne', 'MQ');