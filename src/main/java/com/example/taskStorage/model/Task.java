package com.example.taskStorage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "task_name")
	@NotBlank(message = "Task name should not be blank")
	@Length(max = 100, message = "Task name is too long")
	private String taskName;

	@Column(name = "task_description")
	@NotBlank(message = "Task description should not be blank")
	@Length(max = 255, message = "Task description is too long")
	private String taskDescription;

	@Column(name = "current_status")
	@Enumerated(EnumType.STRING)
	private TaskCurrentStatus status;

//	@Column(name = "project_id")
//	private Long projectId;

//	public Long getProjectId() {
//		return projectId;
//	}
//
//	public void setProjectId(Long projectId) {
//		this.projectId = projectId;
//	}

	@Min(value = 0, message = "Priority should not be negative or zero")
	@Column(name = "priority")
	private int priority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public TaskCurrentStatus getStatus() {
		return status;
	}

	public void setStatus(TaskCurrentStatus status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Project getProject() {
		return project;
	}

	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonBackReference
	private Project project;

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return priority == task.priority && id.equals(task.id) && Objects.equals(taskName, task.taskName) && Objects.equals(taskDescription, task.taskDescription) && status == task.status && Objects.equals(project, task.project);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, taskName, taskDescription, status, priority, project);
	}

	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", taskName='" + taskName + '\'' +
				", taskDescription='" + taskDescription + '\'' +
				", status=" + status +
				", priority=" + priority +
				", project=" + project +
				'}';
	}
}
