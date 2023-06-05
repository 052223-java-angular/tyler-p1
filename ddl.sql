CREATE TABLE users (
    id TEXT PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE items (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    price DECIMAL NOT NULL,
    description TEXT NOT NULL
)