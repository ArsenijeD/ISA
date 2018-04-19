INSERT INTO role (role_id, role) VALUES ('1', 'SYSTEM_ADMIN');
INSERT INTO role (role_id, role) VALUES ('2', 'CINEMA_ADMIN');
INSERT INTO role (role_id, role) VALUES ('3', 'FAN_ZONE_ADMIN');
INSERT INTO role (role_id, role) VALUES ('4', 'USER');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('666', 'admin@admin', b'1', '$2a$10$9YI2iH.npcWAZP1.uduQ4udqtbDMwFD1QZC9mmi1oEnYlmeZ7ZJIK','imeadmina','prezimeAdmina');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('1', 'aki@aki', b'1', '$10$2nL3nm6LGoderKkWgJOcY.hUKEOCMqW32ooJ1z3eNc0yWQJzZsDGe','arsenije','degenek');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('2', 'mida@mida', b'1', '$2a$10$xyBGGrdKmPlzDijnO1Cd5uN0JyMPqOTbZ788Z01d0uyLMwMpLAHuK','milan','suvajdzic');
INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('3', 'joja@joja', b'1', '$2a$10$UG1/ZiGqigRytRmGC5Li9ux0JR8XAIvlQmPEfUa0bCQH8VxosgSv2','jovica','cubric');
INSERT INTO user_role (user_id, role_id) VALUES ('666', '1');
INSERT INTO verificationtoken (id, expiryDate, token, user_id) VALUES ('99', '2018-12-12', 'token', '666');

INSERT INTO user (user_id, email, enabled, password_hash,first_name,last_name) VALUES ('999', 'user@user', b'1', '$2a$10$2nL3nm6LGoderKkWgJOcY.hUKEOCMqW32ooJ1z3eNc0yWQJzZsDGe','imeUser','prezimeUsera');
INSERT INTO user_role (user_id, role_id) VALUES ('999', '4');
INSERT INTO verificationtoken (id, expiryDate, token, user_id) VALUES ('66', '2018-12-12', 'token2', '999');

INSERT INTO friendrequest(id,status,receiver_user_id,sender_user_id) VALUES ('11','1','999','666');
