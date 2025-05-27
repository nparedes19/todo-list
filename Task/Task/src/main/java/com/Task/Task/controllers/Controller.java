package com.Task.Task.controllers;

import com.Task.Task.models.Tarea;
import com.Task.Task.models.Usuario;
import com.Task.Task.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@RestController

public class Controller {

    @Autowired
    private Repository repo;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public String index(){
        return "Conectado";
    }

    @GetMapping("tareas")
    public List<Tarea> getTareas(){
        return repo.findAll();
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarTarea(@RequestBody Tarea tarea) {
        String url = "http://USER-SERVICE/usuarios/" + tarea.getUsuarioId();
        try {
            restTemplate.getForEntity(url, Usuario.class);
            repo.save(tarea);
            return ResponseEntity.ok("Tarea guardada correctamente");

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar la tarea");
        }
    }

    @PutMapping("editar/{id}")
    public String update(@PathVariable Long id, @RequestBody Tarea tarea){
        Tarea updateTarea = repo.findById(id).get();
        updateTarea.setDescripcion(tarea.getDescripcion());
        updateTarea.setCompletada(tarea.isCompletada());
        updateTarea.setUsuarioId(tarea.getUsuarioId());
        repo.save(updateTarea);

        return "Tarea editada";
    }

    @DeleteMapping("eliminar/{id}")
    public String delete(@PathVariable Long id){
        Tarea deleteTarea = repo.findById(id).get();
        repo.delete(deleteTarea);

        return "Tarea eliminada";
    }

    @GetMapping("tareas/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Long id) {
        Optional<Tarea> tarea = repo.findById(id);
        if (tarea.isPresent()) {
            return ResponseEntity.ok(tarea.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("tareas/user/{id}")
    public List<Tarea> getTareasPorUsuario(@PathVariable Long id) {
        return repo.findByUsuarioId(id);
    }
}
