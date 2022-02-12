package com.example.taskStorage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * Project entity model
 */
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
	String projectName;

	@Column(name = "project_start_date")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	String projectStartDate;

	@Column(name = "project_completion_date")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	String projectCompletionDate;

	@Column(name = "current_status")
	@Enumerated(EnumType.STRING)
	ProjectCurrentStatus currentStatus;

	@Column(name = "priority")
	@Min(value = 0, message = "Project priority should not be negative or zero")
	int priority;

	@OneToMany(mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Task> tasks = new HashSet<>();

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
