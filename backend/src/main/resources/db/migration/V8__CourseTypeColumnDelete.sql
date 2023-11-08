-- PostgreSQL Script
-- 08.11.2023 13:00
-- Deleting course types from learning tables

ALTER TABLE wateradventure.general_learning
    DROP COLUMN course_type;

ALTER TABLE wateradventure.category_learning
    DROP COLUMN course_type;

ALTER TABLE wateradventure.trial_exams
    DROP COLUMN course_type;