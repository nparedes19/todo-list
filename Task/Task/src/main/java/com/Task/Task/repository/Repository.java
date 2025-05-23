package com.Task.Task.repository;

import com.Task.Task.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Tarea, Long> {
}
