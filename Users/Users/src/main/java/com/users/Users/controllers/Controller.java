package com.users.Users.controllers;

import com.users.Users.models.Usuario;
import com.users.Users.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private Repository repo;

    @GetMapping()
    public String index(){
        return "Conectado";
    }

    @GetMapping("usuarios")
    public List<Usuario> getUsuario(){
        return repo.findAll();
    }

    @PostMapping("guardar")
    public String save(@RequestBody Usuario usuario){
        repo.save(usuario);
        return "Guardado";
    }

    @PutMapping("editar/{id}")
    public String update(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario updateUsuario = repo.findById(id).get();
        updateUsuario.setNombre(usuario.getNombre());
        updateUsuario.setTelefono(usuario.getTelefono());
        updateUsuario.setContraseña(usuario.getContraseña());
        updateUsuario.setCorreo(usuario.getCorreo());
        repo.save(updateUsuario);
        return "Editado";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id ) {
        Usuario deleteUsuario = repo.findById(id).get();
        repo.delete(deleteUsuario);
        return "Usuario eliminado";
    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = repo.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("usuarios/telefono/{telefono}")
    public ResponseEntity<Usuario> getUsuarioByTelefono(@PathVariable String telefono) {
        Optional<Usuario> usuario = repo.findByTelefono(telefono);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginData) {
        String correo = loginData.get("correo");
        String contraseña = loginData.get("contraseña");
        Usuario usuario = repo.findByCorreo(correo).orElse(null);
        if (usuario == null) {
            return "El correo no existe";
        }
        if (usuario.getContraseña().equals(contraseña)) {
            return "Login exitoso";
        } else {
            return "Contraseña incorrecta";
        }
    }
}
