package com.example.task_manager.controller;

import com.example.task_manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

	private final ProjectService projectService;

	public ProjectController(@Autowired ProjectService projectService) {
		this.projectService = projectService;
	}
}
