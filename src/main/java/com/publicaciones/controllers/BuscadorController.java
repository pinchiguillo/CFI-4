package com.publicaciones.controllers;

import com.publicaciones.models.Documento;
import com.publicaciones.services.PersistenceService;
import com.publicaciones.utils.TextAnalyzer;
import java.util.List;

public class BuscadorController {

    private PersistenceService persistenceService;

    public BuscadorController() {
        // Se instancia el servicio de persistencia para cargar los documentos desde la BD
        this.persistenceService = new PersistenceService();
    }

    // Busca una palabra en el documento identificado por su id y retorna una lista de índices donde se encuentra.
    public List<Integer> buscarPalabra(Long documentoId, String palabra) {
        Documento documento = persistenceService.cargar(documentoId);
        if (documento == null) {
            throw new IllegalArgumentException("Documento no encontrado con id: " + documentoId);
        }
        return TextAnalyzer.buscarPalabra(documento.getContenido(), palabra);
    }

    // Permite cerrar el servicio y liberar recursos.
    public void cerrar() {
        persistenceService.cerrar();
    }
}
