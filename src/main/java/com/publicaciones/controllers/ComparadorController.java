package com.publicaciones.controllers;

import com.publicaciones.models.Documento;
import com.publicaciones.services.PersistenceService;
import com.publicaciones.utils.Comparador;
import java.util.List;

public class ComparadorController {

    private PersistenceService persistenceService;

    public ComparadorController() {
        // Se instancia el servicio de persistencia para cargar los documentos desde la BD
        this.persistenceService = new PersistenceService();
    }

    // Compara el contenido de dos documentos identificados por su ID y retorna una lista con las diferencias encontradas.
    public List<String> compararDocumentos(Long docId1, Long docId2) {
        Documento doc1 = persistenceService.cargar(docId1);
        Documento doc2 = persistenceService.cargar(docId2);
        if (doc1 == null || doc2 == null) {
            throw new IllegalArgumentException("Documento no encontrado con alguno de los IDs proporcionados.");
        }
        return Comparador.comparar(doc1.getContenido(), doc2.getContenido());
    }

    // Calcula la cantidad de palabras en un documento identificado por su ID.
    public int contarPalabras(Long documentoId) {
        Documento doc = persistenceService.cargar(documentoId);
        if (doc == null) {
            throw new IllegalArgumentException("Documento no encontrado con id: " + documentoId);
        }
        String contenido = doc.getContenido();
        if (contenido == null || contenido.isEmpty()) {
            return 0;
        }
        String[] palabras = contenido.split("\\s+");
        return palabras.length;
    }

    // Permite cerrar el servicio y liberar recursos.
    public void cerrar() {
        persistenceService.cerrar();
    }
}
