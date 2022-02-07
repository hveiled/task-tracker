package com.example.task_manager.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	String projectName;
	String projectStartDate;
	String projectCompletionDate;
	ProjectCurrentStatus currentStatus;
	int priority;

	@OneToMany
	private List<Task> taskList;


}
