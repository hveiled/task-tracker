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

import java.util.Collections;
import java.util.Optional;

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
				.tasks(Collections.singleton(task))
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
//		assertEquals(Collections.singleton(task), actualResult.getTasks());
	}

	@Test
	void testDeleteProjectThrowsExc() {
		assertThrows(ResponseStatusException.class,() -> service.deleteProject(42L));
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
				.tasks(Collections.singleton(task))
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
//		assertEquals(Collections.singleton(task), actualResult.getTasks());
	}

	@Test
	void testChangeProjectIfFoungThanReturnsProject() {
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
				.tasks(Collections.singleton(task))
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);
		Mockito.when(repository.existsById(1L)).thenReturn(true);
		service.createProject(projectBody);
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);
		Project actualResult = service.changeProject(1L, projectBody);

		assertEquals(1L, actualResult.getId());
		assertEquals("Brand new project", actualResult.getProjectName());
		assertEquals("2022-02-12", actualResult.getProjectStartDate());
		assertEquals("2022-02-22", actualResult.getProjectCompletionDate());
		assertEquals(1, actualResult.getPriority());
		assertEquals(ProjectCurrentStatus.NotStarted, actualResult.getCurrentStatus());
	}
	@Test
	void testChangeProjectIfNotFountThanThrows() {
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
				.tasks(Collections.singleton(task))
				.currentStatus(ProjectCurrentStatus.NotStarted)
				.build();
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);
		Mockito.when(repository.existsById(1L)).thenReturn(false);
		service.createProject(projectBody);
		Mockito.when(repository.save(projectBody)).thenReturn(projectBody);

		assertThrows(ResponseStatusException.class, ()->service.changeProject(1L, projectBody));
	}
}