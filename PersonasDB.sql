-- Crud de Personas MySQL Codigo SQL para la creacion de la base de datos

drop database if exists escuela;
create database escuela;

use escuela;

-- Creacion de tablas
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

-- Inserts de pruebas

INSERT INTO `Carreras` (`nombre`) VALUES
('TICS'),
('Contabilidad'),
('Mecatronica');

INSERT INTO `Cursos` (`nombre`, `carrera_id`) VALUES
('Cuatrimestre 1', 1),
('Cuatrimestre 2', 1),
('Cuatrimestre 1', 2),
('Cuatrimestre 2', 2),
('Cuatrimestre 1', 3),
('Cuatrimestre 2', 3);

INSERT INTO `Grupos` (`nombre`, `curso_id`) VALUES
('A', 1),
('B', 1);

INSERT INTO `Alumnos` (`nombre`, `apellido_paterno`, `apellido_materno`, `grupo_id`) VALUES
('Antonio', 'Cadena', 'Gonzalez', 1),
('Julio', 'Gastelum ', 'Martinez', 1),
('Erika', 'Montes ', 'De Oca', 1),
('Mike', 'Briones ', 'Soto', 1),
('Victor', 'Cordova ', 'Cordova', 1);

INSERT INTO `Materias` (`nombre`) VALUES
('Programación'),
('Desarrollo Web'),
('Matematicas'),
('Optativa'),
('Ingles');

INSERT INTO `Profesor` (`nombre`, `grado`) VALUES
('Torres Aldana Daniel', 'Lic.'),
('Laura Trejo', 'M.C'),
('Valencia Javier', 'Ing.');

INSERT INTO `CursosMaterias` (`curso_id`, `materia_id`) VALUES
(1, 1);

INSERT INTO `GruposCursos` (`grupo_id`, `curso_id`) VALUES
(1, 1);
INSERT INTO `MateriasProfesor` (`materia_id`, `profesor_id`) VALUES
(1, 1),
(3, 3),
(2, 2);

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar` (IN `anombre` VARCHAR(20), IN `apaterno` VARCHAR(20),
IN `amaterno` VARCHAR(20), IN `agrupoid` INT, IN `aid` INT)  NO SQL
BEGIN
update Alumnos 
SET nombre = anombre , apellido_paterno = apaterno ,apellido_materno = amaterno,
grupo_id = agrupoid, id=aid
WHERE id= aid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar` (IN `name` VARCHAR(20), IN `lastname` VARCHAR(20),
 IN `lastname2` VARCHAR(20), IN `groupid` INT)  NO SQL
BEGIN
insert into Alumnos(nombre,apellido_paterno,apellido_materno,grupo_id) values(name,lastname,lastname2,groupid);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar` (IN `aid` INT)  NO SQL
BEGIN
DELETE FROM Alumnos where id=aid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `consultaAlumno` (IN `aid` INT)  NO SQL
BEGIN
select a.id,a.nombre, a.apellido_paterno,a.apellido_materno,gru.nombre as grupo, gru.id as grupo_id, cur.id as curso_id,
 cur.nombre as curso, car.id as carrera_id, car.nombre as carrera from Alumnos as a
join Grupos as gru on a.grupo_id = gru.id 
join GruposCursos on gru.id = GruposCursos.grupo_id
join Cursos as cur on GruposCursos.curso_id = cur.id
join Carreras as car on cur.carrera_id = car.id 
where a.id = aid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `todosAlumnos` ()  NO SQL
BEGIN
select a.id,a.nombre, a.apellido_paterno,a.apellido_materno,gru.nombre as grupo, gru.id as grupo_id, cur.id as curso_id,
 cur.nombre as curso, car.id as carrera_id, car.nombre as carrera from Alumnos as a
join Grupos as gru on a.grupo_id = gru.id 
join GruposCursos on gru.id = GruposCursos.grupo_id
join Cursos as cur on GruposCursos.curso_id = cur.id
join Carreras as car on cur.carrera_id = car.id;
END$$

DELIMITER ;
