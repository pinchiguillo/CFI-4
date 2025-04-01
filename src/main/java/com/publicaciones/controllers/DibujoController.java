package com.publicaciones.controllers;

import com.publicaciones.views.DibujoView;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DibujoController {

    private DibujoView dibujoView;

    public DibujoController(DibujoView dibujoView) {
        this.dibujoView = dibujoView;
        configurarEventos();
    }

    // Configura los eventos del ratón en el lienzo de dibujo
    private void configurarEventos() {
        // Si se desea centralizar la lógica de dibujo en el controlador,
        // se pueden agregar o reemplazar los eventos del MouseAdapter aquí.
        dibujoView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Aquí se puede iniciar la lógica de dibujo o delegar a la vista.
                // Por ejemplo: dibujoView.iniciarDibujo(e.getX(), e.getY());
                // Actualmente, la vista ya registra el punto y repinta.
            }
        });

        dibujoView.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Aquí se puede actualizar la lógica de dibujo o delegar a la vista.
                // Por ejemplo: dibujoView.dibujar(e.getX(), e.getY());
                // Si la vista ya maneja esto, se puede omitir.
            }
        });
    }

    // Guarda o exporta el dibujo actual a la ruta indicada, capturando la imagen del panel.
    public boolean guardarDibujo(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.isEmpty()) {
            return false;
        }
        // Crear una imagen con el tamaño del panel de dibujo.
        BufferedImage imagen = new BufferedImage(dibujoView.getWidth(), dibujoView.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();
        // Se pinta el contenido actual del panel en la imagen.
        dibujoView.paint(g2d);
        g2d.dispose();
        try {
            // Se guarda la imagen en el archivo indicado (formato PNG).
            ImageIO.write(imagen, "png", new File(rutaArchivo));
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
