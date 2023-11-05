-- PostgreSQL Script
-- 05.11.2023 14:00
-- Initialization of entire database

-- Creating schema for WaterAdventure
CREATE SCHEMA IF NOT EXISTS wateradventure;

-- Creating a sequence for all tables
CREATE SEQUENCE wateradventure.id_seq;

-- Table Courses
CREATE TABLE IF NOT EXISTS wateradventure.courses (
                                            id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                            type VARCHAR(30) NOT NULL,
                                            date_from DATE NOT NULL,
                                            date_to DATE NOT NULL,
                                            status VARCHAR(30) NOT NULL
);

-- Table Participants_Course_Info
CREATE TABLE IF NOT EXISTS wateradventure.participants_course_info (
                                                            id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                            access_given_at TIMESTAMP NOT NULL,
                                                            is_passed BOOLEAN NOT NULL,
                                                            course_id BIGINT NOT NULL,
                                                            CONSTRAINT fk_Accesses_Courses1 FOREIGN KEY (course_id) REFERENCES wateradventure.courses (id)
);

-- Table Participants_Learning_History
CREATE TABLE IF NOT EXISTS wateradventure.participants_learning_history (
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
                                                 accesses_id BIGINT NOT NULL,
                                                 answers_history_id BIGINT NOT NULL,
                                                 CONSTRAINT fk_Users_Accesses FOREIGN KEY (accesses_id) REFERENCES wateradventure.participants_course_info (id),
                                                 CONSTRAINT fk_Participants_Participants_Answers_History1 FOREIGN KEY (answers_history_id) REFERENCES wateradventure.participants_learning_history (id)
);

-- Table Questions
CREATE TABLE IF NOT EXISTS wateradventure.questions (
                                              id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                              content TEXT NOT NULL,
                                              first_answer VARCHAR(45) NOT NULL,
                                              second_answer VARCHAR(45) NOT NULL,
                                              third_answer VARCHAR(45) NOT NULL,
                                              section VARCHAR(45) NOT NULL,
                                              correct_answer VARCHAR(45) NOT NULL,
                                              explanation TEXT NULL,
                                              image VARCHAR(40) NULL
);

-- Table Events
CREATE TABLE IF NOT EXISTS wateradventure.events (
                                           id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                           type VARCHAR(45) NOT NULL,
                                           is_paid BOOLEAN NOT NULL,
                                           cost DOUBLE PRECISION NOT NULL,
                                           date TIMESTAMP NOT NULL,
                                           city VARCHAR(45) NOT NULL,
                                           orderer_email VARCHAR(9) NOT NULL,
                                           orderer_first_name VARCHAR(45) NOT NULL,
                                           orderer_last_name VARCHAR(45) NOT NULL,
                                           orderer_phone_number VARCHAR(45) NOT NULL
);

-- Table General_learning
CREATE TABLE IF NOT EXISTS wateradventure.general_learning (
                                                     id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                     course_type VARCHAR(45) NOT NULL,
                                                     questions_answered INT NOT NULL,
                                                     correct_answers INT NOT NULL,
                                                     participants_Learning_History_id BIGINT NOT NULL,
                                                     CONSTRAINT fk_General_learning_Participants_Learning_History1 FOREIGN KEY (participants_learning_history_id) REFERENCES wateradventure.participants_learning_history (id)
);
