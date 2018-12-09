DROP SCHEMA IF EXISTS tracker;

CREATE SCHEMA tracker;

USE tracker;

CREATE TABLE IF NOT EXISTS species
(
  species_id      INT PRIMARY KEY AUTO_INCREMENT,
  taxon           VARCHAR(100) NOT NULL,
  common_name     VARCHAR(250),
  scientific_name VARCHAR(250) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS observer
(
  observer_id int PRIMARY KEY AUTO_INCREMENT,
  name        varchar(150) NOT NULL,
  email       varchar(150) NOT NULL,
  phone       varchar(20),
  scientist   boolean DEFAULT FALSE
);



CREATE TABLE IF NOT EXISTS watch
(
  watch_id     int PRIMARY KEY AUTO_INCREMENT,
  latitude     double   NOT NULL,
  longitude    double   NOT NULL,
  radius       double   NOT NULL DEFAULT 1.0,
  start_date   datetime NOT NULL DEFAULT now(),
  end_date     datetime,
  species_id   int      NOT NULL,
  scientist_id int      NOT NULL,
  FOREIGN KEY (species_id) REFERENCES species (species_id),
  FOREIGN KEY (scientist_id) REFERENCES observer (observer_id)
);



CREATE TABLE IF NOT EXISTS sighting
(
  sighting_id int PRIMARY KEY AUTO_INCREMENT,
  quantity    int      NOT NULL DEFAULT 1,
  latitude    double   NOT NULL,
  longitude   double   NOT NULL,
  notes       varchar(500),
  species_id  int      NOT NULL,
  observer_id int      NOT NULL,
  photo       BLOB,
  watch_id    int,
  date        datetime NOT NULL DEFAULT NOW(),
  FOREIGN KEY (species_id) REFERENCES species (species_id),
  FOREIGN KEY (observer_id) REFERENCES observer (observer_id),
  FOREIGN KEY (watch_id) REFERENCES watch (watch_id)
);
