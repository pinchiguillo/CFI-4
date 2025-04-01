package com.publicaciones.views;

import com.publicaciones.controllers.ContactoController;
import javax.swing.*;
import java.awt.*;

public class ContactoView extends JPanel {

    private JTextField nombreField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JButton guardarButton;

    // Instancia del controlador para gestionar la lógica de persistencia
    private ContactoController contactoController;

    public ContactoView() {
        // Se inicializa la vista y se crea el controlador
        contactoController = new ContactoController();
        initComponents();
        configurarEventos();
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 2, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        add(new JLabel(""));  // Espacio vacío para alinear el botón
        add(guardarButton);
    }

    private void configurarEventos() {
        guardarButton.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String email = emailField.getText().trim();
            String telefono = telefonoField.getText().trim();

            // Llama al controlador para crear el contacto
            boolean creado = contactoController.crearContacto(nombre, email, telefono);
            if (creado) {
                JOptionPane.showMessageDialog(this, "Contacto guardado exitosamente.");
                // Limpiar campos después de guardar
                nombreField.setText("");
                emailField.setText("");
                telefonoField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error: Email inválido o no se pudo guardar el contacto.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Métodos de acceso (opcional, según se necesite en la integración)
    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getTelefonoField() {
        return telefonoField;
    }

    public JButton getGuardarButton() {
        return guardarButton;
    }
}
