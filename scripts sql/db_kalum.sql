-- 16/05/2020
-- CREADO POR CARLOS PAZ
-- KINAL 2020
-- FULL STACK JAVA
------------------------------------------------------------------
-- CREACIÓN DE LA BASE DE DATOS PARA EL PROYECTO FINAL DE BACKEND
------------------------------------------------------------------
create database kalum;
use kalum;
------------------------------------------------------------------
-- CREACIÓN DE LAS TABLAS
------------------------------------------------------------------
create table carrera_tecnica(
codigo_carrera varchar(128) primary key not null,
nombre varchar(255) not null
)engine = innodb;

insert into carrera_tecnica values(uuid(),'DESARROLLO DE APLICACIONES FULL STACK CON JAVA');
insert into carrera_tecnica values(uuid(),'DESARROLLO DE APLICACIONES FULL STACK CON .NET');
insert into carrera_tecnica values(uuid(),'INSTALACIONES ELÉCTRICAS GENERALES');
insert into carrera_tecnica values(uuid(),'CONTROLES Y MÁQUINAS ELÉCTRICAS');
insert into carrera_tecnica values(uuid(),'DISPOSITIVOS ELECTRÓNICOS ANALÓGICOS');



------------------------------------------------------------------
create table instructor(
instructor_id varchar(128) primary key not null,
apellidos varchar(255) not null,
nombres varchar(255) not null,
direccion varchar(255) not null,
telefono varchar(255) not null,
comentario varchar(255) not null,
estatus varchar(255) not null,
foto varchar(255)
)engine = innodb;

insert into instructor values(uuid(),'Tumax','Edwin','Guatemala, Guatemala','1111-1111','Instructor de Informática','ALTA',null);
insert into instructor values(uuid(),'Jobs','Steve','Guatemala, Guatemala','2222-2222','Instructor de Informática','ALTA',null);
insert into instructor values(uuid(),'Einstein','Albert','Guatemala, Guatemala','3333-3333','Instructor de Electricidad','ALTA',null);
insert into instructor values(uuid(),'Newton','Isaac','Guatemala, Guatemala','4444-4444','Instructor de Física','ALTA',null);
insert into instructor values(uuid(),'Galilei','Galileo','Guatemala, Guatemala','5555-5555','Instructor de Mecánica','ALTA',null);
------------------------------------------------------------------
create table salon(
salon_id varchar(128) primary key not null,
nombre_salon varchar(255) not null,
descripcion varchar(255) not null,
capacidad int(11) not null
)engine = innodb;

insert into salon values
	(uuid(),'C28','Salón de computación 1',30)
    ,(uuid(),'C29','Salón de computación 2',30)
    ,(uuid(),'C30','Salón de electricidad',25)
    ,(uuid(),'C31','Salón de máquinas eléctricas',20)
    ,(uuid(),'C32','Salón de electrónica',20)
;
------------------------------------------------------------------
create table horario(
horario_id varchar(128) primary key not null,
horario_inicio time not null,
horario_final time not null
)engine = innodb;

insert into horario values
    (uuid(),'08:00:00','09:00:00')
    ,(uuid(),'09:00:00','10:00:00')
    ,(uuid(),'10:00:00','11:00:00')
    ,(uuid(),'11:00:00','12:00:00')
    ,(uuid(),'13:00:00','17:30:00')
;
------------------------------------------------------------------
create table alumno(
carne varchar(16) primary key not null,
no_expediente varchar(64) not null,
apellidos varchar(128) not null,
nombres varchar(128) not null,
email varchar(32) not null
)engine = innodb;

insert into alumno values(202001,'EXP-001','Paz Hernández','Carlos Estuardo','charlypaz321@gmail.com');
insert into alumno values(202002,'EXP-001','García Soto','Juan Carlos','jcgarcias@gmail.com');
insert into alumno values(202003,'EXP-001','Tejeda Santos','José Antonio','jtejeda@gmail.com');
------------------------------------------------------------------
create table modulo(
modulo_id varchar(128) primary key not null,
nombre_modulo varchar(128) not null,
carrera_id varchar(128) not null,
numero_seminarios int(11) not null,
constraint fk_carrera foreign key(carrera_id) references carrera_tecnica(codigo_carrera)
)engine = innodb;



------------------------------------------------------------------
create table seminario(
seminario_id varchar(128) primary key not null,
nombre_seminario varchar(128) not null,
fecha_inicio datetime not null,
fecha_fin datetime not null,
modulo_id varchar(128) not null,
constraint fk_modulo foreign key(modulo_id) references modulo(modulo_id)
)engine = innodb;



------------------------------------------------------------------
create table detalle_actividad(
detalle_actividad_id varchar(128) primary key not null,
nombre_actividad varchar(128) not null,
nota_actividad varchar(128) not null,
fecha_creacion datetime not null,
fecha_entrega datetime not null,
fecha_postergacion datetime not null,
estado varchar(1) not null,
seminario_id varchar(128) not null,
constraint fk_seminario foreign key(seminario_id) references seminario(seminario_id)
)engine = innodb;



------------------------------------------------------------------
create table detalle_nota(
detalle_nota_id varchar(128) primary key not null,
valor_nota int(11) not null,
detalle_actividad_id varchar(128) not null,
carne varchar(16),
constraint fk_detalle_actividad foreign key(detalle_actividad_id) references detalle_actividad(detalle_actividad_id),
constraint fk_carne foreign key(carne) references alumno(carne)
)engine = innodb;



------------------------------------------------------------------
create table clase(
clase_id varchar(128) primary key not null,
descripcion varchar(255) not null,
ciclo int(11) not null,
cupo_minimo int(11) not null,
cupo_maximo int(11) not null,
salon_id varchar(128) not null,
horario_id varchar(128) not null,
instructor_id varchar(128) not null,
carrera_id varchar(128) not null,
cantidad_asignaciones int default 0,
constraint fk_salon foreign key(salon_id) references salon(salon_id),
constraint fk_horario foreign key(horario_id) references horario(horario_id),
constraint fk_instructor foreign key(instructor_id) references instructor(instructor_id),
constraint fk_carrera_id foreign key(carrera_id) references carrera_tecnica(codigo_carrera)
)engine = innodb;

-- CLASE 1
insert into clase
values(uuid(),'Desarrollo de software basado en tecnología JAVA',2020,10,30
		,(select salon_id from salon where nombre_salon = 'C28')
		,(select horario_id from horario where horario_inicio = '13:00:00'),(select instructor_id from instructor where apellidos = 'Tumax')
		,(select codigo_carrera from carrera_tecnica where nombre = 'DESARROLLO DE APLICACIONES FULL STACK CON JAVA'),0);

-- CLASE 2
insert into clase
values(uuid(),'Desarrollo de software basado en tecnología .NET',2020,10,30
		,(select salon_id from salon where nombre_salon = 'C29')
		,(select horario_id from horario where horario_inicio = '08:00:00'),(select instructor_id from instructor where apellidos = 'Jobs')
		,(select codigo_carrera from carrera_tecnica where nombre = 'DESARROLLO DE APLICACIONES FULL STACK CON .NET'),0);

-- CLASE 3
insert into clase
values(uuid(),'Electricidad General',2020,10,20
		,(select salon_id from salon where nombre_salon = 'C30')
		,(select horario_id from horario where horario_inicio = '09:00:00'),(select instructor_id from instructor where apellidos = 'Galilei')
		,(select codigo_carrera from carrera_tecnica where nombre = 'INSTALACIONES ELÉCTRICAS GENERALES'),0);

-- CLASE 4
insert into clase
values(uuid(),'Control de máquinas en general',2020,10,20
		,(select salon_id from salon where nombre_salon = 'C31')
		,(select horario_id from horario where horario_inicio = '10:00:00'),(select instructor_id from instructor where apellidos = 'Einstein')
		,(select codigo_carrera from carrera_tecnica where nombre = 'CONTROLES Y MÁQUINAS ELÉCTRICAS'),0);

-- CLASE 5
insert into clase
values(uuid(),'Dispositivos electrónicos',2020,10,20
		,(select salon_id from salon where nombre_salon = 'C32')
		,(select horario_id from horario where horario_inicio = '11:00:00'),(select instructor_id from instructor where apellidos = 'Newton')
		,(select codigo_carrera from carrera_tecnica where nombre = 'DISPOSITIVOS ELECTRÓNICOS ANALÓGICOS'),0);
------------------------------------------------------------------
create table asignacion_alumno(
asignacion_id varchar(128) primary key not null,
fecha_asignacion datetime not null,
clase_id varchar(128) not null,
carne varchar(16) not null,
constraint fk_clase foreign key(clase_id) references clase(clase_id),
constraint fk_carne_id foreign key(carne) references alumno(carne)
)engine = innodb;



------------------------------------------------------------------
-- CREACIÓN DE TRIGGER
------------------------------------------------------------------
delimiter //
create trigger tg_actualizarAsignacionesAgregar after insert on asignacion_alumno
for each row
begin
	declare asignaciones int default 0;
    set asignaciones=(select cantidad_asignaciones from clase where clase_id = new.clase_id);
	set asignaciones=asignaciones+1;
    update clase set cantidad_asignaciones = asignaciones where clase_id = new.clase_id;
end
//
------------------------------------------------------------------
-- CREACIÓN DE TRIGGER
------------------------------------------------------------------
delimiter //
create trigger tg_actualizarAsignacionesEliminar after delete on asignacion_alumno
for each row
begin
	declare asignaciones int default 0;
    set asignaciones=(select cantidad_asignaciones from clase where clase_id = old.clase_id);
	set asignaciones=asignaciones-1;
    update clase set cantidad_asignaciones = asignaciones where clase_id = old.clase_id;
end
//

-- delete from asignacion_alumno where asignacion_id = '93b9464e-77b6-11ea-9141-00ffcfd80124';
------------------------------------------------------------------




