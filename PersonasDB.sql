-- Crud de Personas MySQL Codigo SQL para la creacion de la base de datos

create database if not exists escuela;

use escuela;

create table Personas (
    id int not null auto_increment,
    nombre varchar(40) not null,
    apellido_paterno varchar(40) not null,
    apellido_materno varchar(40)
)

create table Alumnos (
    id int not null auto_increment,
    carrera varchar(40) not null,
    grupo varchar(20)
)

create table Carreras (
    id int not null auto_increment,
    nombre varchar(40) not null
)


