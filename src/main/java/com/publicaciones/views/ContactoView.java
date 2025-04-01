package com.publicaciones.views;

import javax.swing.*;
import java.awt.*;

public class ContactoView extends JPanel {

    private JTextField nombreField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JButton guardarButton;

    public ContactoView() {
        setLayout(new GridLayout(4, 2));

        nombreField = new JTextField();
        emailField = new JTextField();
        telefonoField = new JTextField();
        guardarButton = new JButton("Guardar Contacto");

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Teléfono:"));
        add(telefonoField);
        add(new JLabel(""));
        add(guardarButton);
    }
}
