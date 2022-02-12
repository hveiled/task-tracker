package com.example.taskStorage.service;

import com.example.taskStorage.model.Project;
import com.example.taskStorage.model.Task;
import com.example.taskStorage.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

/**
 * Project service. Business layer.
 *
 * @author Andrei Ivanov
 * @version 1.0
 */
@Service
public class ProjectService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

	/**
	 * DI of repository
	 */
	private final ProjectRepository projectRepository;

	public ProjectService(@Autowired ProjectRepository repository) {
		this.projectRepository = repository;
	}

	/**
	 * Method of business layer allows to get all the projects.
	 *
	 * @return List of Project objects
	 */
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	/**
	 * Method of business layer. Allows to create a project.
	 *
	 * @param project The Project body
	 * @return Saved project
	 * @throws ResponseStatusException if project with given name already exists, or Date errors
	 */
	public Project createProject(Project project) {
		if (projectRepository.existsByProjectName(project.getProjectName())) {
			LOGGER.info("Project with the name " + project.getProjectName() + " already exists");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project with the name " +
					"'" + project.getProjectName() + "'" + " already exists");
		}
		if (LocalDate.parse(project.getProjectStartDate())
				.isAfter(LocalDate.parse(project.getProjectCompletionDate()))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date can not be after Completion date");
		}
		return projectRepository.save(project);
	}

	/**
	 * Method of business layer. Allows to find a project by given ID.
	 *
	 * @param id The ID of the project
	 * @return Found project
	 * @throws ResponseStatusException if the Project with the given ID was not found
	 */
	public Project findById(Long id) {
		return projectRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
						id + " was not found")
		);
	}

	/**
	 * Method of business layer. Allows to change existing project by ID.
	 *
	 * @param id The ID of changing project
	 * @param project The Project body
	 * @return Project body.
	 * @throws ResponseStatusException if the Project with the ID was not found
	 * @see Project the entity model of Project
	 */
	public Project changeProject(Long id, Project project) {
		if (!projectRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
					id + " was not found");
		}
		project.setId(id);
		return projectRepository.save(project);
	}

	/**
	 * Method of business layer. Allows to delete a project by ID.
	 *
	 * @param id The ID of a project
	 * @throws ResponseStatusException if the project was not found
	 */
	public void deleteProject(Long id) {
		if (!projectRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
					id + " was not found");
		}
		projectRepository.deleteById(id);
	}

	/**
	 * Method of business layer. Allows to add a new task to a existing project
	 *
	 * @param id The ID of a project
 	 * @param task The Task body
	 * @see Task entity model
	 */
	public void addTask(Long id, Task task) {
		Project foundProject = projectRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
						id + " was not found")
		);

		for (Task el : foundProject.getTasks()) {
			if (el.getTaskName().equals(task.getTaskName())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The task in the task list already");
			}
		}
		task.setProject(foundProject);
		foundProject.getTasks().add(task);
		projectRepository.save(foundProject);
	}

	/**
	 * Method of business layer. Allows to delete a task form a project by taskId
	 *
	 * @param projectId Project ID
	 * @param taskId Task ID
	 */
	public void deleteTask(Long projectId, int taskId) {
		Project foundProject = projectRepository.findById(projectId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
						projectId + " was not found")
		);

		boolean exists = false;
		int index = 0;
		for (Task el : foundProject.getTasks()) {
			if (el.getId() == (long)taskId) {
				foundProject.getTasks().remove(el);
				projectRepository.save(foundProject);
				exists = true;
				break;
			}
		}
		if (!exists) {
			LOGGER.info("Task was  not found");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no task with ID " + taskId);
		}
	}
}
