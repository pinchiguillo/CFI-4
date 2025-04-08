package com.publicaciones.views;

import com.publicaciones.controllers.EditorController;
import com.publicaciones.models.Documento;
import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class BuscadorView extends JPanel {

    private JComboBox<Documento> documentoComboBox;
    private JTextField palabraField;
    private JButton buscarButton;
    private JTextArea resultadoArea;

    // Almacena el término de búsqueda actual para usarlo al cargar el documento
    private String currentSearchTerm = "";

    // Se usará para obtener todos los documentos y filtrar aquellos que contienen la cadena.
    private EditorController editorController;

    public BuscadorView() {
        editorController = new EditorController();
        initComponents();
        configurarEventos();
    }

    private void initComponents() {
        // Configuración del layout y márgenes
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior: campo de texto, botón y dropdown
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Campo para ingresar la cadena a buscar
        JLabel palabraLabel = new JLabel("Buscar palabra:");
        palabraField = new JTextField(20);
        topPanel.add(palabraLabel);
        topPanel.add(palabraField);

        // Botón para iniciar la búsqueda
        buscarButton = new JButton("Buscar");
        buscarButton.setToolTipText("Presione para filtrar los documentos que contienen la cadena");
        topPanel.add(buscarButton);

        // Dropdown para mostrar los documentos filtrados
        JLabel docLabel = new JLabel("Documentos encontrados:");
        documentoComboBox = new JComboBox<>();
        topPanel.add(docLabel);
        topPanel.add(documentoComboBox);

        add(topPanel, BorderLayout.NORTH);

        // Área de resultados para mostrar el contenido del documento con la cadena resaltada
        resultadoArea = new JTextArea(15, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Documento"));
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Configura los eventos:
     * - Al pulsar "Buscar": se obtiene la cadena, se limpia el área de resultados,
     *   se filtran los documentos disponibles y se actualiza el dropdown.
     * - Al seleccionar un documento en el dropdown: se carga su contenido en el área de resultados
     *   y se resaltan las ocurrencias del término de búsqueda.
     */
    private void configurarEventos() {
        buscarButton.addActionListener(e -> {
            currentSearchTerm = palabraField.getText().trim();
            // Limpia el área de resultados solo al pulsar buscar
            resultadoArea.setText("");
            if (currentSearchTerm.isEmpty()) {
                JOptionPane.showMessageDialog(BuscadorView.this,
                        "Ingrese una cadena para buscar.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Filtra los documentos que contienen currentSearchTerm en su contenido
            List<Documento> docsFiltrados = filtrarDocumentos(currentSearchTerm);
            setDocumentos(docsFiltrados);
        });

        // Al seleccionar un documento del dropdown, se carga y resalta su contenido
        documentoComboBox.addActionListener(e -> {
            Documento docSeleccionado = (Documento) documentoComboBox.getSelectedItem();
            if (docSeleccionado != null) {
                resultadoArea.setText(docSeleccionado.getContenido());
                highlightText(resultadoArea, currentSearchTerm);
            }
        });
    }

    /**
     * Filtra los documentos existentes y retorna aquellos cuyo contenido contiene el término (ignora mayúsculas).
     */
    private List<Documento> filtrarDocumentos(String termino) {
        List<Documento> todos = editorController.listarDocumentos();
        String terminoLower = termino.toLowerCase();
        return todos.stream()
                .filter(doc -> doc.getContenido() != null &&
                        doc.getContenido().toLowerCase().contains(terminoLower))
                .collect(Collectors.toList());
    }

    /**
     * Pone la lista de documentos en el combo.
     */
    public void setDocumentos(List<Documento> documentos) {
        DefaultComboBoxModel<Documento> model = new DefaultComboBoxModel<>();
        if (documentos != null) {
            for (Documento doc : documentos) {
                model.addElement(doc);
            }
        }
        documentoComboBox.setModel(model);
    }

    public JComboBox<Documento> getDocumentoComboBox() {
        return documentoComboBox;
    }

    public JTextField getPalabraField() {
        return palabraField;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTextArea getResultadoArea() {
        return resultadoArea;
    }

    /**
     * Resalta todas las ocurrencias de 'pattern' en el JTextArea usando color amarillo.
     */
    private void highlightText(JTextArea textArea, String pattern) {
        try {
            Highlighter hilite = textArea.getHighlighter();
            hilite.removeAllHighlights();
            if (pattern == null || pattern.isEmpty()) {
                return;
            }
            Highlighter.HighlightPainter painter =
                    new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            String text = textArea.getText();
            int pos = 0;
            // Búsqueda insensible a mayúsculas/minúsculas
            String lowerText = text.toLowerCase();
            String lowerPattern = pattern.toLowerCase();
            while ((pos = lowerText.indexOf(lowerPattern, pos)) >= 0) {
                hilite.addHighlight(pos, pos + pattern.length(), painter);
                pos += pattern.length();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
