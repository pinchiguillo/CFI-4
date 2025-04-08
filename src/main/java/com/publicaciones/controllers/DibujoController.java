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

    // Configura los eventos del ratón para dibujar trazos
    private void configurarEventos() {
        dibujoView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Inicia un nuevo trazo en el punto donde se presiona el botón
                dibujoView.iniciarStroke(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Finaliza el trazo actual; la próxima pulsación inicia un nuevo trazo
                dibujoView.finalizarStroke();
            }
        });

        dibujoView.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Mientras se arrastra, se agregan puntos al trazo actual
                dibujoView.agregarPunto(e.getPoint());
            }
        });
    }

    // Guarda la imagen actual del panel de dibujo en la ruta indicada (formato PNG)
    public boolean guardarDibujo(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.isEmpty()) {
            return false;
        }
        // Crea una imagen con el tamaño actual del panel
        BufferedImage imagen = new BufferedImage(dibujoView.getWidth(), dibujoView.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();
        // Pinta el contenido actual del panel en la imagen
        dibujoView.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(imagen, "png", new File(rutaArchivo));
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
