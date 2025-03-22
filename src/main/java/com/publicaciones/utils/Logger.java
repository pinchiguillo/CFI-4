package com.publicaciones.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "aplicacion.log";

    // Registra un mensaje informativo
    public static void log(String mensaje) {
        log("INFO", mensaje);
    }

    // Registra un mensaje de error
    public static void error(String mensaje) {
        log("ERROR", mensaje);
    }

    // Método interno para formatear y escribir el log en el archivo
    private static void log(String nivel, String mensaje) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println("[" + timestamp + "] [" + nivel + "] " + mensaje);
        } catch (IOException e) {
            System.err.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}
