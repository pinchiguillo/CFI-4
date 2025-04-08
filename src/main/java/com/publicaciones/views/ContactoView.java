package com.publicaciones.views;

import com.publicaciones.controllers.ContactoController;
import com.publicaciones.models.Contacto;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ContactoView extends JPanel {

    private JTextField txtSearch;
    private JButton addButton;
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private ContactoController contactoController;

    public ContactoView() {
        contactoController = new ContactoController();
        initComponents();
        loadContacts();
        configurarEventos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior: Buscador dinámico y botón "Añadir"
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Buscar:"));
        txtSearch = new JTextField(20);
        topPanel.add(txtSearch);
        addButton = new JButton("Añadir");
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        // Tabla para listar los contactos (sin columna ID)
        tableModel = new DefaultTableModel(new Object[]{"Nombre", "Email", "Teléfono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // la tabla es de solo lectura
            }
        };
        contactTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Carga todos los contactos en la tabla
    private void loadContacts() {
        List<Contacto> contactos = contactoController.obtenerContactos();
        updateTable(contactos);
    }

    // Actualiza la tabla con la lista proporcionada sin mostrar el ID
    private void updateTable(List<Contacto> contactos) {
        tableModel.setRowCount(0); // limpia la tabla
        for (Contacto c : contactos) {
            tableModel.addRow(new Object[]{c.getNombre(), c.getEmail(), c.getTelefono()});
        }
    }

    private void configurarEventos() {
        // Listener para búsqueda dinámica: actualiza la tabla a medida que se escribe
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterContacts();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                filterContacts();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                filterContacts();
            }
        });

        // Botón "Añadir": abre un popup para crear un nuevo contacto
        addButton.addActionListener(e -> showAddContactPopup());
    }

    // Filtra la lista de contactos según lo escrito en el campo de búsqueda y actualiza la tabla
    private void filterContacts() {
        String filter = txtSearch.getText().trim().toLowerCase();
        List<Contacto> contactos = contactoController.obtenerContactos();
        if (!filter.isEmpty()) {
            contactos = contactos.stream()
                    .filter(c -> c.getNombre() != null && c.getNombre().toLowerCase().contains(filter))
                    .collect(Collectors.toList());
        }
        updateTable(contactos);
    }

    // Muestra un popup para añadir un nuevo contacto
    private void showAddContactPopup() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField nombreField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telefonoField = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Añadir Contacto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText().trim();
            String email = emailField.getText().trim();
            String telefono = telefonoField.getText().trim();
            if (nombre.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre y email son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean creado = contactoController.crearContacto(nombre, email, telefono);
            if (creado) {
                JOptionPane.showMessageDialog(this, "Contacto creado exitosamente.");
                loadContacts(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el contacto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
