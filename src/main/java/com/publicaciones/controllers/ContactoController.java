package com.publicaciones.controllers;

import com.publicaciones.models.Contacto;
import com.publicaciones.services.ContactoService;
import java.util.List;

public class ContactoController {

    private ContactoService contactoService;

    public ContactoController() {
        this.contactoService = new ContactoService();
    }

    // Crea un nuevo contacto si el email es válido
    public boolean crearContacto(String nombre, String email, String telefono) {
        Contacto contacto = new Contacto(nombre, email, telefono);
        if (!contacto.validarEmail()) {
            return false; // Email inválido
        }
        return contactoService.agregarContacto(contacto);
    }

    // Retorna la lista de todos los contactos
    public List<Contacto> obtenerContactos() {
        return contactoService.obtenerTodos();
    }

    // Actualiza un contacto existente (se asume que se identifica por un id interno en el servicio)
    public boolean actualizarContacto(int id, String nombre, String email, String telefono) {
        return contactoService.actualizarContacto(id, nombre, email, telefono);
    }

    // Elimina un contacto identificado por su id
    public boolean eliminarContacto(int id) {
        return contactoService.eliminarContacto(id);
    }
}
