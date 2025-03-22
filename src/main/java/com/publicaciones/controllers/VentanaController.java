package com.publicaciones.controllers;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.util.ArrayList;
import java.util.List;

public class VentanaController {

    private JDesktopPane desktop;
    private List<JInternalFrame> ventanasAbiertas;

    public VentanaController(JDesktopPane desktop) {
        this.desktop = desktop;
        this.ventanasAbiertas = new ArrayList<>();
    }

    // Abre una nueva ventana interna y la agrega a la lista de ventanas abiertas
    public void abrirVentana(JInternalFrame ventana) {
        ventanasAbiertas.add(ventana);
        desktop.add(ventana);
        ventana.setVisible(true);
    }

    // Cierra la ventana especificada y la elimina de la lista
    public void cerrarVentana(JInternalFrame ventana) {
        ventana.dispose();
        ventanasAbiertas.remove(ventana);
    }

    // Devuelve la lista de ventanas abiertas
    public List<JInternalFrame> getVentanasAbiertas() {
        return ventanasAbiertas;
    }
}
