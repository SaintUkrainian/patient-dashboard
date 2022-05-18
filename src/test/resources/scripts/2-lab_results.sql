CREATE TABLE lab_results
(
  results_id      serial,
  patient_id      bigint not null,
  lab_date        timestamp not null,
  albumin         real,
  fluid           real,
  calcium         real,
  blood_flow_rate real,
  hemoglobin      real,
  PRIMARY KEY (results_id),
  CONSTRAINT fk_patient_id
    FOREIGN KEY (patient_id)
      REFERENCES patient (patient_id)
);

INSERT INTO lab_results (patient_id, albumin, fluid, calcium, blood_flow_rate, hemoglobin, lab_date)
VALUES (1, 23.3, 12, 6, 56, 34.4, '2010-10-19 10:23:54'),
       (1, 36.3, 10, 7, 5, 3.4, '2010-10-21 10:50:54'),
       (1, 33.3, 18, 9, 6, 4.4, '2010-10-23 10:12:54'),
       (2, 43.3, 1, 32, 7, 24.4, '2012-10-9 10:26:54'),
       (2, 23.3, 21, 10, 56, 34.4, '2012-10-19 10:23:54'),
       (3, 23.3, 21, 10, 56, 34.4, '2014-10-19 10:23:54'),
       (4, 23.3, 21, 10, 56, 34.4, '2015-10-19 10:23:54'),
       (5, 23.3, 21, 10, 56, 34.4, '2020-10-19 10:23:54');
