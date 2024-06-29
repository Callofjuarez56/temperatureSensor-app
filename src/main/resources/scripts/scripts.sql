CREATE TABLE sensor(
    id int PRIMARY KEY generated always as identity,
    name varchar(100) not null unique,
    created_at timestamp
);
CREATE TABLE measurements(
    id int PRIMARY KEY generated always as identity,
    value double precision not null,
    raining boolean not null,
    measurement_date_time timestamp not null,
    sensor varchar(100) references sensor(name)
);