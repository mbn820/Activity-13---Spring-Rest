CREATE TABLE ADDRESS (
	address_id INT NOT NULL,
	street_number INT,
	barangay VARCHAR(50),
	municipality VARCHAR(50),
	zipcode VARCHAR(4),
	PRIMARY KEY(address_id)
);
