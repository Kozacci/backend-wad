-- PostgreSQL Script
-- 10.11.2023 13:00
-- Deleting unnecessary fields from events entity
-- Adding Participant Event entity

-- Updating Events table
ALTER TABLE wateradventure.events
    DROP COLUMN IF EXISTS orderer_email;

ALTER TABLE wateradventure.events
    DROP COLUMN IF EXISTS orderer_first_name;

ALTER TABLE wateradventure.events
    DROP COLUMN IF EXISTS orderer_last_name;

ALTER TABLE wateradventure.events
    DROP COLUMN IF EXISTS orderer_phone_number;

ALTER TABLE wateradventure.events
    DROP COLUMN IF EXISTS is_paid;

ALTER TABLE wateradventure.events
    ADD COLUMN IF NOT EXISTS duration TIME;

ALTER TABLE wateradventure.events
    ADD COLUMN IF NOT EXISTS max_participants_numer INTEGER;

-- Creating table Participant Events
CREATE TABLE IF NOT EXISTS wateradventure.participant_events (
                                                     id BIGINT DEFAULT nextval('wateradventure.id_seq') PRIMARY KEY,
                                                     orderer_email VARCHAR(40) NOT NULL,
                                                     orderer_first_name VARCHAR(30) NOT NULL,
                                                     orderer_last_name VARCHAR(40) NOT NULL,
                                                     orderer_phone_number VARCHAR(9) NOT NULL,
                                                     participants_number INTEGER NOT NULL,
                                                     is_paid BOOLEAN NOT NULL,
                                                     event_id BIGINT NOT NULL,
                                                     CONSTRAINT fk_Participant_Events1 FOREIGN KEY (event_id) REFERENCES wateradventure.events (id)
);