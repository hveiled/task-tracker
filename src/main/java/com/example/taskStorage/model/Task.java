package com.example.taskStorage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@NoArgsConstructor
@Data
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

	@Min(value = 0, message = "Priority should not be negative or zero")
	@Column(name = "priority")
	private int priority;

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "project_id")
	@JsonBackReference
	private Project project;

	private Task(Long id, String taskName, String taskDescription, TaskCurrentStatus status, int priority){
		this.id = id;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.status = status;
		this.priority = priority;
	}

	public static class Builder {
		private Long id;
		private String taskName;
		private String taskDescription;
		private TaskCurrentStatus status;
		private int priority;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder taskName(String name) {
			this.taskName = name;
			return this;
		}

		public Builder taskDescription(String taskDescription) {
			this.taskDescription = taskDescription;
			return this;
		}

		public Builder status(TaskCurrentStatus status) {
			this.status = status;
			return this;
		}

		public Builder priority(int priority) {
			this.priority = priority;
			return this;
		}

		public Task build() {
			return new Task(id, taskName, taskDescription, status, priority);
		}

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return priority == task.priority &&
				id.equals(task.id) &&
				Objects.equals(taskName, task.taskName) &&
				Objects.equals(taskDescription, task.taskDescription) &&
				status == task.status &&
				Objects.equals(project, task.project);
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
