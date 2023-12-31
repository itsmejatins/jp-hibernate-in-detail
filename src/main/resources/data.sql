INSERT INTO passport(id, number) VALUES (40001, 'E123456');
INSERT INTO passport(id, number) VALUES (40002, 'N123457');
INSERT INTO passport(id, number) VALUES (40003, 'L123890');

INSERT INTO student (id, name, passport_id) VALUES (20001, 'Jatin', 40001);
INSERT INTO student (id, name, passport_id) VALUES (20002, 'Shubhi', 40002);
INSERT INTO student (id, name, passport_id) VALUES (20003, 'Honey', 40003);

INSERT INTO course ( ID,NAME,created_date,last_updated_date) VALUES (10001, 'LEARN SPRING in 100 Steps',now(),now());
INSERT INTO course ( ID,NAME,created_date,last_updated_date)  VALUES(10002, 'LEARN REACT in 100 Steps',now(),now());
INSERT INTO course ( ID,NAME,created_date,last_updated_date) VALUES(10003, 'LEARN DEEP LEARNING',now(),now());
INSERT INTO course ( ID,NAME,created_date,last_updated_date) VALUES(10004, 'LEARN NLP',now(),now());
INSERT INTO course ( ID,NAME,created_date,last_updated_date) VALUES(10005, 'LEARN COMPUTER VISION',now(),now());

INSERT INTO review(id, rating, description, course_id) values(50001, '5', 'Great course', 10001);
INSERT INTO review(id, rating, description, course_id) values(50002, '4', 'Wonderful course', 10001);
INSERT INTO review(id, rating, description, course_id) values(50003, '5', 'Awesome course', 10003);
INSERT INTO review(id, rating, description, course_id) values(50004, '4', 'Good course', 10003);
INSERT INTO review(id, rating, description, course_id) values(50005, '1', 'Too fast paced', 10003);
INSERT INTO review(id, rating, description, course_id) values(50006, '2', 'Not good', 10003);
INSERT INTO review(id, rating, description, course_id) values(50007, '5', 'Exciting course', 10005);
INSERT INTO review(id, rating, description, course_id) values(50008, '5', 'My vision enchanced', 10005);
