package com.publicaciones.controllers;

import com.publicaciones.models.Contacto;
import com.publicaciones.services.ContactoService;
import java.util.List;

public class ContactoController {

    private ContactoService contactoService;

    public ContactoController() {
        // Se instancia el servicio de contacto que utiliza Hibernate para la persistencia
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

    // Actualiza un contacto existente identificado por su id
    public boolean actualizarContacto(Long id, String nombre, String email, String telefono) {
        return contactoService.actualizarContacto(id, nombre, email, telefono);
    }

    // Elimina un contacto identificado por su id
    public boolean eliminarContacto(Long id) {
        return contactoService.eliminarContacto(id);
    }

    // Permite cerrar el servicio y liberar recursos (EntityManager)
    public void cerrar() {
        contactoService.cerrar();
    }
}
