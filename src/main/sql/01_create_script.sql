CREATE TABLE contact(
   contact_id SERIAL PRIMARY KEY,
   firstname VARCHAR (50) NOT NULL,
	lastname VARCHAR (50) NOT NULL
);


CREATE TABLE phonenumber(
   phonenumber_id SERIAL PRIMARY KEY,
	phonenumber VARCHAR (50),
   number_type int4 NOT NULL,
	contact_id INT NOT NULL,
	CONSTRAINT contact_id
      FOREIGN KEY(contact_id)
	  REFERENCES contact(contact_id)
		ON DELETE CASCADE
);