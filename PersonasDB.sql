-- Crud de Personas MySQL Codigo SQL para la creacion de la base de datos

drop database if exists escuela;
create database escuela;

use escuela;

create table `Carreras` (
    `id` int not null auto_increment,
    `nombre` varchar(40) not null,
    primary key(`id`)
);

create table `Profesor` (
    `id` int not null auto_increment,
    `nombre` varchar(40) not null,
    `grado` varchar(30) not null,
    primary key(`id`)
);

create table `Materias` (
    `id` int not null auto_increment,
    `nombre` varchar(40) not null,
    primary key(`id`)
);

create table `Cursos` (
    id int not null auto_increment,
    nombre varchar(40) not null,
    carrera_id int,
    constraint fk_cursoscarreras
    foreign key (carrera_id) references Carreras(id),
    primary key(`id`)
);

create table `Grupos` (
    `id` int not null auto_increment,
    `nombre` varchar(4) not null,
    `curso_id` int not null,
    primary key(`id`),
    foreign key (`curso_id`) references Cursos(`id`)
);

create table `Alumnos` (
    `id` int not null auto_increment,
    `nombre` varchar(40) not null,
    `apellido_paterno` varchar(40) not null,
    `apellido_materno` varchar(40),
    `grupo_id` int not null,
    primary key(`id`),
    foreign key (`grupo_id`) references Grupos(`id`)
);

create table `MateriasProfesor` (
    `id` int not null auto_increment,
    `materia_id` int not null,
    `profesor_id` int not null,
    primary key(`id`),
    foreign key(`profesor_id`) references Profesor(`id`),
    foreign key(`materia_id`) references Materias(`id`)
);

create table `CursosMaterias` (
    `id` int not null auto_increment,
    `curso_id` int not null,
    `materia_id` int not null,
    foreign key(`curso_id`) references Cursos(`id`),
    foreign key(`materia_id`) references Materias(`id`),
    primary key(`id`)
);

create table `GruposCursos` (
    `id` int not null auto_increment,
    `grupo_id` int not null,
    `curso_id` int not null,
    foreign key(`grupo_id`) references Grupos(`id`),
    foreign key(`curso_id`) references Cursos(`id`),
    primary key(`id`)
);
