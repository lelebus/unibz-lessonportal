DELETE FROM lessonslearned CASCADE;
DELETE FROM lessons CASCADE;
DELETE FROM users CASCADE;

INSERT INTO users (name, username, password) VALUES ('User', 'username', 'password');
INSERT INTO lessons (id, title, description, addedBy, comments) VALUES (13, 'title', 'description', 'username', '[{"message":"message","timestamp":"11:11:11 11-11-2011","username":"username"},{"message":"message","timestamp":"12:12:12 12-12-2012","username":"username"}]');
INSERT INTO lessonslearned (username, lesson) VALUES ('username', 13);