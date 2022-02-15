package com.example.taskStorage.service;

import com.example.taskStorage.model.Project;
import com.example.taskStorage.model.ProjectCurrentStatus;
import com.example.taskStorage.model.Task;
import com.example.taskStorage.model.TaskCurrentStatus;
import com.example.taskStorage.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

	private final ProjectRepository repository = Mockito.mock(ProjectRepository.class);
	private ProjectService service;
	private Project.Builder projectBuilder;
	private Task.Builder taskBuilder;

	@BeforeEach
	void setUp() {
		service = new ProjectService(repository);
		projectBuilder = new Project.Builder();
		taskBuilder = new Task.Builder();
	}

	@Test
	void createProject() {
		Task task = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();
		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(Set.of(task))
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);
		Project actualResult = service.createProject(projectBody);
		assertEquals(1L, actualResult.getId());
		assertEquals("Brand new project", actualResult.getProjectName());
		assertEquals("2022-02-12", actualResult.getProjectStartDate());
		assertEquals("2022-02-22", actualResult.getProjectCompletionDate());
		assertEquals(1, actualResult.getPriority());
		assertEquals(ProjectCurrentStatus.NotStarted, actualResult.getCurrentStatus());
		assertEquals(projectBody.getTasks(), actualResult.getTasks());
	}

	@Test
	void testDeleteProjectWhenIdNotFoundThrowsExc() {
		long id = 1L;
		Mockito.when(repository.existsById(id)).thenReturn(false);
		assertThrows(ResponseStatusException.class, ()->service.deleteProject(id));
	}

	@Test
	void testDeleteProjectWhenIdFoundDoesNotThrow() {
		long id = 1L;
		Mockito.when(repository.existsById(id)).thenReturn(true);
		assertDoesNotThrow(()->service.deleteProject(id));
	}

	@Test
	void testFindById() {
		Task task = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();
		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(Set.of(task))
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(projectBody));
		Project actualResult = service.findById(1L);
		assertEquals(1L, actualResult.getId());
		assertEquals("Brand new project", actualResult.getProjectName());
		assertEquals("2022-02-12", actualResult.getProjectStartDate());
		assertEquals("2022-02-22", actualResult.getProjectCompletionDate());
		assertEquals(1, actualResult.getPriority());
		assertEquals(ProjectCurrentStatus.NotStarted, actualResult.getCurrentStatus());
		assertEquals(projectBody.getTasks(), actualResult.getTasks());
	}

	@Test
	void testChangeProjectIfProjectFoundThanReturnsProject() {
		Task task = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();
		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(Set.of(task))
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();
		Mockito.when(repository.existsById(1L)).thenReturn(true);
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);
		Project actualResult = service.changeProject(1L, projectBody);

		assertEquals(1L, actualResult.getId());
		assertEquals("Brand new project", actualResult.getProjectName());
		assertEquals("2022-02-12", actualResult.getProjectStartDate());
		assertEquals("2022-02-22", actualResult.getProjectCompletionDate());
		assertEquals(1, actualResult.getPriority());
		assertEquals(ProjectCurrentStatus.NotStarted, actualResult.getCurrentStatus());
		assertEquals(projectBody.getTasks(), actualResult.getTasks());
	}

	@Test
	void testChangeProjectIfProjectNotFountThanThrows() {
		Task task = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();
		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(Set.of(task))
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();
		Mockito.when(repository.existsById(1L)).thenReturn(false);

		assertThrows(ResponseStatusException.class, ()->service.changeProject(1L, projectBody));
	}

	@Test
	void testDeleteTaskWhenProjectNotFoundThanTrowsExc() {
		Task task = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();
		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(Set.of(task))
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();
		Mockito.when(repository.findById(1L)).thenReturn(Optional.empty()); //project was not found
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);

		assertThrows(ResponseStatusException.class, ()->service.deleteTask(1L, 1L));
	}

	@Test
	void testDeleteTaskWhenProjectFoundThanDoesNotThrowExc() {
		Task task1 = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();
		Task task2 = taskBuilder
				.id(2L)
				.taskName("Some task two")
				.taskDescription("Need to do task two")
				.priority(1)
				.status(TaskCurrentStatus.InProgress)
				.build();

		Set<Task> taskSet = new HashSet<>(2);
		taskSet.add(task1);
		taskSet.add(task2);

		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(taskSet)
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();

		Optional<Project> optionalProject = Optional.of(projectBody);
		Mockito.when(repository.findById(1L)).thenReturn(optionalProject);
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);

		assertDoesNotThrow(()->service.deleteTask(1L, 2L));
	}

	@Test
	void testDeleteTaskWhenTaskFoundThanDoesNotThrowExc() {
		Task task = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();

		Set<Task> taskSet = new HashSet<>(2);
		taskSet.add(task);

		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(taskSet)
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();

		Optional<Project> optionalProject = Optional.of(projectBody);
		Mockito.when(repository.findById(1L)).thenReturn(optionalProject);
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);

		assertDoesNotThrow(()->service.deleteTask(1L, 1L));
	}

	@Test
	void testDeleteTaskWhenTaskNotFoundThanThrowsExc() {
		Task task = taskBuilder
				.id(1L)
				.taskName("Some task one")
				.taskDescription("Need to do task one")
				.priority(1)
				.status(TaskCurrentStatus.ToDo)
				.build();

		Set<Task> taskSet = new HashSet<>(2);
		taskSet.add(task);

		Project projectBody = projectBuilder
				.id(1L)
				.projectName("Brand new project")
				.projectStartDate("2022-02-12")
				.projectCompletionDate("2022-02-22")
				.priority(1)
				.tasks(taskSet)
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();

		Optional<Project> optionalProject = Optional.of(projectBody);
		Mockito.when(repository.findById(1L)).thenReturn(optionalProject);
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);

		assertThrows(ResponseStatusException.class, ()->service.deleteTask(1L, 10L));
	}
}