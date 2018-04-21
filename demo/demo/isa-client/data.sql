INSERT INTO role (role_id, role) VALUES ('1', 'SYSTEM_ADMIN');
INSERT INTO role (role_id, role) VALUES ('2', 'CINEMA_ADMIN');
INSERT INTO role (role_id, role) VALUES ('3', 'FAN_ZONE_ADMIN');
INSERT INTO role (role_id, role) VALUES ('4', 'USER');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('666', 'admin@admin', b'1', '$2a$10$9YI2iH.npcWAZP1.uduQ4udqtbDMwFD1QZC9mmi1oEnYlmeZ7ZJIK','imeadmina','prezimeAdmina');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('1', 'aki@aki', b'1', '$10$2nL3nm6LGoderKkWgJOcY.hUKEOCMqW32ooJ1z3eNc0yWQJzZsDGe','arsenije','degenek');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('2', 'mida@mida', b'1', '$2a$10$xyBGGrdKmPlzDijnO1Cd5uN0JyMPqOTbZ788Z01d0uyLMwMpLAHuK','milan','suvajdzic');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('3', 'joja@joja', b'1', '$2a$10$UG1/ZiGqigRytRmGC5Li9ux0JR8XAIvlQmPEfUa0bCQH8VxosgSv2','jovica','cubric');
INSERT INTO `database`.`user` (`user_id`, `city`, `email`, `enabled`, `first_name`, `last_name`, `password_hash`) VALUES ('6', 'Backi Sokolac', 'brco@brco', b'1', 'brco', 'brcic', '$2a$10$2nL3nm6LGoderKkWgJOcY.hUKEOCMqW32ooJ1z3eNc0yWQJzZsDGe');
INSERT INTO `database`.`user` (`user_id`, `city`, `email`, `enabled`, `first_name`, `last_name`, `password_hash`) VALUES ('7', 'Krivaja', 'gane@gane', b'1', 'gane', 'ganic', '$2a$10$2nL3nm6LGoderKkWgJOcY.hUKEOCMqW32ooJ1z3eNc0yWQJzZsDGe');

INSERT INTO user_role (user_id, role_id) VALUES ('666', '1');
INSERT INTO verificationtoken (id, expiryDate, token, user_id) VALUES ('99', '2018-12-12', 'token', '666');

INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('999', 'user@user', b'1', '$2a$10$2nL3nm6LGoderKkWgJOcY.hUKEOCMqW32ooJ1z3eNc0yWQJzZsDGe','imeUser','prezimeUsera');
INSERT INTO user_role (user_id, role_id) VALUES ('999', '4');
INSERT INTO verificationtoken (id, expiryDate, token, user_id) VALUES ('66', '2018-12-12', 'token2', '999');

INSERT INTO friendrequest(id,status,receiver_user_id,sender_user_id) VALUES ('11','1','999','666');


INSERT INTO `isa`.`user_role` (`user_id`, `role_id`) VALUES ('1', '3');
INSERT INTO `isa`.`user_role` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `isa`.`user_role` (`user_id`, `role_id`) VALUES ('3', '4');

INSERT INTO `isa`.`user_cinema` (`cinema_id`, `user_id`) VALUES ('1', '2');



INSERT INTO `isa`.`cinema` (`cinema_id`, `adress`, `description`, `name`) VALUES ('1', 'Marsala Tita 55, Zrenjanin', 'Najbolji bioskop u okolini, blablabla', 'Bioskop Zrenjanin');
INSERT INTO `isa`.`cinema` (`cinema_id`, `adress`, `description`, `name`) VALUES ('2', 'Bulevar Oslobodjenja 17, Novi Sad', 'Dodjite i pogledajte nas..', 'Arena Cineplex');

INSERT INTO `isa`.`theater` (`theater_id`, `adress`, `description`, `name`) VALUES ('1', 'Bulevar Mihajla Pupina 24, Novi Sad', 'Mladost ludost...', 'Pozoriste Mladih');
INSERT INTO `isa`.`theater` (`theater_id`, `adress`, `description`, `name`) VALUES ('2', 'Jevrejska 18, Novi Sad', 'Najbolje pozoriste u Vojvodini..', 'Srpsko Narodno Pozoriste');




INSERT INTO `isa`.`actor` (`actor_id`, `firstname`, `surname`) VALUES ('1', 'Aki', 'Degenek');
INSERT INTO `isa`.`actor` (`actor_id`, `firstname`, `surname`) VALUES ('2', 'Bobaz', 'Boka');
INSERT INTO `isa`.`actor` (`actor_id`, `firstname`, `surname`) VALUES ('3', 'Nikola', 'Milanko');
INSERT INTO `isa`.`actor` (`actor_id`, `firstname`, `surname`) VALUES ('4', 'Vlaki', 'Kratki');
INSERT INTO `isa`.`actor` (`actor_id`, `firstname`, `surname`) VALUES ('5', 'Janus', 'Jancic');


INSERT INTO `isa`.`director` (`director_id`, `firstname`, `surname`) VALUES ('1', 'Filip', 'Visnjic');
INSERT INTO `isa`.`director` (`director_id`, `firstname`, `surname`) VALUES ('2', 'Marko', 'Markovic');
INSERT INTO `isa`.`director` (`director_id`, `firstname`, `surname`) VALUES ('3', 'Pera', 'Peric');
INSERT INTO `isa`.`director` (`director_id`, `firstname`, `surname`) VALUES ('4', 'Nikola', 'Petrovic');


INSERT INTO `isa`.`hall` (`hall_id`, `hall_number`) VALUES ('1', '1');
INSERT INTO `isa`.`hall` (`hall_id`, `hall_number`) VALUES ('2', '2');
INSERT INTO `isa`.`hall` (`hall_id`, `hall_number`) VALUES ('3', '3');
INSERT INTO `isa`.`hall` (`hall_id`, `hall_number`) VALUES ('4', '4');


INSERT INTO `isa`.`cinema_halls` (`cinema_id`, `hall_id`) VALUES ('1', '1');
INSERT INTO `isa`.`cinema_halls` (`cinema_id`, `hall_id`) VALUES ('1', '2');
INSERT INTO `isa`.`cinema_halls` (`cinema_id`, `hall_id`) VALUES ('1', '3');
INSERT INTO `isa`.`cinema_halls` (`cinema_id`, `hall_id`) VALUES ('2', '4');



INSERT INTO `isa`.`film` (`film_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('1', '6', 'sad', '120', 'aktion', 'Fast and FIrious', '200');
INSERT INTO `isa`.`film` (`film_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('2', '5', 'asdas', '100', 'comedy', 'Dictator', '300');
INSERT INTO `isa`.`film` (`film_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('3', '4', 'dasfdsf', '90', 'drama', 'El cuerpo', '400');
INSERT INTO `isa`.`film` (`film_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('4', '9', 'fgsfgsfg', '200', 'fantazija', 'Gospodar prstenova', '500');




INSERT INTO `isa`.`projection` (`projection_id`, `date`, `discount`, `time`, `film_id`) VALUES ('1', '22-04-2018', '10', '20:00', '1');
INSERT INTO `isa`.`projection` (`projection_id`, `date`, `discount`, `time`, `film_id`) VALUES ('2', '18-05-2018', '20', '21:00', '2');
INSERT INTO `isa`.`projection` (`projection_id`, `date`, `discount`, `time`, `film_id`) VALUES ('3', '20-05-2018', '30', '19:00', '3');
INSERT INTO `isa`.`projection` (`projection_id`, `date`, `discount`, `time`, `film_id`) VALUES ('4', '19-04-2018', '5', '18:00', '4');


INSERT INTO `isa`.`hall_projections` (`hall_id`, `projection_id`) VALUES ('1', '1');
INSERT INTO `isa`.`hall_projections` (`hall_id`, `projection_id`) VALUES ('2', '2');
INSERT INTO `isa`.`hall_projections` (`hall_id`, `projection_id`) VALUES ('3', '3');
INSERT INTO `isa`.`hall_projections` (`hall_id`, `projection_id`) VALUES ('4', '4');


INSERT INTO `isa`.`stage` (`stage_id`, `stage_number`) VALUES ('1', '1');
INSERT INTO `isa`.`stage` (`stage_id`, `stage_number`) VALUES ('2', '2');
INSERT INTO `isa`.`stage` (`stage_id`, `stage_number`) VALUES ('3', '3');
INSERT INTO `isa`.`stage` (`stage_id`, `stage_number`) VALUES ('4', '4');


INSERT INTO `isa`.`performance` (`performance_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('1', '6', 'smesno ....', '90', 'COMEDY', 'Cikaske perverzije', '300');
INSERT INTO `isa`.`performance` (`performance_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('2', '7', 'ljubavna prica dvoje mladih koji se vole', '120', 'ROMANCE', 'Romeo i Julija', '350');
INSERT INTO `isa`.`performance` (`performance_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('3', '8', ' bla bla bla', '70', 'TRAGEDY', 'Hamlet', '400');
INSERT INTO `isa`.`performance` (`performance_id`, `averageRating`, `description`, `duration`, `genre`, `name`, `price`) VALUES ('4', '9', 'istripovana gospodja. ..', '100', 'COMEDY', 'Gospodja ministarka', '250');


INSERT INTO `isa`.`presentation` (`presentation_id`, `date`, `discount`, `time`, `performance_id`) VALUES ('1', '24-05-2018', '5', '19:00', '1');
INSERT INTO `isa`.`presentation` (`presentation_id`, `date`, `discount`, `time`, `performance_id`) VALUES ('2', '25-04-2018', '10', '20:00', '2');
INSERT INTO `isa`.`presentation` (`presentation_id`, `date`, `discount`, `time`, `performance_id`) VALUES ('3', '10-05-2018', '0', '18:00', '3');
INSERT INTO `isa`.`presentation` (`presentation_id`, `date`, `discount`, `time`, `performance_id`) VALUES ('4', '17-06-2018', '15', '21:00', '4');

INSERT INTO `isa`.`stage_presentations` (`stage_id`, `presentation_id`) VALUES ('1', '1');
INSERT INTO `isa`.`stage_presentations` (`stage_id`, `presentation_id`) VALUES ('2', '2');
INSERT INTO `isa`.`stage_presentations` (`stage_id`, `presentation_id`) VALUES ('3', '3');
INSERT INTO `isa`.`stage_presentations` (`stage_id`, `presentation_id`) VALUES ('4', '4');


INSERT INTO `isa`.`theater_stages` (`theater_id`, `stage_id`) VALUES ('1', '1');
INSERT INTO `isa`.`theater_stages` (`theater_id`, `stage_id`) VALUES ('1', '2');
INSERT INTO `isa`.`theater_stages` (`theater_id`, `stage_id`) VALUES ('1', '3');
INSERT INTO `isa`.`theater_stages` (`theater_id`, `stage_id`) VALUES ('2', '4');




INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('1', '1');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('2', '2');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('3', '3');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('4', '4');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('5', '5');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('6', '6');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('7', '7');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('8', '8');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('9', '9');
INSERT INTO `isa`.`seat` (`seat_id`, `seat_number`) VALUES ('10', '10');



INSERT INTO `isa`.`hall_seats` (`hall_id`, `seat_id`) VALUES ('1', '1');
INSERT INTO `isa`.`hall_seats` (`hall_id`, `seat_id`) VALUES ('1', '2');
INSERT INTO `isa`.`hall_seats` (`hall_id`, `seat_id`) VALUES ('1', '3');
INSERT INTO `isa`.`hall_seats` (`hall_id`, `seat_id`) VALUES ('1', '4');
INSERT INTO `isa`.`hall_seats` (`hall_id`, `seat_id`) VALUES ('1', '5');
INSERT INTO `isa`.`hall_seats` (`hall_id`, `seat_id`) VALUES ('1', '6');



INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('1', '1');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('2', '2');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('3', '3');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('4', '4');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('5', '5');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('6', '6');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('7', '7');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('8', '8');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('9', '9');
INSERT INTO `isa`.`chair` (`chair_id`, `chair_number`) VALUES ('10', '10');


INSERT INTO `isa`.`stage_chairs` (`stage_id`, `chair_id`) VALUES ('1', '1');
INSERT INTO `isa`.`stage_chairs` (`stage_id`, `chair_id`) VALUES ('1', '2');
INSERT INTO `isa`.`stage_chairs` (`stage_id`, `chair_id`) VALUES ('1', '3');
INSERT INTO `isa`.`stage_chairs` (`stage_id`, `chair_id`) VALUES ('1', '4');
INSERT INTO `isa`.`stage_chairs` (`stage_id`, `chair_id`) VALUES ('2', '5');
INSERT INTO `isa`.`stage_chairs` (`stage_id`, `chair_id`) VALUES ('2', '6');
INSERT INTO `isa`.`stage_chairs` (`stage_id`, `chair_id`) VALUES ('2', '7');


INSERT INTO `isa`.`ticket` (`ticket_id`, `forFastReservation`, `price`, `reserved`, `seat_id`) VALUES ('1', b'0', '200', b'0', '1');
INSERT INTO `isa`.`ticket` (`ticket_id`, `forFastReservation`, `price`, `reserved`, `seat_id`) VALUES ('2', b'1', '300', b'0', '2');

INSERT INTO `isa`.`projection_tickets` (`projection_id`, `ticket_id`) VALUES ('1', '1');
INSERT INTO `isa`.`projection_tickets` (`projection_id`, `ticket_id`) VALUES ('1', '2');
