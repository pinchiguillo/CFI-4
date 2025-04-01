package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DibujoView extends JPanel {

    private final List<Point> puntos = new ArrayList<>();

    public DibujoView() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                puntos.add(e.getPoint());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point p : puntos) {
            g.fillOval(p.x, p.y, 5, 5);
        }
    }
}
