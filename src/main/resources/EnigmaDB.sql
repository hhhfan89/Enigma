create table USERS (
	id integer auto_increment primary key,
	imei char(20) not null,
	mac char(20) not null,
	device_name char(100) not null,
	lives int(5),
	unique(imei, mac, device_name)
) engine=InnoDB;

create table QUESTIONS (
	id integer auto_increment primary key,
	question text not null,
	solution text not null,
	level int(2)
) engine=InnoDB;