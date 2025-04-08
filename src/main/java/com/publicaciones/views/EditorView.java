package com.publicaciones.views;

import com.publicaciones.controllers.EditorController;
import com.publicaciones.models.Documento;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditorView extends JPanel {

    private JTextArea textArea;
    private JButton saveButton;
    private JButton newButton;
    private JComboBox<Documento> fileComboBox;

    private EditorController editorController;

    public EditorView() {
        editorController = new EditorController();
        initComponents();
        cargarDocumentosEnCombo();  // Al inicio no forzamos ningún doc
        configurarEventos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newButton = new JButton("Nuevo Documento");
        topPanel.add(newButton);

        fileComboBox = new JComboBox<>();
        topPanel.add(new JLabel("Archivos guardados:"));
        topPanel.add(fileComboBox);

        add(topPanel, BorderLayout.NORTH);

        textArea = new JTextArea(20, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Guardar Documento");
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Versión sin parámetro, para uso inicial
    private void cargarDocumentosEnCombo() {
        cargarDocumentosEnCombo(null);
    }

    // Versión con parámetro: recarga el combo y selecciona docToSelect (si existe)
    private void cargarDocumentosEnCombo(Documento docToSelect) {
        fileComboBox.removeAllItems();
        List<Documento> docs = editorController.listarDocumentos();

        Documento docEncontrado = null;

        for (Documento doc : docs) {
            fileComboBox.addItem(doc);
            if (docToSelect != null
                    && docToSelect.getId() != null
                    && doc.getId() != null
                    && doc.getId().equals(docToSelect.getId())) {
                docEncontrado = doc;
            }
        }

        if (docEncontrado != null) {
            fileComboBox.setSelectedItem(docEncontrado);
        }
    }

    private void configurarEventos() {
        // Botón Nuevo
        newButton.addActionListener(e -> {
            String nombreArchivo = JOptionPane.showInputDialog(
                    EditorView.this,
                    "Ingrese el nombre del archivo (ej. documento.txt):"
            );
            if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
                editorController.crearDocumento(nombreArchivo.trim(), "");
                boolean guardado = editorController.guardarDocumento(nombreArchivo.trim());

                if (guardado) {
                    Documento docRecienCreado = editorController.getDocumento();
                    textArea.setText("");

                    // Refresca combo y selecciona el nuevo doc
                    cargarDocumentosEnCombo(docRecienCreado);

                    JOptionPane.showMessageDialog(
                            EditorView.this,
                            "Nuevo documento creado y guardado: " + nombreArchivo
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            EditorView.this,
                            "Error al guardar el nuevo documento.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        EditorView.this,
                        "Debe ingresar un nombre de archivo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Botón Guardar
        saveButton.addActionListener(e -> {
            editorController.editarDocumento(textArea.getText());
            Documento docActual = editorController.getDocumento();
            if (docActual == null) {
                JOptionPane.showMessageDialog(
                        EditorView.this,
                        "No hay ningún documento cargado.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String nombreActual = docActual.getNombreArchivo();
            if (nombreActual == null || nombreActual.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        EditorView.this,
                        "El documento no tiene nombre. Use 'Nuevo Documento' para crearlo correctamente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            boolean guardado = editorController.guardarDocumento(nombreActual.trim());
            if (guardado) {
                // Tras guardar, nos aseguramos de re-seleccionar el doc actual
                cargarDocumentosEnCombo(docActual);
                JOptionPane.showMessageDialog(
                        EditorView.this,
                        "Documento guardado: " + nombreActual
                );
            } else {
                JOptionPane.showMessageDialog(
                        EditorView.this,
                        "Error al guardar el documento.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Al cambiar la selección en el combo, cargamos el documento
        fileComboBox.addActionListener(e -> {
            Documento docSeleccionado = (Documento) fileComboBox.getSelectedItem();
            if (docSeleccionado != null) {
                if (editorController.cargarDocumento(docSeleccionado.getNombreArchivo())) {
                    textArea.setText(editorController.getDocumento().getContenido());
                } else {
                    textArea.setText("");
                    JOptionPane.showMessageDialog(
                            EditorView.this,
                            "No se pudo cargar: " + docSeleccionado.getNombreArchivo(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    // Getters (opcionales)
    public JTextArea getTextArea() {
        return textArea;
    }

    public JComboBox<Documento> getFileComboBox() {
        return fileComboBox;
    }
}
