-- DUMMY IRRELEVANT DATA FOR DATA.SQL TO PREVENT IT FROM BEING EMPTY. EMPTY SQL FILE MAKES SPRING THROW EXCEPTION -----------------------------------------------------------------------------
CREATE TABLE dummy (dumm_col INT);
---------------------------------------------------------------------------------------------------------------------------------------------------

-- SQL STATEMENTS FOR example.relationships : START -----------------------------------------------------------------------------------------------------------

INSERT INTO passport(id, number) VALUES (40001, 'E123456');
INSERT INTO passport(id, number) VALUES (40002, 'N123457');
INSERT INTO passport(id, number) VALUES (40003, 'L123890');

INSERT INTO student (id, name, passport_id) VALUES (20001, 'S1', 40001);
INSERT INTO student (id, name, passport_id) VALUES (20002, 'S2', 40002);
INSERT INTO student (id, name, passport_id) VALUES (20003, 'S3', 40003);
INSERT INTO student (id, name) VALUES (20004, 'S4');
INSERT INTO student (id, name) VALUES (20005, 'S5');

INSERT INTO course ( ID,NAME,created_date,last_updated_date, is_deleted) VALUES (10001, 'LEARN SPRING in 100 Steps',now(),now(), false);
INSERT INTO course ( ID,NAME,created_date,last_updated_date, is_deleted)  VALUES(10002, 'LEARN REACT in 100 Steps',now(),now(), false);
INSERT INTO course ( ID,NAME,created_date,last_updated_date, is_deleted) VALUES(10003, 'LEARN DEEP LEARNING',now(),now(), false);
INSERT INTO course ( ID,NAME,created_date,last_updated_date, is_deleted) VALUES(10004, 'LEARN NLP',now(),now(), false);
INSERT INTO course ( ID,NAME,created_date,last_updated_date, is_deleted) VALUES(10005, 'LEARN COMPUTER VISION',now(),now(), false);

INSERT INTO review(id, rating, description, course_id) VALUES(50001, 'FIVE', 'Great course', 10001);
INSERT INTO review(id, rating, description, course_id) VALUES(50002, 'THREE', 'Wonderful course', 10001);
INSERT INTO review(id, rating, description, course_id) VALUES(50003, 'FOUR', 'Awesome course', 10003);
INSERT INTO review(id, rating, description, course_id) VALUES(50004, 'THREE', 'Good course', 10003);
INSERT INTO review(id, rating, description, course_id) VALUES(50005, 'ONE', 'Too fast paced', 10003);
INSERT INTO review(id, rating, description, course_id) VALUES(50006, 'ONE', 'Not good', 10003);
INSERT INTO review(id, rating, description, course_id) VALUES(50007, 'FOUR', 'Exciting course', 10005);
INSERT INTO review(id, rating, description, course_id) VALUES(50008, 'FOUR', 'My vision enchanced', 10005);

INSERT INTO student_course(student_id, course_id) VALUES (20001, 10001);
INSERT INTO student_course(student_id, course_id) VALUES (20001, 10002);
INSERT INTO student_course(student_id, course_id) VALUES (20001, 10003);
INSERT INTO student_course(student_id, course_id) VALUES (20001, 10004);
INSERT INTO student_course(student_id, course_id) VALUES (20002, 10001);
INSERT INTO student_course(student_id, course_id) VALUES (20002, 10002);
INSERT INTO student_course(student_id, course_id) VALUES (20002, 10003);
INSERT INTO student_course(student_id, course_id) VALUES (20003, 10001);
INSERT INTO student_course(student_id, course_id) VALUES (20003, 10002);
INSERT INTO student_course(student_id, course_id) VALUES (20004, 10001);
INSERT INTO student_course(student_id, course_id) VALUES (20004, 10002);

-- SQL STATEMENTS FOR example.relationships : END ---------------------------------------------------------------------------------------------------------