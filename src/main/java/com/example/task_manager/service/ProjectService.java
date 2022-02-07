package com.example.task_manager.service;

import com.example.task_manager.model.Project;
import com.example.task_manager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	private final ProjectRepository repository;

	public ProjectService(@Autowired ProjectRepository repository) {
		this.repository = repository;
	}

	private Project save(Project project) {
		return repository.save(project);
	}
}
