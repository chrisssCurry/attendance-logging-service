DROP TABLE IF EXISTS attendance_logs;
CREATE TABLE attendance_logs (
    log_id BIGINT NOT NULL,
    card_id VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP(6) NOT NULL,
    lecture_id BIGINT,
    PRIMARY KEY (log_id)
);

DROP TABLE IF EXISTS lectures;
CREATE TABLE lectures (
    lecture_id BIGINT NOT NULL,
    title VARCHAR(255),
    description VARCHAR(255),
    classroom VARCHAR(255),
    term SMALLINT NOT NULL,
    end_time TIME NOT NULL,
    start_time TIME NOT NULL,
    PRIMARY KEY (lecture_id)
);

DROP TABLE IF EXISTS students;
CREATE TABLE students (
    student_id BIGINT NOT NULL,
    card_id VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(255),
    major VARCHAR(255) NOT NULL,
    year SMALLINT,
    PRIMARY KEY (card_id)
);

ALTER TABLE IF EXISTS attendance_logs ADD CONSTRAINT FK__attendance_logs__lectures FOREIGN KEY (lecture_id) REFERENCES lectures;
ALTER TABLE IF EXISTS attendance_logs ADD CONSTRAINT FK__attendance_logs__students FOREIGN KEY (card_id) REFERENCES students;


CREATE INDEX index__students__card_id ON students (card_id);

CREATE INDEX index__lectures__lecture_id ON lectures (lecture_id);