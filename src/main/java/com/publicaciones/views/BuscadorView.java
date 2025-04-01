package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;

public class BuscadorView extends JPanel {

    private JTextField palabraField;
    private JButton buscarButton;
    private JTextArea resultadoArea;

    public BuscadorView() {
        setLayout(new BorderLayout());

        palabraField = new JTextField(15);
        buscarButton = new JButton("Buscar");
        resultadoArea = new JTextArea();

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Palabra:"));
        topPanel.add(palabraField);
        topPanel.add(buscarButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
    }
}
