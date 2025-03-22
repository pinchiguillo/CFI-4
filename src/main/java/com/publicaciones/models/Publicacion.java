package com.publicaciones.models;

import java.util.Date;

public class Publicacion {
    private String titulo;
    private String contenido;
    private Date fecha;
    private Usuario autor; // Relación con la clase Usuario

    public Publicacion(String titulo, String contenido, Date fecha, Usuario autor) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Usuario getAutor() {
        return autor;
    }
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    // Método para actualizar el contenido de la publicación
    public void actualizarContenido(String nuevoContenido) {
        this.contenido = nuevoContenido;
    }

    // Método para obtener un extracto del contenido
    public String obtenerExtracto(int numCaracteres) {
        if (contenido.length() <= numCaracteres) {
            return contenido;
        }
        return contenido.substring(0, numCaracteres) + "...";
    }

    @Override
    public String toString() {
        return "Publicacion [titulo=" + titulo + ", fecha=" + fecha + ", autor=" + autor + "]";
    }
}
