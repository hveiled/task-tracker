package com.example.taskStorage.repository;

import com.example.taskStorage.model.Project;
import com.example.taskStorage.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStorageRepository extends JpaRepository<Task, Long> {
}
