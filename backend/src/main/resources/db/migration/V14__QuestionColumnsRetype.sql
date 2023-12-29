-- PostgreSQL Script
-- 29.12.2023 20:35
-- Modifying question entity columns type

ALTER TABLE wateradventure.questions
    ALTER COLUMN first_answer TYPE text;

ALTER TABLE wateradventure.questions
    ALTER COLUMN second_answer TYPE text;

ALTER TABLE wateradventure.questions
    ALTER COLUMN third_answer TYPE text;