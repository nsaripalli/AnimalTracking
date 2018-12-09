DROP SCHEMA IF EXISTS tracker;

CREATE SCHEMA tracker;

USE tracker;

CREATE TABLE IF NOT EXISTS species (
  species_id      INT PRIMARY KEY AUTO_INCREMENT,
  taxon           VARCHAR(100) NOT NULL,
  common_name     VARCHAR(250),
  scientific_name VARCHAR(250) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS observer (
  observer_id INT PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(150) NOT NULL,
  email       VARCHAR(150) NOT NULL,
  phone       VARCHAR(20),
  scientist   BOOLEAN DEFAULT FALSE
);



CREATE TABLE IF NOT EXISTS watch (
  watch_id     INT PRIMARY KEY AUTO_INCREMENT,
  latitude     DOUBLE   NOT NULL,
  longitude    DOUBLE   NOT NULL,
  radius       DOUBLE   NOT NULL DEFAULT 1.0,
  start_date   DATETIME NOT NULL DEFAULT now(),
  end_date     DATETIME,
  species_id   INT      NOT NULL,
  scientist_id INT      NOT NULL,
  FOREIGN KEY (species_id) REFERENCES species (species_id),
  FOREIGN KEY (scientist_id) REFERENCES observer (observer_id)
);



CREATE TABLE IF NOT EXISTS sighting (
  sighting_id INT PRIMARY KEY AUTO_INCREMENT,
  quantity    INT      NOT NULL DEFAULT 1,
  latitude    DOUBLE   NOT NULL,
  longitude   DOUBLE   NOT NULL,
  notes       VARCHAR(500),
  species_id  INT      NOT NULL,
  observer_id INT      NOT NULL,
  photo       BLOB,
  watch_id    INT,
  date        DATETIME NOT NULL DEFAULT NOW(),
  FOREIGN KEY (species_id) REFERENCES species (species_id),
  FOREIGN KEY (observer_id) REFERENCES observer (observer_id),
  FOREIGN KEY (watch_id) REFERENCES watch (watch_id)
);
