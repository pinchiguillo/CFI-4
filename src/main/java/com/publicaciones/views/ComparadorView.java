package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;

public class ComparadorView extends JPanel {

    private JTextArea file1Text;
    private JTextArea file2Text;
    private JButton compareButton;

    public ComparadorView() {
        setLayout(new GridLayout(2, 1));

        file1Text = new JTextArea("Archivo 1");
        file2Text = new JTextArea("Archivo 2");
        compareButton = new JButton("Comparar");

        add(new JScrollPane(file1Text));
        add(new JScrollPane(file2Text));
        add(compareButton, BorderLayout.SOUTH);
    }

    public JButton getCompareButton() {
        return compareButton;
    }
}
