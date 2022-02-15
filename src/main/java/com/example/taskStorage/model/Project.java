package com.example.taskStorage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * Project entity model
 */
//@Builder(toBuilder = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_name")
	@Length(max = 100, message = "Project name is too long")
	@NotBlank(message = "Project name should not be blank")
	private String projectName;

	@Column(name = "project_start_date")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	private String projectStartDate;

	@Column(name = "project_completion_date")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	private String projectCompletionDate;

	@Column(name = "current_status")
	@Enumerated(EnumType.STRING)
	private ProjectCurrentStatus currentStatus;

	@Column(name = "priority")
	@Min(value = 0, message = "Project priority should not be negative or zero")
	private int priority;

	@OneToMany(mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Task> tasks/* = new HashSet<>()*/;

	private Project(Long id, String projectName, String projectStartDate, String projectCompletionDate, ProjectCurrentStatus status, int priority, Set<Task> tasks) {
		this.id = id;
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectCompletionDate = projectCompletionDate;
		this.currentStatus = status;
		this.priority = priority;
		this.tasks = tasks;
	}

	public static class Builder{
		private Long id;
		private String projectName;
		private String projectStartDate;
		private String projectCompletionDate;
		private ProjectCurrentStatus currentStatus;
		private int priority;
		private Set<Task> tasks;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder projectName(String name) {
			this.projectName = name;
			return this;
		}

		public Builder projectStartDate(String startDate) {
			this.projectStartDate = startDate;
			return this;
		}

		public Builder projectCompletionDate(String completionDate) {
			this.projectCompletionDate = completionDate;
			return this;
		}

		public Builder currentStatus(ProjectCurrentStatus status) {
			this.currentStatus = status;
			return this;
		}

		public Builder priority(int priority) {
			this.priority = priority;
			return this;
		}

		public Builder tasks(Set<Task> tasks) {
			this.tasks = tasks;
			return this;
		}

		public Project build() {
			return new Project(id, projectName, projectStartDate, projectCompletionDate, currentStatus, priority, tasks);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Project project = (Project) o;
		return priority == project.priority && id.equals(project.id) && Objects.equals(projectName, project.projectName) && Objects.equals(projectStartDate, project.projectStartDate) && Objects.equals(projectCompletionDate, project.projectCompletionDate) && currentStatus == project.currentStatus && Objects.equals(tasks, project.tasks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, projectName, projectStartDate, projectCompletionDate, currentStatus, priority, tasks);
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
