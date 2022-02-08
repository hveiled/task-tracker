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

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ProjectService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

	private final ProjectRepository projectRepository;

	public ProjectService(@Autowired ProjectRepository repository) {
		this.projectRepository = repository;
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public Project createProject(Project project) {
		if (projectRepository.existsByProjectName(project.getProjectName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project with the name " +
					"'" + project.getProjectName() + "'" + " already exists");
		}
		return projectRepository.save(project);
	}

	public Project findById(Long id) {
		return projectRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
						id + " was not found")
		);
	}

	public Project changeProject(Long id, Project project) {
		Project changingProject = projectRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
						id + " was not found")
		);
		changingProject.setProjectName(project.getProjectName());
		changingProject.setCurrentStatus(project.getCurrentStatus());
		changingProject.setPriority(project.getPriority());
		changingProject.setProjectStartDate(project.getProjectStartDate());
		changingProject.setProjectCompletionDate(project.getProjectCompletionDate());
		changingProject.setTasks(project.getTasks());
		return projectRepository.save(changingProject);
	}

	public void deleteProject(Long id) {
		if (!projectRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
					id + " was not found");
		}
		projectRepository.deleteById(id);
	}

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

	public void deleteTask(Long projectId, int taskId) {
		Project foundProject = projectRepository.findById(projectId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with the ID " +
						projectId + " was not found")
		);

		boolean exists = false;
		int index = 0;
		for (Task el : foundProject.getTasks()) {
			if (el.getId() == (long)taskId) {
				exists = true;
				break;
			}
			index++;
		}
		if (!exists) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no task with ID " + taskId);
		} else {
			foundProject.getTasks().remove(index);
			projectRepository.save(foundProject);
		}
	}
}
