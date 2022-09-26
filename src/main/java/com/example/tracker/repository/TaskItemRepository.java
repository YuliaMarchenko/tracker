package com.example.tracker.repository;

import com.example.tracker.entities.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskItemRepository extends JpaRepository<TaskItem, Long> {
}
