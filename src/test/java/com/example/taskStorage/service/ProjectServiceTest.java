package com.example.taskStorage.service;

import com.example.taskStorage.model.Project;
import com.example.taskStorage.model.ProjectCurrentStatus;
import com.example.taskStorage.model.Task;
import com.example.taskStorage.model.TaskCurrentStatus;
import com.example.taskStorage.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

	@Autowired
	private ProjectRepository repository;

	private final ProjectService service = new ProjectService(repository);

	@Test
	void findAll() {
		List<Project> actual = service.findAll();
		assertEquals(actual, actual);
	}

	@Test
	void testCreateProjectWhen() {
		Project project = new Project();
		project.setProjectName("Test project");
		project.setProjectStartDate("2022-02-08");
		project.setProjectCompletionDate("2022-02-18");
		project.setCurrentStatus(ProjectCurrentStatus.NotStarted);
		project.setPriority(1);

		Task task = new Task();
		task.setTaskName("Test task 1");
		task.setTaskDescription("Need to do task 1");
		task.setPriority(2);
		task.setStatus(TaskCurrentStatus.ToDo);
		task.setProject(project);

		project.setTasks(List.of(task));

		Project actualResult = service.createProject(project);
		assertEquals("Test project", actualResult.getProjectName());
		assertEquals("2022-02-08", actualResult.getProjectStartDate());
		assertEquals("2022-02-18", actualResult.getProjectCompletionDate());
		assertEquals(ProjectCurrentStatus.NotStarted, actualResult.getCurrentStatus());
		assertEquals(1, actualResult.getPriority());
	}

	@Test
	void findById() {

	}

	@Test
	void changeProject() {
		Project project = new Project();
		project.setProjectName("Test project");
		project.setProjectStartDate("2022-02-08");
		project.setProjectCompletionDate("2022-02-18");
		project.setCurrentStatus(ProjectCurrentStatus.NotStarted);
		project.setPriority(1);

		Task task = new Task();
		task.setTaskName("Test task 1");
		task.setTaskDescription("Need to do task 1");
		task.setPriority(2);
		task.setStatus(TaskCurrentStatus.ToDo);
		task.setProject(project);

		project.setTasks(List.of(task));
		Project actualResult = service.changeProject(1L, project );
		assertEquals("Test project", actualResult.getProjectName());
		assertEquals("2022-02-08", actualResult.getProjectStartDate());
		assertEquals("2022-02-18", actualResult.getProjectCompletionDate());
		assertEquals(ProjectCurrentStatus.NotStarted, actualResult.getCurrentStatus());
		assertEquals(1, actualResult.getPriority());
	}

	@Test
	void deleteProject() {
		Project project = new Project();
		project.setProjectName("Test project");
		project.setProjectStartDate("2022-02-08");
		project.setProjectCompletionDate("2022-02-18");
		project.setCurrentStatus(ProjectCurrentStatus.NotStarted);
		project.setPriority(1);

		Task task = new Task();
		task.setTaskName("Test task 1");
		task.setTaskDescription("Need to do task 1");
		task.setPriority(2);
		task.setStatus(TaskCurrentStatus.ToDo);
		task.setProject(project);

		project.setTasks(List.of(task));
		service.deleteProject(1L);
	}

	@Test
	void addTask() {
		Project pr = service.findById(1L);
		Task task = new Task();
		task.setTaskName("Newadded test task");
		task.setTaskDescription("ToDo newadded test task");
		task.setPriority(1);
		task.setStatus(TaskCurrentStatus.ToDo);
		service.addTask(pr.getId(), task);

	}

	@Test
	void deleteTask() {
	}
}