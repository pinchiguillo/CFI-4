package com.publicaciones.controllers;

import com.publicaciones.views.DibujoView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DibujoController {

    private DibujoView dibujoView;

    public DibujoController(DibujoView dibujoView) {
        this.dibujoView = dibujoView;
        configurarEventos();
    }

    // Configura los eventos del ratón en el lienzo de dibujo
    private void configurarEventos() { //TODO not implemented
        //dibujoView.getLienzo().addMouseListener(new MouseAdapter() {
        //    @Override
        //    public void mousePressed(MouseEvent e) {
        //        //dibujoView.iniciarDibujo(e.getX(), e.getY());
        //    }
        //});

        //dibujoView.getLienzo().addMouseMotionListener(new MouseAdapter() {
        //    @Override
        //    public void mouseDragged(MouseEvent e) {
        //        dibujoView.dibujar(e.getX(), e.getY());
        //    }
        //});
    }

    // Guarda o exporta el dibujo actual a la ruta indicada
    public boolean guardarDibujo(String rutaArchivo) { //TODO not implemented
        //return dibujoView.guardarLienzo(rutaArchivo);
        return false;
    }
}
