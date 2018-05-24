CREATE TABLE PERSON (
	id INT NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	middle_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	suffix VARCHAR(10),
	title VARCHAR(10),
	address_id INT NOT NULL,
	birth_date DATE NOT NULL,
	date_hired DATE NOT NULL,
	currently_employed BOOLEAN,
	gwa DOUBLE PRECISION,
	PRIMARY KEY(id),
	FOREIGN KEY (address_id) REFERENCES address (address_id),
	UNIQUE (address_id)
);
