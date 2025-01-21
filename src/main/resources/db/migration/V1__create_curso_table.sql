create table cursos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    categoria varchar(100) not null,
    activo tinyint not null,

    primary key (id)
    );