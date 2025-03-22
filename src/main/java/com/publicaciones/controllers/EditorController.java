package com.publicaciones.controllers;

import com.publicaciones.models.Documento;
import com.publicaciones.services.PersistenceService;

public class EditorController {

    private Documento documento;
    private PersistenceService persistenceService;

    public EditorController() {
        // Inicializa un documento vacío y el servicio de persistencia
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

    // Guarda el documento actual en la ruta especificada utilizando el servicio de persistencia
    public boolean guardarDocumento(String rutaArchivo) {
        return persistenceService.guardar(documento, rutaArchivo);
    }

    // Carga un documento desde la ruta especificada y lo asigna como documento actual
    public boolean cargarDocumento(String rutaArchivo) {
        Documento docCargado = persistenceService.cargar(rutaArchivo);
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
