create table users (
    id int primary key auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    age int not null,
    email varchar(255) not null unique,
    password varchar(255) not null
);