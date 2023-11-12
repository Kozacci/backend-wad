-- PostgreSQL Script
-- 12.11.2023 12:00
-- Adding role column to participants table

ALTER TABLE wateradventure.participants
    ADD COLUMN IF NOT EXISTS role VARCHAR(45) NOT NULL DEFAULT 'CLIENT';