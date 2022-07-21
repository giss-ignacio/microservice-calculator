
CREATE TABLE users (
                       id serial PRIMARY KEY,
                       username VARCHAR ( 50 ) UNIQUE NOT NULL,
                       password VARCHAR ( 60 ) NOT NULL,
                       email VARCHAR ( 255 ) UNIQUE NOT NULL,
                       name VARCHAR ( 255 ) NOT NULL,
                       role VARCHAR ( 50 ) NOT NULL
);


CREATE TABLE refreshtoken (
                              id serial PRIMARY KEY,
                              user_id serial,
                              token VARCHAR ( 60 ),
                              expiry_date DATE ,
                              CONSTRAINT fk_user
                                  FOREIGN KEY(user_id)
                                      REFERENCES users(id)
);

CREATE TABLE history (
                         id serial PRIMARY KEY,
                         request TEXT,
                         response TEXT,
                         endpoint VARCHAR ( 50 )
);