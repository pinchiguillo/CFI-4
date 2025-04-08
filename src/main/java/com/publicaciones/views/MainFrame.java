package com.publicaciones.views;

import com.publicaciones.controllers.BuscadorController;
import com.publicaciones.controllers.ComparadorController;
import com.publicaciones.controllers.DibujoController;
import com.publicaciones.controllers.EditorController;
import com.publicaciones.models.Documento;   // <-- Importación necesaria
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
        // Se instancia la vista del editor, la cual ya contiene la configuración de eventos
        EditorView editorView = new EditorView();
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
            Documento doc = (Documento) buscadorView.getDocumentoComboBox().getSelectedItem();
            if (doc == null) {
                JOptionPane.showMessageDialog(buscadorView, "Seleccione un documento.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String palabra = buscadorView.getPalabraField().getText().trim();
            try {
                java.util.List<Integer> indices = buscadorController.buscarPalabra(doc.getId(), palabra);
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
        ContactoView contactoView = new ContactoView();
        tabbedPane.addTab("Contactos", contactoView);

        // ---------------------------
        // Dibujo Tab
        // ---------------------------
        DibujoView dibujoView = new DibujoView();
        DibujoController dibujoController = new DibujoController(dibujoView);
        JPanel dibujoPanel = new JPanel(new BorderLayout());
        dibujoPanel.add(dibujoView, BorderLayout.CENTER);
        JPanel dibujoButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton limpiarButton = new JButton("Limpiar Dibujo");
        JButton guardarDibujoButton = new JButton("Guardar Dibujo");
        dibujoButtonsPanel.add(limpiarButton);
        dibujoButtonsPanel.add(guardarDibujoButton);
        dibujoPanel.add(dibujoButtonsPanel, BorderLayout.SOUTH);
        limpiarButton.addActionListener(e -> dibujoView.limpiarDibujo());
        guardarDibujoButton.addActionListener(e -> {
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
