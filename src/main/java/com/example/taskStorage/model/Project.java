package com.example.taskStorage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(
			cascade = CascadeType.ALL, fetch = FetchType.EAGER,
			mappedBy = "project", orphanRemoval = true)
	@JsonManagedReference
	private List<Task> tasks = new ArrayList<>();

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
