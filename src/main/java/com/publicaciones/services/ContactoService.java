package com.publicaciones.services;

import com.publicaciones.models.Contacto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactoService {

    private Map<Integer, Contacto> contactos;
    private int idCounter;

    public ContactoService() {
        this.contactos = new HashMap<>();
        this.idCounter = 1;
    }

    // Agrega un nuevo contacto y asigna un id único.
    public boolean agregarContacto(Contacto contacto) {
        if (contacto != null) {
            contactos.put(idCounter++, contacto);
            return true;
        }
        return false;
    }

    // Retorna una lista con todos los contactos almacenados.
    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(contactos.values());
    }

    // Actualiza la información de un contacto identificado por su id.
    public boolean actualizarContacto(int id, String nombre, String email, String telefono) {
        if (contactos.containsKey(id)) {
            Contacto contacto = contactos.get(id);
            contacto.actualizarContacto(nombre, email, telefono);
            return true;
        }
        return false;
    }

    // Elimina un contacto identificado por su id.
    public boolean eliminarContacto(int id) {
        if (contactos.containsKey(id)) {
            contactos.remove(id);
            return true;
        }
        return false;
    }
}
