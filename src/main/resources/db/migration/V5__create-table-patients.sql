create table patients(

    id bigint not null auto_increment,
    active tinyint not null,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(20) not null,
    document varchar(14) not null unique,
    street varchar(100) not null,
    neighbor varchar(100) not null,
    postal_code varchar(12) not null,
    complement varchar(100),
    number varchar(20),
    state char(100) not null,
    city varchar(100) not null,

    primary key(id)

);
