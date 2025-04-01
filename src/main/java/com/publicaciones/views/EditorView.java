package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;

public class EditorView extends JPanel {

    private JTextArea textArea;
    private JButton saveButton;
    private JButton newButton;
    private JButton loadButton;
    private JTextField loadIdField;

    public EditorView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior para "Nuevo" y "Cargar"
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newButton = new JButton("Nuevo Documento");
        topPanel.add(newButton);

        topPanel.add(new JLabel("ID Documento:"));
        loadIdField = new JTextField(10);
        topPanel.add(loadIdField);
        loadButton = new JButton("Cargar Documento");
        topPanel.add(loadButton);

        add(topPanel, BorderLayout.NORTH);

        // Área central para editar el contenido
        textArea = new JTextArea(20, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para el botón "Guardar"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Guardar Documento");
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getters para que el controlador pueda registrar listeners
    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getNewButton() {
        return newButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JTextField getLoadIdField() {
        return loadIdField;
    }
}
