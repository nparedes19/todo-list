package com.Task.Task.repository;

import com.Task.Task.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByCompletada(boolean completada);
}
