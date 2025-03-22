package com.publicaciones.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileManager {

    // Lee el contenido de un archivo y lo retorna como String
    public static String leerArchivo(String ruta) {
        try {
            Path path = Paths.get(ruta);
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }

    // Escribe el contenido proporcionado en el archivo especificado
    public static boolean escribirArchivo(String ruta, String contenido) {
        try {
            Path path = Paths.get(ruta);
            Files.write(path, contenido.getBytes());
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }
}
