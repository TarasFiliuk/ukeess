drop database if exists ukeessdb;
create database if not exists ukeessdb;
USE ukeessdb;

-- Employees
drop table if exists employees;
create table if not exists employees(
  id int not null auto_increment primary key ,
  employees_name varchar(255) not null ,
  emp_Active varchar(255) not null,
  emp_dpID int not null
)
ENGINE = InnoDB;
-- Departments
drop table if exists departments;
create table if not exists departments(
  id int not null auto_increment primary key ,
  department_name varchar(255) not null
)
  ENGINE = InnoDB;


INSERT  into employees values (1,'Liza','YES',5);
INSERT  into employees values (2,'Erik','YES',6);
INSERT  into employees values (3,'Don','YES',7);
INSERT  into employees values (4,'Peter','NO',8);

insert into departments values (5,'HR');
insert into departments values (6,'Tech');
insert into departments values (7,'Finance');
insert into departments values (8,'Tech');
