INSERT INTO role (role_id, role) VALUES ('1', 'ADMIN');
INSERT INTO user (user_id, email, enabled, password_hash) VALUES ('666', 'admin@admin', b'1', '$2a$10$9YI2iH.npcWAZP1.uduQ4udqtbDMwFD1QZC9mmi1oEnYlmeZ7ZJIK');
INSERT INTO user_role (user_id, role_id) VALUES ('666', '1');
INSERT INTO verificationtoken (id, expiryDate, token, user_id) VALUES ('99', '2018-12-12', 'token', '666');

INSERT INTO role (role_id, role) VALUES ('2', 'USER');
INSERT INTO user (user_id, email, enabled, password_hash) VALUES ('999', 'user@user', b'1', '$2a$10$2nL3nm6LGoderKkWgJOcY.hUKEOCMqW32ooJ1z3eNc0yWQJzZsDGe');
INSERT INTO user_role (user_id, role_id) VALUES ('999', '2');
INSERT INTO verificationtoken (id, expiryDate, token, user_id) VALUES ('66', '2018-12-12', 'token2', '999');

INSERT INTO friendrequest(id,status,receiver_user_id,sender_user_id) VALUES ('11','1','999','666');
