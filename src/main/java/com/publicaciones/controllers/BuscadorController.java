package com.publicaciones.controllers;

import com.publicaciones.models.Documento;
import com.publicaciones.utils.TextAnalyzer;
import java.util.List;

public class BuscadorController {

    // Busca una palabra en el documento y retorna una lista de índices donde se encuentra
    public List<Integer> buscarPalabra(Documento documento, String palabra) {
        return TextAnalyzer.buscarPalabra(documento.getContenido(), palabra);
    }
}
