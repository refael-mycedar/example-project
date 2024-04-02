ALTER TABLE user
    ADD created_at datetime NULL;

ALTER TABLE user
    ADD updated_at datetime NULL;

ALTER TABLE user
    MODIFY email VARCHAR(255) NULL;

ALTER TABLE user
    MODIFY first_name VARCHAR(255) NULL;

ALTER TABLE user
    MODIFY last_name VARCHAR(255) NULL;