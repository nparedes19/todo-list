package com.Task.Task.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column
    @Getter
    @Setter
    private String nombre;

    @Column
    @Getter
    @Setter
    private String telefono;

    @Column
    @Getter
    @Setter
    private String correo;

    @Column
    @Getter
    @Setter
    private String contraseña;
}
