package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoView extends JPanel {

    private final List<Point> puntos;

    public DibujoView() {
        // Inicializa la lista de puntos y configura el fondo
        this.puntos = new ArrayList<>();
        setBackground(Color.WHITE);
    }

    // Método para agregar un punto al dibujo
    public void agregarPunto(Point p) {
        if (p != null) {
            puntos.add(p);
            repaint();
        }
    }

    // Método para limpiar el dibujo
    public void limpiarDibujo() {
        puntos.clear();
        repaint();
    }

    // Método para obtener una copia de los puntos actuales
    public List<Point> getPuntos() {
        return new ArrayList<>(puntos);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja cada punto como un óvalo pequeño
        for (Point p : puntos) {
            g.fillOval(p.x, p.y, 5, 5);
        }
    }
}
