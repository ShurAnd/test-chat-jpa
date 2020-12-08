drop table if exists messages;
create table messages(
	id bigint not null AUTO_INCREMENT,
	text varchar(255),
	author varchar(100),
	PRIMARY KEY (id)
);