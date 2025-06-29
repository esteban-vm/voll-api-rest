alter table doctors add active tinyint not null;
update doctors set active = 1;
