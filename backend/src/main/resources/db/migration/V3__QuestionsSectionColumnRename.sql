-- PostgreSQL Script
-- 06.11.2023 22:00
-- Renaming question table column section to category
-- (due to compability with category_learning table)

-- Script to check if the "section" column exists in the "questions" table
DO $$
    BEGIN
        IF EXISTS (SELECT column_name
                   FROM information_schema.columns
                   WHERE table_name = 'questions'
                     AND column_name = 'section')
            THEN
            ALTER TABLE wateradventure.questions
                RENAME COLUMN section TO category;
        END IF;
END $$;