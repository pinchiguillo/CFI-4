package com.publicaciones.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "en_edicion")
    private boolean enEdicion;

    @ElementCollection
    @CollectionTable(name = "historial_cambios", joinColumns = @JoinColumn(name = "documento_id"))
    @Column(name = "cambio")
    private List<String> historialCambios = new ArrayList<>();

    public Documento() {
        this.contenido = "";
        this.enEdicion = false;
    }

    public Documento(String contenido) {
        this.contenido = contenido;
        this.enEdicion = false;
        this.historialCambios.add(contenido);
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
        this.historialCambios.add(contenido);
    }

    public boolean isEnEdicion() {
        return enEdicion;
    }

    public void setEnEdicion(boolean enEdicion) {
        this.enEdicion = enEdicion;
    }

    public List<String> getHistorialCambios() {
        return historialCambios;
    }

    // Métodos simplificados (no persistentes)

    public boolean guardarDocumento(String rutaArchivo) {
        // Aquí podrías escribir la lógica para guardar en archivo externo
        return true;
    }

    public boolean cargarDocumento(String rutaArchivo) {
        // Aquí podrías escribir la lógica para cargar desde archivo externo
        return true;
    }

    @Override
    public String toString() {
        String preview = contenido.length() > 20 ? contenido.substring(0, 20) + "..." : contenido;
        return "Documento{" +
                "id=" + id +
                ", enEdicion=" + enEdicion +
                ", contenido='" + preview + '\'' +
                '}';
    }
}
