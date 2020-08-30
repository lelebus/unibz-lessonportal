        
CREATE TABLE IF NOT EXISTS users (
    username varchar UNIQUE NOT NULL PRIMARY KEY,
    name text NOT NULL,
    password text NOT NULL,
    resetcount int NOT NULL default 0,
    points int NOT NULL default 0
);

CREATE TABLE IF NOT EXISTS lessons (
    id serial NOT NULL PRIMARY KEY,
    title text NOT NULL,
    description text,
    addedBy varchar NOT NULL REFERENCES users(username),
    comments json
);
-- COMMENTS
-- timestamp
-- username
-- message

DO $$ BEGIN
    CREATE TYPE rating AS ENUM ('like', 'dislike');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS lessonsLearned (
    username varchar NOT NULL REFERENCES users(username),
    lesson int NOT NULL REFERENCES lessons(id),
    rating rating,
    UNIQUE (username, lesson)
);