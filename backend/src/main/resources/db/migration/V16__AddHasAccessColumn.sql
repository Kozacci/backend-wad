-- PostgreSQL Script
-- 30.12.2023 13:30
-- Adding has_access column to participant_course table

alter table wateradventure.participant_courses add column if not exists has_access boolean not null default false;