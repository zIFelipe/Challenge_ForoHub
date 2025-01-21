create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(300) not null,
    fecha_creacion DATETIME NOT NULL,
    estado tinyint not null,
    usuario_id bigint not null,
    curso_id bigint not null,
    primary key (id),
    foreign key (usuario_id) references usuarios(id),
    foreign key (curso_id) references cursos(id)


    );