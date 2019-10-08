-- Crud de Personas MySQL Codigo SQL para la creacion de la base de datos

drop database if exists escuela;
create database escuela;

use escuela;


DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar` (IN `anombre` VARCHAR(20), IN `apaterno` VARCHAR(20), IN `amaterno` VARCHAR(20), IN `agrupoid` INT, IN `aid` INT)  NO SQL
BEGIN
update alumnos 
SET nombre = anombre , apellido_paterno = apaterno ,apellido_materno = amaterno,
grupo_id = agrupoid, id=aid
WHERE id= aid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar` (IN `id` INT, IN `name` VARCHAR(20), IN `lastname` VARCHAR(20), IN `lastname2` VARCHAR(20), IN `groupid` INT)  NO SQL
BEGIN
insert into alumnos(id,nombre,apellido_paterno,apellido_materno,grupo_id) values(id,name,lastname,lastname2,groupid);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar` (IN `uid` INT)  NO SQL
BEGIN
DELETE FROM alumnos where id=uid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `todosAlumnos` (IN `uid` INT)  NO SQL
BEGIN
select a.id,a.nombre, a.apellido_paterno,a.apellido_materno,gru.nombre as Grupo,cur.nombre as Curso, car.nombre as Carrera from alumnos as a
join grupos as gru on a.grupo_id = gru.id 
join gruposcursos on gru.id = gruposcursos.grupo_id
join cursos as cur on gruposcursos.curso_id = cur.id
join carreras as car on cur.carrera_id = car.id 
where a.id = uid;
END$$

DELIMITER ;

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



INSERT INTO `carreras` (`id`, `nombre`) VALUES
(1, 'TICS'),
(3, 'Contabilidad'),
(4, 'Mecatronica');

INSERT INTO `cursos` (`id`, `nombre`, `carrera_id`) VALUES
(1, 'Cuatrimestre 1', 1),
(2, 'Cuatrimestre 2', 1),
(6, 'Cuatrimestre 1', 3),
(7, 'Cuatrimestre 2', 3),
(11, 'Cuatrimestre 1', 4),
(12, 'Cuatrimestre 2', 4);


INSERT INTO `Grupos` (`id`, `nombre`, `curso_id`) VALUES
(1, 'A', 1);




create table `GruposCursos` (
    `id` int not null auto_increment,
    `grupo_id` int not null,
    `curso_id` int not null,
    foreign key(`grupo_id`) references Grupos(`id`),
    foreign key(`curso_id`) references Cursos(`id`),
    primary key(`id`)
);

INSERT INTO `alumnos` (`id`, `nombre`, `apellido_paterno`, `apellido_materno`, `grupo_id`) VALUES
(1, 'Antonio', 'Cadena', 'Gonzalez', 1),
(2, 'Julio', 'Gastelum ', 'Martinez', 1),
(4, 'Erika', 'Montes ', 'De Oca', 1),
(5, 'Mike', 'Briones ', 'Soto', 1),
(6, 'Victor', 'Cordova ', 'Cordova', 1);


INSERT INTO `materias` (`id`, `nombre`) VALUES
(1, 'Programación'),
(2, 'Desarrollo Web'),
(3, 'Matematicas'),
(4, 'Optativa'),
(5, 'Ingles');




INSERT INTO `profesor` (`id`, `nombre`, `grado`) VALUES
(1, 'Torres Aldana Daniel', 'Lic.'),
(2, 'Laura Trejo', 'M.C'),
(3, 'Valencia Javier', 'Ing.');

INSERT INTO `cursosmaterias` (`id`, `curso_id`, `materia_id`) VALUES
(1, 1, 1);

INSERT INTO `gruposcursos` (`id`, `grupo_id`, `curso_id`) VALUES
(1, 1, 1);
INSERT INTO `materiasprofesor` (`id`, `materia_id`, `profesor_id`) VALUES
(1, 1, 1),
(2, 5, 3),
(3, 2, 2);

