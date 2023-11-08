-- PostgreSQL Script
-- 08.11.2023 12:00
-- Adding is paid column to participant courses table

ALTER TABLE wateradventure.participant_courses
    ADD COLUMN is_paid BOOLEAN NOT NULL;