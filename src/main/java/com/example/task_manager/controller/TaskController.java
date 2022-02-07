package com.example.task_manager.controller;

import com.example.task_manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

	private final ProjectService projectService;

	public TaskController(@Autowired ProjectService projectService) {
		this.projectService = projectService;
	}
}
