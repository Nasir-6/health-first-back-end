CREATE TABLE patients (
    id SERIAL PRIMARY KEY,
    patient_name character varying(255),
    phone_number character varying(255),
    email_address character varying(255),
    blood_type character varying(255)
);


INSERT INTO patients (patient_name, phone_number, email_address, blood_type)
 VALUES ('Suraya', '123456', 'Suraya@gmail.com', 'A');
INSERT INTO patients (patient_name, phone_number, email_address, blood_type)
 VALUES ('Hajr', '123456', 'Hajr@gmail.com', 'B');
INSERT INTO patients (patient_name, phone_number, email_address, blood_type)
 VALUES ('Michael', '123456', 'Michael@gmail.com', 'O');
INSERT INTO patients (patient_name, phone_number, email_address, blood_type)
 VALUES ('Aoife', '123456', 'Aoife@gmail.com', 'AB');
INSERT INTO patients (patient_name, phone_number, email_address, blood_type)
 VALUES ('Jake', '123456', 'Jake@gmail.com', 'A');
 

CREATE TABLE doctors (
    id SERIAL PRIMARY KEY,
    doctor_name character varying(255),
    room_name character varying(255)
);

 INSERT INTO doctors(doctor_name, room_name)
 VALUES ('Dr Darwin','Room 1');
 
 INSERT INTO doctors(doctor_name, room_name)
 VALUES ('Dr Maryland','Room 2');

 INSERT INTO doctors(doctor_name, room_name)
 VALUES ('Dr Franks','Room 3');



CREATE TABLE appointments (
    appointment_id SERIAL PRIMARY KEY,
    patient_id integer REFERENCES patients(id),
    doctor_id integer REFERENCES doctors(id),
    appointment_date character varying(255),
    appointment_time character varying(255)
);

 INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time)
  VALUES (1, 1, '1997-10-09', '11:23:00');
  
  INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time)
  VALUES (2, 1, '2020-10-09', '11:23:00');
  
   
  INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time)
  VALUES (5, 2, '2021-10-09', '10:00:00');

  INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time)
  VALUES (4, 2,'2019-10-09', '11:23:00');
  

