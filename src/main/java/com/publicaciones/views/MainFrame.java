package com.publicaciones.views;

import com.publicaciones.controllers.BuscadorController;
import com.publicaciones.controllers.ComparadorController;
import com.publicaciones.controllers.DibujoController;
import com.publicaciones.controllers.EditorController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sistema de Gestión de Publicaciones");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // ---------------------------
        // Editor Tab
        // ---------------------------
        EditorView editorView = new EditorView();
        EditorController editorController = new EditorController();
        // Nuevo Documento: limpia el área y crea un documento vacío.
        editorView.getNewButton().addActionListener(e -> {
            editorController.crearDocumento("");
            editorView.getTextArea().setText("");
        });
        // Cargar Documento: lee el ID, carga el documento y muestra su contenido.
        editorView.getLoadButton().addActionListener(e -> {
            try {
                Long id = Long.parseLong(editorView.getLoadIdField().getText().trim());
                if (editorController.cargarDocumento(id)) {
                    editorView.getTextArea().setText(editorController.getDocumento().getContenido());
                    JOptionPane.showMessageDialog(editorView, "Documento cargado.");
                } else {
                    JOptionPane.showMessageDialog(editorView, "Documento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editorView, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Guardar Documento: actualiza el contenido y guarda en la base de datos.
        editorView.getSaveButton().addActionListener(e -> {
            editorController.editarDocumento(editorView.getTextArea().getText());
            if (editorController.guardarDocumento()) {
                JOptionPane.showMessageDialog(editorView, "Documento guardado.");
            } else {
                JOptionPane.showMessageDialog(editorView, "Error al guardar el documento.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        tabbedPane.addTab("Editor", editorView);

        // ---------------------------
        // Comparador Tab
        // ---------------------------
        ComparadorView comparadorView = new ComparadorView();
        ComparadorController comparadorController = new ComparadorController();
        comparadorView.getCompareButton().addActionListener(e -> {
            try {
                Long id1 = Long.parseLong(comparadorView.getDocIdField1().getText().trim());
                Long id2 = Long.parseLong(comparadorView.getDocIdField2().getText().trim());
                java.util.List<String> diffs = comparadorController.compararDocumentos(id1, id2);
                StringBuilder sb = new StringBuilder();
                for (String diff : diffs) {
                    sb.append(diff).append("\n");
                }
                comparadorView.getResultArea().setText(sb.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(comparadorView, "IDs inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(comparadorView, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        tabbedPane.addTab("Comparador", comparadorView);

        // ---------------------------
        // Buscador Tab
        // ---------------------------
        BuscadorView buscadorView = new BuscadorView();
        BuscadorController buscadorController = new BuscadorController();
        buscadorView.getBuscarButton().addActionListener(e -> {
            // Para este ejemplo se usa un ID fijo (por ejemplo, 1L).
            // En una aplicación real se podría agregar un campo para ingresar el ID del documento.
            Long docId = 1L;
            String palabra = buscadorView.getPalabraField().getText().trim();
            try {
                java.util.List<Integer> indices = buscadorController.buscarPalabra(docId, palabra);
                StringBuilder sb = new StringBuilder("Índices: ").append(indices);
                buscadorView.getResultadoArea().setText(sb.toString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(buscadorView, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        tabbedPane.addTab("Buscador", buscadorView);

        // ---------------------------
        // Contactos Tab
        // ---------------------------
        // La vista de contactos ya incluye su propio controlador internamente.
        ContactoView contactoView = new ContactoView();
        tabbedPane.addTab("Contactos", contactoView);

        // ---------------------------
        // Dibujo Tab
        // ---------------------------
        DibujoView dibujoView = new DibujoView();
        DibujoController dibujoController = new DibujoController(dibujoView);
        // Panel para botones de acción en el dibujo
        JPanel dibujoPanel = new JPanel(new BorderLayout());
        dibujoPanel.add(dibujoView, BorderLayout.CENTER);
        JPanel dibujoButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton limpiarButton = new JButton("Limpiar Dibujo");
        JButton guardarButton = new JButton("Guardar Dibujo");
        dibujoButtonsPanel.add(limpiarButton);
        dibujoButtonsPanel.add(guardarButton);
        dibujoPanel.add(dibujoButtonsPanel, BorderLayout.SOUTH);
        // Eventos para limpiar y guardar el dibujo
        limpiarButton.addActionListener(e -> dibujoView.limpiarDibujo());
        guardarButton.addActionListener(e -> {
            String ruta = JOptionPane.showInputDialog(dibujoView, "Ingrese la ruta de guardado (ej. dibujo.png):");
            if (ruta != null && !ruta.trim().isEmpty()) {
                boolean guardado = dibujoController.guardarDibujo(ruta.trim());
                if (guardado) {
                    JOptionPane.showMessageDialog(dibujoView, "Dibujo guardado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(dibujoView, "Error al guardar el dibujo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tabbedPane.addTab("Dibujo", dibujoPanel);

        add(tabbedPane);
    }
}
