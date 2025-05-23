package com.Task.Task.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String descripcion;

    @Column
    @Getter
    @Setter
    private boolean completada;

    @Column
    @Getter
    @Setter
    private Long usuarioId;
}
