package com.example.task_manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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


}
