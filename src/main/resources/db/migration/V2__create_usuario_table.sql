create table usuarios(
    id bigint not null auto_increment,
    login varchar(100) not null,
    email varchar(100) not null unique,
    clave varchar(100) not null,
    activo tinyint not null,

    primary key (id)
    );