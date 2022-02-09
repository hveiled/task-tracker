package com.example.taskStorage.repository;

import com.example.taskStorage.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project repository class. Persistence layer.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	/**
	 * Method allows to find out either project exist or not
	 * @param projectName String type project name.
	 * @return boolean value
	 */
	boolean existsByProjectName(String projectName);
}
