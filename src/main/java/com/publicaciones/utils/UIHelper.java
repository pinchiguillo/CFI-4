package com.publicaciones.utils;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class UIHelper {

    // Actualiza el texto de un JLabel en el hilo de despacho de eventos de Swing
    public static void actualizarEtiqueta(final JLabel label, final String texto) {
        SwingUtilities.invokeLater(() -> label.setText(texto));
    }

    // Centra un componente dentro de su contenedor padre
    public static void centrarComponente(JComponent componente) {
        if (componente.getParent() != null) {
            componente.setLocation(
                    (componente.getParent().getWidth() - componente.getWidth()) / 2,
                    (componente.getParent().getHeight() - componente.getHeight()) / 2
            );
        }
    }
}
