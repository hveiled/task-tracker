CREATE TABLE IF NOT EXISTS project
(
    id BIGSERIAL            PRIMARY KEY ,
    project_name            VARCHAR (100) NOT NULL ,
    project_start_date      DATE ,
    project_completion_date DATE ,
    current_status          VARCHAR (20) ,
    priority                INTEGER
);

CREATE TABLE IF NOT EXISTS task
(
    id BIGSERIAL            PRIMARY KEY ,
    task_name               VARCHAR (100) NOT NULL ,
    task_description        VARCHAR (255) NOT NULL ,
    current_status          VARCHAR (20) ,
    priority                INTEGER ,
    project_id              INTEGER ,
    CONSTRAINT fk_task_project
    FOREIGN KEY (project_id) REFERENCES project (id)
);

-- CREATE TABLE IF NOT EXISTS project_tasks
-- (
--     project_id INTEGER ,
--     CONSTRAINT fk_project_task
--     FOREIGN KEY (project_id) REFERENCES project (id) ,
--     task_id INTEGER ,
--     CONSTRAINT fk_project_task1
--     FOREIGN KEY (task_id) REFERENCES task (id)
-- );
--
