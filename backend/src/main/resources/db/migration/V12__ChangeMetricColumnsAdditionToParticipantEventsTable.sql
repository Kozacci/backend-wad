-- PostgreSQL Script
-- 10.11.2023 17:14
-- Adding change(time) metric to some tables


ALTER TABLE wateradventure.participant_events
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP;
ALTER TABLE wateradventure.participant_events
    ADD COLUMN IF NOT EXISTS modified_at TIMESTAMP;