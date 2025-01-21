create table respuestas(
    id bigint not null auto_increment,
    mensaje varchar(300) not null,
    fecha_creacion DATETIME NOT NULL,
    cerrado tinyint not null,
    usuario_id bigint not null,
    topico_id bigint not null,
    primary key (id),
    foreign key (usuario_id) references usuarios(id),
    foreign key (topico_id) references topicos(id)
    );