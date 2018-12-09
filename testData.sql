USE tracker;

INSERT INTO species (taxon, common_name, scientific_name)
VALUES ('small bird', 'red bird', 'cardinal'),
       ('big bird', 'yellow bird', 'fromsesame');

INSERT INTO observer (name, email, phone, scientist)
VALUES ('ethan', '17watsoeth@gmail.com', '111-111-1111', TRUE),
       ('tanner', 'tanner@gmail.com', '222-222-2222', TRUE),
       ('nithin', 'nithin@yahoo.com', '333-333-3333', TRUE),
       ('jeffrey', 'jeff@gmail.com', '444-444-4444', TRUE),
       ('rebeccaworker', 'guy@rebeccas.com', '555-555-5555', FALSE);

INSERT INTO watch (latitude, longitude, radius, start_date, end_date, species_id, scientist_id)
VALUES (10.0, 10.0, 1, '2018-12-01 10:00:00', '2018-12-05 10:00:00', 1, 1);
/*ethan creates a watch for small bird.*/


INSERT INTO sighting (latitude, longitude, notes, species_id, observer_id, photo, watch_id)
VALUES (1, 1, 'Rachlin did not find whales', 1, 1, NULL, 1);
