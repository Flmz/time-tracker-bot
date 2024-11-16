CREATE TABLE users
(
    id            bigserial PRIMARY KEY,
    tg_id         bigint not null unique,
    tg_user_name  varchar(1000),
    first_name    varchar(50),
    sur_name      varchar(50),
    language_code varchar(10)
);