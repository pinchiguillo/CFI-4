package com.publicaciones.services;

import com.publicaciones.models.Documento;
import com.publicaciones.utils.FileManager;

public class PersistenceService {

    // Guarda el contenido del documento en la ruta especificada.
    public boolean guardar(Documento documento, String rutaArchivo) {
        if (documento == null || rutaArchivo == null || rutaArchivo.isEmpty()) {
            return false;
        }
        return FileManager.escribirArchivo(rutaArchivo, documento.getContenido());
    }

    // Carga el contenido desde la ruta especificada y lo encapsula en un objeto Documento.
    public Documento cargar(String rutaArchivo) {
        String contenido = FileManager.leerArchivo(rutaArchivo);
        if (contenido != null) {
            return new Documento(contenido);
        }
        return null;
    }
}
