create table role
(
    id        int auto_increment
        primary key,
    role_name varchar(255) not null
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(65) not null,
    password varchar(64) not null,
    role_id  int         not null,
    constraint user_ibfk_1
        foreign key (role_id) references role (id)
);
