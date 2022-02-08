package com.example.taskStorage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_name")
	@Length(max = 100, message = "Project name is too long")
	@NotBlank(message = "Project name should not be blank")
	String projectName;

	@Column(name = "project_start_date")
	@NotBlank(message = "Project start date should not be blank")
	Date projectStartDate;

	@Column(name = "project_completion_date")
	Date projectCompletionDate;

	@Column(name = "current_status")
	@Enumerated(EnumType.STRING)
	ProjectCurrentStatus currentStatus;

	@Column(name = "priority")
	@Min(value = 0, message = "Project priority should not be negative or zero")
	int priority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectCompletionDate() {
		return projectCompletionDate;
	}

	public void setProjectCompletionDate(Date projectCompletionDate) {
		this.projectCompletionDate = projectCompletionDate;
	}

	public ProjectCurrentStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(ProjectCurrentStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@OneToMany(
			cascade = CascadeType.ALL, fetch = FetchType.EAGER,
			mappedBy = "project", orphanRemoval = true)
	@JsonManagedReference
	private List<Task> tasks = new ArrayList<>();

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> taskList) {
		this.tasks = taskList;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id=" + id +
				", projectName='" + projectName + '\'' +
				", projectStartDate=" + projectStartDate +
				", projectCompletionDate=" + projectCompletionDate +
				", currentStatus=" + currentStatus +
				", priority=" + priority +
				", tasks=" + tasks +
				'}';
	}
}
