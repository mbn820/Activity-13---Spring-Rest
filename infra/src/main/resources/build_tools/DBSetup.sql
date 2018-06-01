CREATE TABLE PERSON (
	id INT NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	middle_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	suffix VARCHAR(10),
	title VARCHAR(10),
	street_number INT,
	barangay VARCHAR(50),
	municipality VARCHAR(50),
	zipcode VARCHAR(4),
	birth_date DATE NOT NULL,
	date_hired DATE NOT NULL,
	currently_employed BOOLEAN,
	gwa DOUBLE PRECISION,
	PRIMARY KEY(id)
);

CREATE TABLE CONTACT (
	id INT NOT NULL,
	type VARCHAR(10) NOT NULL,
	detail VARCHAR(20) NOT NULL,
	person_id INT,
	FOREIGN KEY(person_id) references PERSON(id) ON DELETE CASCADE,
	PRIMARY KEY(id)
);

CREATE TABLE ROLE (
	id INT NOT NULL,
	role_name VARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE PERSON_ROLE(
	person_id INT NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY(person_id, role_id)
);

CREATE TABLE USERS (
	id INT NOT NULL,
	user_name VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	user_role VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
);
