package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoView extends JPanel {

    // Lista de trazos: cada trazo es una lista de puntos.
    private final List<List<Point>> strokes;
    // Referencia al trazo actual (nulo cuando no se está dibujando).
    private List<Point> currentStroke;

    public DibujoView() {
        strokes = new ArrayList<>();
        currentStroke = null;
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
    }

    // Inicia un nuevo trazo en el punto de inicio
    public void iniciarStroke(Point p) {
        currentStroke = new ArrayList<>();
        currentStroke.add(p);
        strokes.add(currentStroke);
        repaint();
    }

    // Agrega un nuevo punto al trazo actual (si existe)
    public void agregarPunto(Point p) {
        if (currentStroke != null) {
            currentStroke.add(p);
            repaint();
        }
    }

    // Finaliza el trazo actual (ya no se conectará con el siguiente)
    public void finalizarStroke() {
        currentStroke = null;
    }

    // Limpia el dibujo (todos los trazos)
    public void limpiarDibujo() {
        strokes.clear();
        currentStroke = null;
        repaint();
    }

    // Retorna una copia de los trazos (si es necesario)
    public List<List<Point>> getStrokes() {
        return new ArrayList<>(strokes);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Se usa Graphics2D para un mejor control
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        // Recorre cada trazo y dibuja líneas entre puntos consecutivos
        for (List<Point> stroke : strokes) {
            if (stroke.size() > 1) {
                for (int i = 1; i < stroke.size(); i++) {
                    Point p1 = stroke.get(i - 1);
                    Point p2 = stroke.get(i);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            } else if (!stroke.isEmpty()) {
                // Si hay un solo punto, lo dibuja como pequeño óvalo
                Point p = stroke.get(0);
                g2d.fillOval(p.x, p.y, 5, 5);
            }
        }
    }
}
