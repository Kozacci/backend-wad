-- PostgreSQL Script
-- 05.11.2023 14:00
-- Initialization of entire database

-- Creating schema for WaterAdventure
CREATE SCHEMA IF NOT EXISTS wateradventure;

-- Creating a sequence for all tables
CREATE SEQUENCE IF NOT EXISTS wateradventure.id_seq;

-- Table Courses
CREATE TABLE IF NOT EXISTS wateradventure.courses (
                                            id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                            type VARCHAR(30) NOT NULL,
                                            date_from DATE NOT NULL,
                                            date_to DATE NOT NULL,
                                            status VARCHAR(30) NOT NULL
);

-- Table Answers_History
CREATE TABLE IF NOT EXISTS wateradventure.answers_history (
                                                id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY
);

-- Table Participants
CREATE TABLE IF NOT EXISTS wateradventure.participants (
                                                 id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                 first_name VARCHAR(30) NOT NULL,
                                                 last_name VARCHAR(40) NOT NULL,
                                                 email VARCHAR(40) NOT NULL,
                                                 password VARCHAR(255) NOT NULL,
                                                 phone_number VARCHAR(9) NOT NULL,
                                                 answers_history_id BIGINT NOT NULL,
                                                 CONSTRAINT fk_Participants_Answers_History1 FOREIGN KEY (answers_history_id) REFERENCES wateradventure.answers_history (id)
);

-- Table Participant_Courses
CREATE TABLE IF NOT EXISTS wateradventure.participant_courses (
                                                id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                access_date TIMESTAMP NOT NULL,
                                                is_passed BOOLEAN NOT NULL,
                                                course_id BIGINT NOT NULL,
                                                participant_id BIGINT NOT NULL,
                                                CONSTRAINT fk_Accesses_Courses1 FOREIGN KEY (course_id) REFERENCES wateradventure.courses (id),
                                                CONSTRAINT fk_Accesses_Participants1 FOREIGN KEY (participant_id) REFERENCES wateradventure.participants (id)

);

-- Table Questions
CREATE TABLE IF NOT EXISTS wateradventure.questions (
                                              id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                              content TEXT NOT NULL,
                                              first_answer VARCHAR(60) NOT NULL,
                                              second_answer VARCHAR(60) NOT NULL,
                                              third_answer VARCHAR(60) NOT NULL,
                                              section VARCHAR(40) NOT NULL,
                                              correct_answer VARCHAR(60) NOT NULL,
                                              explanation TEXT NULL,
                                              image VARCHAR(40) NULL
);

-- Table Events
CREATE TABLE IF NOT EXISTS wateradventure.events (
                                           id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                           type VARCHAR(40) NOT NULL,
                                           is_paid BOOLEAN NOT NULL,
                                           cost DOUBLE PRECISION NOT NULL,
                                           date TIMESTAMP NOT NULL,
                                           address VARCHAR(50) NOT NULL,
                                           orderer_email VARCHAR(40) NOT NULL,
                                           orderer_first_name VARCHAR(30) NOT NULL,
                                           orderer_last_name VARCHAR(40) NOT NULL,
                                           orderer_phone_number VARCHAR(9) NOT NULL
);

-- Table General_learning
CREATE TABLE IF NOT EXISTS wateradventure.general_learning (
                                                     id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                     course_type VARCHAR(40) NOT NULL,
                                                     questions_answered INT NOT NULL,
                                                     correct_answers INT NOT NULL,
                                                     answers_history_id BIGINT NOT NULL,
                                                     CONSTRAINT fk_General_learning_Answers_History1 FOREIGN KEY (answers_history_id) REFERENCES wateradventure.answers_history (id)
);

-- Table Category_learning
CREATE TABLE IF NOT EXISTS wateradventure.category_learning (
                                                    id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                    course_type VARCHAR(40) NOT NULL,
                                                    questions_answered INT NOT NULL,
                                                    correct_answers INT NOT NULL,
                                                    category VARCHAR (30) NOT NULL,
                                                    answers_history_id BIGINT NOT NULL,
                                                    CONSTRAINT fk_Category_learning_Answers_History1 FOREIGN KEY (answers_history_id) REFERENCES wateradventure.answers_history (id)
);

-- Table Trial_exams
CREATE TABLE IF NOT EXISTS wateradventure.trial_exams (
                                                id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                course_type VARCHAR(40) NOT NULL,
                                                total INT NOT NULL,
                                                passed INT NOT NULL,
                                                failed INT NOT NULL,
                                                answers_history_id BIGINT NOT NULL,
                                                CONSTRAINT fk_Trial_exam_Answers_History1 FOREIGN KEY (answers_history_id) REFERENCES wateradventure.answers_history (id)
);
