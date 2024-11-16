CREATE TABLE user_tasks
(
    id         bigserial PRIMARY KEY,
    status     varchar(25)  not null,
    track_name varchar(100) not null,
    user_id    bigint references users (id)
);