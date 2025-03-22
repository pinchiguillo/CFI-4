package com.publicaciones.config;

import com.publicaciones.services.PublicacionService;
import com.publicaciones.services.ContactoService;
import com.publicaciones.services.PersistenceService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    private static Properties properties = new Properties();
    private static PublicacionService publicacionService;
    private static ContactoService contactoService;
    private static PersistenceService persistenceService;

    // Bloque estático para inicializar la configuración y servicios
    static {
        // Cargar propiedades desde un archivo de configuración
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error al cargar la configuración: " + e.getMessage());
        }

        // Inicialización de servicios
        publicacionService = new PublicacionService();
        contactoService = new ContactoService();
        persistenceService = new PersistenceService();
    }

    // Métodos para obtener los servicios de forma centralizada
    public static PublicacionService getPublicacionService() {
        return publicacionService;
    }

    public static ContactoService getContactoService() {
        return contactoService;
    }

    public static PersistenceService getPersistenceService() {
        return persistenceService;
    }

    // Método para obtener propiedades de configuración
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
