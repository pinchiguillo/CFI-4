package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;

public class ComparadorView extends JPanel {

    private JTextField docIdField1;
    private JTextField docIdField2;
    private JButton compareButton;
    private JTextArea resultArea;

    public ComparadorView() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del layout principal
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior para ingresar los IDs de los documentos
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Documento ID 1:"));
        docIdField1 = new JTextField(10);
        inputPanel.add(docIdField1);
        inputPanel.add(new JLabel("Documento ID 2:"));
        docIdField2 = new JTextField(10);
        inputPanel.add(docIdField2);
        add(inputPanel, BorderLayout.NORTH);

        // Área central para mostrar el resultado de la comparación
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Diferencias"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con el botón para iniciar la comparación
        compareButton = new JButton("Comparar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(compareButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Métodos de acceso para el controlador
    public JTextField getDocIdField1() {
        return docIdField1;
    }

    public JTextField getDocIdField2() {
        return docIdField2;
    }

    public JButton getCompareButton() {
        return compareButton;
    }

    public JTextArea getResultArea() {
        return resultArea;
    }
}
