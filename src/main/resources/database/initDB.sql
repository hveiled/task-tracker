CREATE TABLE IF NOT EXISTS project
(
    id BIGSERIAL            PRIMARY KEY ,
    project_name            VARCHAR (100) NOT NULL ,
    project_start_date      VARCHAR (10) ,
    project_completion_date VARCHAR (10) ,
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
    FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE ON UPDATE CASCADE
);