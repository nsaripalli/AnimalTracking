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
#Additionally use DataGrip to map all of the species in the species.csv into the table
insert into species (taxon, common_name, scientific_name)
values ('small bird', 'red bird', 'cardinal'),
       ('big bird', 'yellow bird', 'fromsesame');

CREATE TABLE IF NOT EXISTS observer
(
  observer_id int PRIMARY KEY AUTO_INCREMENT,
  name        varchar(150) NOT NULL,
  email       varchar(150) NOT NULL,
  phone       varchar(20),
  scientist   boolean DEFAULT FALSE
);

insert into observer (name, email, phone, scientist)
values ('ethan', '17watsoeth@gmail.com', '111-111-1111', true),
       ('tanner', 'tanner@gmail.com', '222-222-2222', true),
       ('nithin', 'nithin@yahoo.com', '333-333-3333', true),
       ('jeffrey', 'jeff@gmail.com', '444-444-4444', true),
       ('rebeccaworker', 'guy@rebeccas.com', '555-555-5555', false);


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

insert into watch (latitude, longitude, radius, start_date, end_date, species_id, scientist_id)
values (10.0, 10.0, 1, '2018-12-01 10:00:00', '2018-12-05 10:00:00', 1, 1) -- ethan creates a watch for small bird.
;


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

insert into sighting (latitude, longitude, notes, species_id, observer_id, photo, watch_id)
values (1, 1, 'Rachlin did not find whales', 1, 1, null, 1);