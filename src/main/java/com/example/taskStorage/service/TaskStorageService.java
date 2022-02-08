package com.example.taskStorage.service;

import com.example.taskStorage.model.Project;
import com.example.taskStorage.model.Task;
import com.example.taskStorage.repository.TaskStorageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskStorageService {

	private final TaskStorageRepository taskStorageRepository;
	private final ProjectService projectService;

	public TaskStorageService(TaskStorageRepository taskStorageRepository, ProjectService projectService) {
		this.taskStorageRepository = taskStorageRepository;
		this.projectService = projectService;
	}

	public Task findById(Long taskId) {
		return taskStorageRepository.findById(taskId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
		);
	}

	public Task createTask(Long projectId, Task task) {
		Project project = projectService.findById(projectId);
		if (project.getTasks().contains(task)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		task.setProject(project);
		return taskStorageRepository.save(task);
	}
}
