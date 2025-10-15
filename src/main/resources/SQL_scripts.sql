CREATE SEQUENCE admins_seq start with 100;

CREATE TABLE admins(
    admin_id NUMBER PRIMARY KEY DEFAULT admins_seq.NEXTVAL,
    admin_user_id VARCHAR2(100) UNIQUE NOT NULL,
    admin_password VARCHAR2(255) NOT NULL,
    admin_name VARCHAR2(50) NOT NULL,
);

INSERT INTO admins (admin_user_id, admin_password, admin_name) VALUES ('admin1', 'password1', 'Admin One');
INSERT INTO admins (admin_user_id, admin_password, admin_name) VALUES ('admin2', 'password2', 'Admin Two'); 

CREATE SEQUENCE faculty_seq start with 100;

CREATE TABLE faculty(
    faculty_id BIGINT PRIMARY KEY DEFAULT faculty_seq.NEXTVAL,
    faculty_user VARCHAR(100) UNIQUE NOT NULL,
    faculty_password VARCHAR(255) NOT NULL,
    faculty_name VARCHAR(50) NOT NULL,
    subject VARCHAR(100) NOT NULL
); 

INSERT INTO faculty (faculty_user, faculty_password, faculty_name, subject) VALUES ('faculty1', 'password1', 'Faculty One', 'Mathematics');
INSERT INTO faculty (faculty_user, faculty_password, faculty_name, subject) VALUES ('faculty2', 'password2', 'Faculty Two', 'Science');

CREATE SEQUENCE jobs_seq start with 100;

CREATE TABLE jobs(
    job_id NUMBER PRIMARY KEY DEFAULT nex,
    job_title VARCHAR2(100) NOT NULL,
    job_description VARCHAR2(255) NOT NULL
);

INSERT INTO jobs (job_title, job_description) VALUES ('Software Engineer', 'Develop and maintain software applications.');
INSERT INTO jobs (job_title, job_description) VALUES ('Data Analyst', 'Analyze and interpret complex data sets.');  
INSERT INTO jobs (job_title, job_description) VALUES ('Project Manager', 'Oversee project planning and execution.');
INSERT INTO jobs (job_title, job_description) VALUES ('UX Designer', 'Design user-friendly interfaces and experiences.');
INSERT INTO jobs (job_title, job_description) VALUES ('Marketing Specialist', 'Develop and implement marketing strategies.');
INSERT INTO jobs (job_title, job_description) VALUES ('Sales Manager', 'Lead and manage sales teams to achieve targets.');
INSERT INTO jobs (job_title, job_description) VALUES ('HR Coordinator', 'Manage recruitment and employee relations.');
INSERT INTO jobs (job_title, job_description) VALUES ('Financial Analyst', 'Conduct financial analysis and reporting.');
INSERT INTO jobs (job_title, job_description) VALUES ('Content Writer', 'Create engaging content for various platforms.');
INSERT INTO jobs (job_title, job_description) VALUES ('Customer Support', 'Provide assistance and support to customers.');

CREATE TABLE applications(
    app_id NUMBER PRIMARY KEY,
    job_id NUMBER REFERENCES jobs(job_id),
    resume VARCHAR2(255) NOT NULL,
    status VARCHAR2(50) DEFAULT 'IN_REVIEW'
);

CREATE TABLE reviews(
    rev_id NUMBER PRIMARY KEY,
    fac_id NUMBER REFERENCES faculty(faculty_id),
    app_id NUMBER REFERENCES applications(app_id),
    content VARCHAR2(255)
);

SELECT * FROM applications;