package com.publicaciones.views;

import com.publicaciones.models.Documento;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BuscadorView extends JPanel {

    private JComboBox<Documento> documentoComboBox;
    private JTextField palabraField;
    private JButton buscarButton;
    private JTextArea resultadoArea;

    public BuscadorView() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del layout y márgenes
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior para selección de documento y búsqueda
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Dropdown para seleccionar el documento
        JLabel documentoLabel = new JLabel("Documento:");
        documentoComboBox = new JComboBox<>();
        topPanel.add(documentoLabel);
        topPanel.add(documentoComboBox);

        // Campo para ingresar la palabra a buscar
        JLabel palabraLabel = new JLabel("Buscar palabra:");
        palabraField = new JTextField(20);
        topPanel.add(palabraLabel);
        topPanel.add(palabraField);

        // Botón para iniciar la búsqueda
        buscarButton = new JButton("Buscar");
        buscarButton.setToolTipText("Haga clic para buscar la palabra en el documento seleccionado");
        topPanel.add(buscarButton);

        // Área de resultados con scroll
        resultadoArea = new JTextArea(15, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados"));

        // Se agregan los componentes al panel principal
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Método para poblar el dropdown con la lista de documentos recuperados
    public void setDocumentos(List<Documento> documentos) {
        DefaultComboBoxModel<Documento> model = new DefaultComboBoxModel<>();
        if (documentos != null) {
            for (Documento doc : documentos) {
                model.addElement(doc);
            }
        }
        documentoComboBox.setModel(model);
    }

    // Getters para que el controlador pueda registrar listeners y acceder a los elementos
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
}
