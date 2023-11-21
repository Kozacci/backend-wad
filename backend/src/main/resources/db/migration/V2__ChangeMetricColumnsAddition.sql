-- PostgreSQL Script
-- 06.11.2023 15:00
-- Adding change(time) metric to some tables

ALTER TABLE wateradventure.courses
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP;
ALTER TABLE wateradventure.courses
    ADD COLUMN IF NOT EXISTS modified_at TIMESTAMP;

ALTER TABLE wateradventure.events
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP;
ALTER TABLE wateradventure.events
    ADD COLUMN IF NOT EXISTS modified_at TIMESTAMP;

ALTER TABLE wateradventure.questions
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP;
ALTER TABLE wateradventure.questions
    ADD COLUMN IF NOT EXISTS modified_at TIMESTAMP;

ALTER TABLE wateradventure.participants
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP;
ALTER TABLE wateradventure.participants
    ADD COLUMN IF NOT EXISTS modified_at TIMESTAMP;

ALTER TABLE wateradventure.participant_courses
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP;
ALTER TABLE wateradventure.participant_courses
    ADD COLUMN IF NOT EXISTS modified_at TIMESTAMP;
