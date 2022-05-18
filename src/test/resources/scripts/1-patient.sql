CREATE TABLE patient
(
  patient_id serial,
  first_name varchar(255) not null,
  last_name  varchar(255) not null,
  date_of_birth date not null,
  address text not null,
  PRIMARY KEY (patient_id)
);

INSERT INTO patient (first_name, last_name, date_of_birth, address)
VALUES ('Denys', 'Matsenko', '11/15/2001', 'Akademika Valtera, 14'),
       ('Vlad', 'Lisnenko', '7/21/2002', 'Akademika Valtera, 14'),
       ('Max', 'Basov', '1/30/2002', 'North Saltovka, 21'),
       ('Bill', 'Clinton', '10/5/2001', 'Istorichiy muzei, 10'),
       ('Jorge', 'Bush', '9/26/2004', 'Saint Jorge, 35');
