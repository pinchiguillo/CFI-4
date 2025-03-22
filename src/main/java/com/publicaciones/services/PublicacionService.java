package com.publicaciones.services;

import com.publicaciones.models.Publicacion;
import java.util.ArrayList;
import java.util.List;

public class PublicacionService {

    private List<Publicacion> publicaciones;

    public PublicacionService() {
        publicaciones = new ArrayList<>();
    }

    // Agrega una nueva publicación a la lista.
    public boolean agregarPublicacion(Publicacion publicacion) {
        if (publicacion != null) {
            publicaciones.add(publicacion);
            return true;
        }
        return false;
    }

    // Actualiza una publicación existente identificada por su título.
    // (Se asume que el título es único para simplificar el ejemplo.)
    public boolean actualizarPublicacion(String titulo, Publicacion publicacionActualizada) {
        for (int i = 0; i < publicaciones.size(); i++) {
            if (publicaciones.get(i).getTitulo().equals(titulo)) {
                publicaciones.set(i, publicacionActualizada);
                return true;
            }
        }
        return false;
    }

    // Retorna la lista de publicaciones.
    public List<Publicacion> obtenerPublicaciones() {
        return publicaciones;
    }
}
