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

    // Abre una nueva ventana interna y la agrega a la lista de ventanas abiertas.
    public void abrirVentana(JInternalFrame ventana) {
        if (ventana == null) {
            return;
        }
        ventanasAbiertas.add(ventana);
        desktop.add(ventana);
        ventana.setVisible(true);
    }

    // Cierra la ventana especificada y la elimina de la lista.
    public void cerrarVentana(JInternalFrame ventana) {
        if (ventana == null) {
            return;
        }
        ventana.dispose();
        ventanasAbiertas.remove(ventana);
    }

    // Cierra todas las ventanas abiertas.
    public void cerrarTodasVentanas() {
        // Se crea una copia para evitar ConcurrentModificationException.
        List<JInternalFrame> copiaVentanas = new ArrayList<>(ventanasAbiertas);
        for (JInternalFrame ventana : copiaVentanas) {
            cerrarVentana(ventana);
        }
    }

    // Devuelve una copia de la lista de ventanas abiertas.
    public List<JInternalFrame> getVentanasAbiertas() {
        return new ArrayList<>(ventanasAbiertas);
    }
}
