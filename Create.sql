DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS PROJECT;
DROP TABLE IF EXISTS TASK;
DROP TABLE IF EXISTS PARENT_TASK;

CREATE TABLE USER (

 USER_ID INT NOT NULL AUTO_INCREMENT,
 FIRST_NAME VARCHAR(25) NOT NULL,
 LAST_NAME VARCHAR(25) NOT NULL,
 EMPLOYEE_ID INT NOT NULL,
 PROJECT_ID INT,
 TASK_ID INT,
 PRIMARY KEY ( USER_ID)

);

CREATE TABLE PROJECT (

PROJECT_ID INT NOT NULL AUTO_INCREMENT,
PROJECT_NAME VARCHAR(25) NOT NULL,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
PRIORITY INT NOT NULL,
PROJECT_STATUS VARCHAR(25),
USER_ID INT,
PRIMARY KEY ( PROJECT_ID )

);

CREATE TABLE TASK (

TASK_ID INT NOT NULL AUTO_INCREMENT,
PARENT_ID INT NOT NULL,
PROJECT_ID INT NOT NULL,
TASK VARCHAR(25),
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
PRIORITY INT NOT NULL,
STATUS VARCHAR(25),
PRIMARY KEY ( TASK_ID )

);

CREATE TABLE PARENT_TASK (

PARENT_ID INT NOT NULL AUTO_INCREMENT,
PARENT_TASK  VARCHAR(25) NOT NULL,
PRIMARY KEY ( PARENT_ID )

);create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table parent_task (parent_id integer not null, parent_task varchar(255), primary key (parent_id)) engine=InnoDB
create table project (project_id integer not null, end_date datetime, priority integer, project_name varchar(255), project_status varchar(255), start_date datetime, user_id integer, primary key (project_id)) engine=InnoDB
create table task (task_id integer not null, end_date datetime, parent_id integer, priority integer, project_id integer, start_date datetime, status varchar(255), task varchar(255), primary key (task_id)) engine=InnoDB
create table user (user_id integer not null, employee_id integer, firstname varchar(255), lastname varchar(255), project_id integer, task_id integer, primary key (user_id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table parent_task (parent_id integer not null, parent_task varchar(255), primary key (parent_id)) engine=InnoDB
create table project (project_id integer not null, end_date datetime, priority integer, project_name varchar(255), project_status varchar(255), start_date datetime, user_id integer, primary key (project_id)) engine=InnoDB
create table task (task_id integer not null, end_date datetime, parent_id integer, priority integer, project_id integer, start_date datetime, status varchar(255), task varchar(255), primary key (task_id)) engine=InnoDB
create table user (user_id integer not null, employee_id integer, firstname varchar(255), lastname varchar(255), project_id integer, task_id integer, primary key (user_id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table parent_task (parent_id integer not null, parent_task varchar(255), primary key (parent_id)) engine=InnoDB
create table project (project_id integer not null, end_date datetime, priority integer, project_name varchar(255), project_status varchar(255), start_date datetime, user_id integer, primary key (project_id)) engine=InnoDB
create table task (task_id integer not null, end_date datetime, parent_id integer, priority integer, project_id integer, start_date datetime, status varchar(255), task varchar(255), primary key (task_id)) engine=InnoDB
create table user (user_id integer not null, employee_id integer, firstname varchar(255), lastname varchar(255), project_id integer, task_id integer, primary key (user_id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table parent_task (parent_id integer not null, parent_task varchar(255), primary key (parent_id)) engine=InnoDB
create table project (project_id integer not null, end_date datetime, priority integer, project_name varchar(255), project_status varchar(255), start_date datetime, user_id integer, primary key (project_id)) engine=InnoDB
create table task (task_id integer not null, end_date datetime, parent_id integer, priority integer, project_id integer, start_date datetime, status varchar(255), task varchar(255), primary key (task_id)) engine=InnoDB
create table user (user_id integer not null auto_increment, employee_id integer, firstname varchar(255), lastname varchar(255), project_id integer, task_id integer, primary key (user_id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table parent_task (parent_id integer not null, parent_task varchar(255), primary key (parent_id)) engine=InnoDB
create table project (project_id integer not null, end_date datetime, priority integer, project_name varchar(255), project_status varchar(255), start_date datetime, user_id integer, primary key (project_id)) engine=InnoDB
create table task (task_id integer not null, end_date datetime, parent_id integer, priority integer, project_id integer, start_date datetime, status varchar(255), task varchar(255), primary key (task_id)) engine=InnoDB
create table user (user_id integer not null auto_increment, employee_id integer, firstname varchar(255), lastname varchar(255), project_id integer, task_id integer, primary key (user_id)) engine=InnoDB
