CREATE SCHEMA IF NOT EXISTS hotels_schema;

CREATE TABLE hotels (
    id uuid  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ad_title VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    distance_from_center DOUBLE PRECISION,
    rating INTEGER CHECK (rating >= 1 AND rating <= 5),
    rating_count INTEGER DEFAULT 0
);

CREATE TABLE rooms (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    number INT UNIQUE NOT NULL,
    price NUMERIC(19, 2),
    max_people INT,
    hotel_id UUID NOT NULL,
    CONSTRAINT fk_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);

CREATE TABLE room_unavailable_dates (
    room_id UUID NOT NULL,
    unavailable_date DATE NOT NULL,
    CONSTRAINT pk_room_unavailable_dates PRIMARY KEY (room_id, unavailable_date),
    CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    role VARCHAR(20) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
);

CREATE TABLE booking (
    id UUID PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    room_id UUID NOT NULL,
    user_id UUID NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

