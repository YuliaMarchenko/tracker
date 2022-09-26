package com.example.tracker.repository;

import com.example.tracker.entities.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
}
