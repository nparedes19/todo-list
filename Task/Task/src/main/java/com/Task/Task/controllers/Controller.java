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

    @PostMapping("guardar")
    public ResponseEntity<String> save(@RequestBody Tarea tarea) {
        // Validar que el usuario exista
        String url = "http://USER-SERVICE/usuarios/" + tarea.getUsuarioId();
        System.out.println("Consultando usuario en: " + url);

        try {
            ResponseEntity<Usuario> response = restTemplate.getForEntity(url, Usuario.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                repo.save(tarea);
                return ResponseEntity.ok("Guardo la tarea");
            }
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar tarea");
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
    public ResponseEntity<Tarea> getTareaById(@PathVariable Long id){
        Optional<Tarea> tarea = repo.findById((id));
        return tarea.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("tareas/completadas")
    public List<Tarea> getTareasPorEstado(@RequestParam boolean estado) {
        return repo.findByCompletada(estado);
    }
}
