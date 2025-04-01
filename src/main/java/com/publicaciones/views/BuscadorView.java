package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;

public class BuscadorView extends JPanel {

    private JTextField palabraField;
    private JButton buscarButton;
    private JTextArea resultadoArea;

    public BuscadorView() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del layout y márgenes
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior para la búsqueda
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel palabraLabel = new JLabel("Buscar palabra:");
        palabraField = new JTextField(20);
        buscarButton = new JButton("Buscar");
        buscarButton.setToolTipText("Haga clic para buscar la palabra en el documento");

        topPanel.add(palabraLabel);
        topPanel.add(palabraField);
        topPanel.add(buscarButton);

        // Área de resultados con título y scroll
        resultadoArea = new JTextArea(15, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados"));

        // Se agregan los componentes al panel principal
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Métodos de acceso para el controlador
    public JTextField getPalabraField() {
        return palabraField;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTextArea getResultadoArea() {
        return resultadoArea;
    }
}
