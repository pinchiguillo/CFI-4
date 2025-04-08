package com.publicaciones.views;

import com.publicaciones.models.Documento;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComparadorView extends JPanel {

    // Se sustituyen los JTextField por JComboBox de Documento
    private JComboBox<Documento> documentoComboBox1;
    private JComboBox<Documento> documentoComboBox2;
    private JButton compareButton;
    private JTextArea resultArea;

    public ComparadorView() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del layout principal
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior para la selección de documentos mediante dropdowns
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Documento 1:"));
        documentoComboBox1 = new JComboBox<>();
        inputPanel.add(documentoComboBox1);
        inputPanel.add(new JLabel("Documento 2:"));
        documentoComboBox2 = new JComboBox<>();
        inputPanel.add(documentoComboBox2);
        add(inputPanel, BorderLayout.NORTH);

        // Área central para mostrar el resultado de la comparación
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Diferencias"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con el botón para iniciar la comparación
        compareButton = new JButton("Comparar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(compareButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Método para poblar ambos dropdowns con la lista de documentos.
     * Se utiliza el método toString() de Documento para mostrar la información de cada item.
     */
    public void setDocumentos(List<Documento> documentos) {
        DefaultComboBoxModel<Documento> model1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Documento> model2 = new DefaultComboBoxModel<>();
        if (documentos != null) {
            for (Documento doc : documentos) {
                model1.addElement(doc);
                model2.addElement(doc);
            }
        }
        documentoComboBox1.setModel(model1);
        documentoComboBox2.setModel(model2);
    }

    // Getters para que el controlador acceda a cada uno de los componentes
    public JComboBox<Documento> getDocumentoComboBox1() {
        return documentoComboBox1;
    }

    public JComboBox<Documento> getDocumentoComboBox2() {
        return documentoComboBox2;
    }

    public JButton getCompareButton() {
        return compareButton;
    }

    public JTextArea getResultArea() {
        return resultArea;
    }
}
