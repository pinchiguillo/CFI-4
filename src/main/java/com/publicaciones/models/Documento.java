package com.publicaciones.models;

import java.util.ArrayList;
import java.util.List;

public class Documento {
    private String contenido;
    private boolean enEdicion;
    private List<String> historialCambios;

    public Documento() {
        this.contenido = "";
        this.enEdicion = false;
        this.historialCambios = new ArrayList<>();
    }

    public Documento(String contenido) {
        this.contenido = contenido;
        this.enEdicion = false;
        this.historialCambios = new ArrayList<>();
        this.historialCambios.add(contenido);
    }

    // Getters y Setters
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

    // Métodos para guardar y cargar el documento (implementación simplificada)
    public boolean guardarDocumento(String rutaArchivo) {
        // Lógica para escribir el contenido en un archivo
        // Se retorna true para indicar que se realizó la operación exitosamente
        return true;
    }

    public boolean cargarDocumento(String rutaArchivo) {
        // Lógica para cargar el contenido desde un archivo
        // Se retorna true para indicar que se realizó la operación exitosamente
        return true;
    }

    @Override
    public String toString() {
        String preview = contenido.length() > 20 ? contenido.substring(0, 20) + "..." : contenido;
        return "Documento [enEdicion=" + enEdicion + ", contenido=" + preview + "]";
    }
}
