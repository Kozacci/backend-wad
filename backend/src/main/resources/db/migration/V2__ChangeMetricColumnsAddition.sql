ALTER TABLE wateradventure.courses
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE wateradventure.courses
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE wateradventure.events
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE wateradventure.events
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE wateradventure.questions
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE wateradventure.questions
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE wateradventure.participants
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE wateradventure.participants
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE wateradventure.participants_course_info
    ADD COLUMN created_at TIMESTAMP;
ALTER TABLE wateradventure.participants_course_info
    ADD COLUMN modified_at TIMESTAMP;

