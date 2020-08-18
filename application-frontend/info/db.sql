        
CREATE TABLE IF NOT EXISTS users (
    id serial NOT NULL PRIMARY KEY,
    username text UNIQUE NOT NULL,
    email text UNIQUE NOT NULL,
    password text NOT NULL,
    resetcount int NOT NULL,
    points int NOT NULL
);

CREATE TABLE IF NOT EXISTS lessons (
    id serial NOT NULL PRIMARY KEY,
    title text NOT NULL,
    description text,
    addedBy int NOT NULL REFERENCES users(id),
    comments json
);
-- COMMENTS
-- timestamp
-- username
-- message

CREATE TYPE rating AS ENUM ('like', 'dislike');

CREATE TABLE IF NOT EXISTS lessonsLearned (
    user int NOT NULL REFERENCES users(id),
    lesson int NOT NULL REFERENCES lessons(id),
    rating rating,
    UNIQUE (user, lesson)
);