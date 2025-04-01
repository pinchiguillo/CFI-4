package com.publicaciones.controllers;

import com.publicaciones.models.Documento;
import com.publicaciones.services.PersistenceService;

public class EditorController {

    private Documento documento;
    private PersistenceService persistenceService;

    public EditorController() {
        // Inicializa un documento vacío y el servicio de persistencia basado en Hibernate
        this.documento = new Documento();
        this.persistenceService = new PersistenceService();
    }

    // Crea un nuevo documento con contenido inicial
    public void crearDocumento(String contenidoInicial) {
        documento = new Documento(contenidoInicial);
    }

    // Edita el contenido del documento actual
    public void editarDocumento(String nuevoContenido) {
        documento.setContenido(nuevoContenido);
    }

    // Guarda el documento actual en la base de datos utilizando el servicio de persistencia
    public boolean guardarDocumento() {
        return persistenceService.guardar(documento);
    }

    // Carga un documento desde la base de datos utilizando su ID y lo asigna como documento actual
    public boolean cargarDocumento(Long id) {
        Documento docCargado = persistenceService.cargar(id);
        if (docCargado != null) {
            documento = docCargado;
            return true;
        }
        return false;
    }

    // Devuelve el documento actual
    public Documento getDocumento() {
        return documento;
    }
}
