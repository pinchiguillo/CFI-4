package com.publicaciones.controllers;

import com.publicaciones.models.Documento;
import com.publicaciones.services.PersistenceService;
import java.util.List;

public class EditorController {

    private Documento documento;
    private PersistenceService persistenceService;

    public EditorController() {
        this.documento = new Documento();
        this.persistenceService = new PersistenceService();
    }

    public void crearDocumento(String nombreArchivo, String contenidoInicial) {
        documento = new Documento(nombreArchivo, contenidoInicial);
    }

    public void editarDocumento(String nuevoContenido) {
        documento.setContenido(nuevoContenido);
    }

    public boolean guardarDocumento(String nombreArchivo) {
        documento.setNombreArchivo(nombreArchivo);
        return persistenceService.guardar(documento);
    }

    public boolean cargarDocumento(String nombreArchivo) {
        Documento docCargado = persistenceService.cargarPorNombreArchivo(nombreArchivo);
        if (docCargado != null) {
            documento = docCargado;
            return true;
        }
        return false;
    }

    public boolean eliminarDocumento(String nombreArchivo) {
        return persistenceService.eliminarPorNombreArchivo(nombreArchivo);
    }

    public Documento getDocumento() {
        return documento;
    }

    // NUEVO: Listar todos los documentos de la BD
    public List<Documento> listarDocumentos() {
        return persistenceService.listarTodos();
    }
}
