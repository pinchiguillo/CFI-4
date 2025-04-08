package com.publicaciones.models;

import jakarta.persistence.*;

@Entity
@Table(name = "dibujos")
public class Dibujo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Se especifica MEDIUMBLOB para permitir almacenar imágenes de mayor tamaño
    @Lob
    @Column(name = "imagen", nullable = false, columnDefinition="MEDIUMBLOB")
    private byte[] imagen;

    @Column(name = "nombre_archivo", length = 200)
    private String nombreArchivo;

    // Constructor vacío requerido por Hibernate
    public Dibujo() {
        // Al no recibir imagen, aseguramos que la referencia no sea null
        this.imagen = new byte[0];
    }

    // Constructor que solo recibe la imagen
    public Dibujo(byte[] imagen) {
        // Si es null, guardamos un array vacío
        this.imagen = (imagen != null) ? imagen : new byte[0];
    }

    // Constructor completo: imagen + nombreArchivo
    public Dibujo(byte[] imagen, String nombreArchivo) {
        // Si la imagen es null, guardamos un array vacío
        this.imagen = (imagen != null) ? imagen : new byte[0];
        this.nombreArchivo = nombreArchivo;
    }

    public Long getId() {
        return id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    // Setter para imagen: convierte null a new byte[0]
    public void setImagen(byte[] imagen) {
        this.imagen = (imagen != null) ? imagen : new byte[0];
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public String toString() {
        // Si no hay nombre, mostrar algo genérico; si existe, mostrarlo.
        if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
            return nombreArchivo;
        } else {
            return "Dibujo{" + "id=" + id + "}";
        }
    }
}
