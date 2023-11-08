-- PostgreSQL Script
-- 08.11.2023 12:30
-- Adding answers history foreign key column to participant courses table

ALTER TABLE wateradventure.participant_courses
    ADD COLUMN answers_history_id BIGINT NOT NULL;

ALTER TABLE wateradventure.participant_courses
    ADD CONSTRAINT fk_Accesses_Answers_History1 FOREIGN KEY (answers_history_id) REFERENCES wateradventure.answers_history (id);

ALTER TABLE wateradventure.participants
    DROP CONSTRAINT fk_Participants_Answers_History1;

ALTER TABLE wateradventure.participants
    DROP COLUMN answers_history_id;
