-- PostgreSQL Script
-- 10.11.2023 15:00
-- Renaming column in participant events column

ALTER TABLE wateradventure.events
    RENAME COLUMN max_participants_numer TO max_participants_number;
