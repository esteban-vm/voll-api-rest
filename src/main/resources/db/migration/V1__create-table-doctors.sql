create table doctors(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    document varchar(12) not null unique,
    specialty varchar(100) not null,
    street varchar(100) not null,
    neighbor varchar(100) not null,
    postal_code varchar(12) not null,
    complement varchar(100),
    number varchar(20),
    state char(100) not null,
    city varchar(100) not null,

    primary key(id)

);
