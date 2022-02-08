
INSERT INTO
    project (id, project_name, project_start_date, project_completion_date, current_status, priority)
VALUES
(1, 'New project 1', '2022-02-08', '2022-02-18', 'Active', 1),
(2, 'New project 3', '2022-02-08', '2022-02-18', 'NotStarted', 2),
(3, 'New project 2', '2022-02-08', '2022-02-18', 'Completed', 3);

INSERT INTO
    task (id, task_name, task_description, current_status, priority, project_id)
VALUES
(1, 'Task one', 'Do task one', 'Done', 1, 1),
(2, 'Task one', 'Do task one', 'ToDo', 1, 2),
(3, 'Task one', 'Do task one', 'InProgress', 1, 3),
(4, 'Task two', 'Do task two', 'ToDo', 1, 1),
(5, 'Task two', 'Do task two', 'InProgress', 1, 2),
(6, 'Task two', 'Do task two', 'Done', 1, 3),
(7, 'Task three', 'Do task three', 'ToDo', 1, 1),
(8, 'Task three', 'Do task three', 'InProgress', 1, 2),
(9, 'Task three', 'Do task three', 'Done', 1, 3);

ALTER SEQUENCE project_id_seq RESTART WITH 4;
ALTER SEQUENCE task_id_seq RESTART WITH 10;
