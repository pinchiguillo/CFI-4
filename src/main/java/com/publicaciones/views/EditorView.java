package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;

public class EditorView extends JPanel {

    private JTextArea textArea;
    private JButton saveButton;

    public EditorView() {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        saveButton = new JButton("Guardar");

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
