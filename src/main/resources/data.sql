drop table if exists person;

create table person (
    person_id int auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);

insert into person (first_name, last_name) values ('A', 'K');
insert into person (first_name, last_name) values ('A', 'L');
insert into person (first_name, last_name) values ('B', 'K');
insert into person (first_name, last_name) values ('B', 'L');