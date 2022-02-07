package com.example.task_manager.model;

import javax.persistence.*;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String taskName;
	private String taskDescription;
	private TaskCurrentStatus status;
	int priority;

	@ManyToOne
	private Project project;

}
