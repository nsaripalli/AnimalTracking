-- insert
#Additionally use DataGrip to map all of the species in the species.csv into the table
insert into species (taxon, common_name, scientific_name)
values ('small bird', 'red bird', 'cardinal'),
       ('big bird', 'yellow bird', 'fromsesame');

insert into observer (name, email, phone, scientist)
values ('ethan', '17watsoeth@gmail.com', '111-111-1111', true),
       ('tanner', 'tanner@gmail.com', '222-222-2222', true),
       ('nithin', 'nithin@yahoo.com', '333-333-3333', true),
       ('jeffrey', 'jeff@gmail.com', '444-444-4444', true),
       ('rebeccaworker', 'guy@rebeccas.com', '555-555-5555', false);

insert into watch (latitude, longitude, radius, start_date, end_date, species_id, scientist_id)
values (10.0, 10.0, 1, '2018-12-01 10:00:00', '2018-12-05 10:00:00', 1, 1) -- ethan creates a watch for small bird.
;


insert into sighting (latitude, longitude, notes, species_id, observer_id, photo, watch_id)
values (1, 1, 'Rachlin did not find whales', 1, 1, null, 1);