CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name varchar(50) not null,
    address varchar(50),
    number varchar(50),
    complement varchar(50),
    neighborhood varchar(50),
    cep varchar(50),
    city varchar(50),
    state varchar(2),
    contact varchar(50),
    active boolean not null
);
