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


Ajouter dans rôle
1 ROLE_ADMIN
2 ROLE_USER

Ajouter dans user

1,admin,$2y$12$zRcUApFsej/Ph3il3/4dN.LSDKxDFMluMorJicMwP0MRtkDhhgQJa,1
2,user,$2y$12$L3vgt7kgKMK8sTszQUkjP.zOnC2PcPK3R9znVR0UoDKl8lb9wPvGq,2
