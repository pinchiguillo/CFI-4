package com.publicaciones.controllers;

import com.publicaciones.models.Documento;
import com.publicaciones.utils.Comparador;
import java.util.List;

public class ComparadorController {

    // Compara el contenido de dos documentos y retorna una lista con las diferencias encontradas
    public List<String> compararDocumentos(Documento doc1, Documento doc2) {
        return Comparador.comparar(doc1.getContenido(), doc2.getContenido());
    }

    // Calcula la cantidad de palabras en un documento
    public int contarPalabras(Documento documento) {
        String contenido = documento.getContenido();
        if (contenido == null || contenido.isEmpty()) {
            return 0;
        }
        String[] palabras = contenido.split("\\s+");
        return palabras.length;
    }
}
