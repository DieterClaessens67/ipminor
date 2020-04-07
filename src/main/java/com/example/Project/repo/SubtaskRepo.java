package com.example.Project.repo;

import com.example.Project.domain.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepo extends JpaRepository<SubTask,Integer> {
}
