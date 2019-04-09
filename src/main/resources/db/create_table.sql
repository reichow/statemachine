CREATE TABLE IF NOT EXISTS  my_stateful_entity (
	id int(11),
	business_id varchar(255),
	state varchar(255) not null,
	property_one varchar(255) ,
	property_two tinyint(1),
	property_three int(11),
	PRIMARY KEY(id),
	UNIQUE(business_id)
);

CREATE TABLE IF NOT EXISTS  PROPOSTA (
	ID_PROPOSTA int(11),
	ESTADO varchar(255) not null,
	PROP1 varchar(255),
	PROP2 varchar(255),
	PRIMARY KEY(ID_PROPOSTA),
	UNIQUE(ID_PROPOSTA)
);

ALTER TABLE PROPOSTA MODIFY COLUMN ID_PROPOSTA INT auto_increment;
