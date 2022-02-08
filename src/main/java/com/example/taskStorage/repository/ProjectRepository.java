package com.example.taskStorage.repository;

import com.example.taskStorage.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	boolean existsByProjectName(String projectName);
}
