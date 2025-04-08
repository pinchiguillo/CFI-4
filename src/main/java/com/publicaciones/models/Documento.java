package com.publicaciones.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "contenido", length = 65535)
    private String contenido;

    @Column(name = "en_edicion")
    private boolean enEdicion;

    // CONSTRUCTORES
    public Documento() {
    }

    public Documento(String nombreArchivo, String contenido) {
        this.nombreArchivo = nombreArchivo;
        this.contenido = contenido;
    }

    // GETTERS y SETTERS
    public Long getId() {
        return id;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isEnEdicion() {
        return enEdicion;
    }

    public void setEnEdicion(boolean enEdicion) {
        this.enEdicion = enEdicion;
    }

    // NUEVO: Sobrescribir toString() para que el combo muestre algo amigable
    @Override
    public String toString() {
        if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
            return nombreArchivo;
        } else {
            // Si no hay nombre, retorna algo genérico
            return "[Sin nombre] (ID: " + (id != null ? id : "??") + ")";
        }
    }
}
