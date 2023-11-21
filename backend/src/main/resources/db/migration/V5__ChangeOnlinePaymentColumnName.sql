-- PostgreSQL Script
-- 07.11.2023 10:00
-- Adding payment column to participant courses table

ALTER TABLE wateradventure.participant_courses
    RENAME COLUMN onlinePayment TO online_payment;
