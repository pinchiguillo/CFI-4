package com.publicaciones.views;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sistema de Gestión de Publicaciones");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Editor", new EditorView());
        tabbedPane.addTab("Comparador", new ComparadorView());
        tabbedPane.addTab("Buscador", new BuscadorView());
        tabbedPane.addTab("Contactos", new ContactoView());
        tabbedPane.addTab("Dibujo", new DibujoView());

        add(tabbedPane);
    }
}
