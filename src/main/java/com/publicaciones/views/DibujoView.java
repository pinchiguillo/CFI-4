package com.publicaciones.views;

import com.publicaciones.models.Dibujo;
import com.publicaciones.services.DibujoService;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DibujoView extends JPanel {

    // ==========================
    // Controles superiores
    // ==========================
    private JComboBox<Dibujo> dibujoComboBox;
    private JButton nuevoButton;    // NUEVO: Botón "Crear Nuevo"
    private JButton eliminarButton;
    private JButton guardarButton;

    // ==========================
    // Lógica de dibujo (canvas)
    // ==========================
    private final List<List<Point>> strokes;
    private List<Point> currentStroke;
    private BufferedImage loadedImage;

    // ==========================
    // Servicio para la BD
    // ==========================
    private DibujoService dibujoService;

    public DibujoView() {
        this.dibujoService = new DibujoService();
        this.strokes = new ArrayList<>();
        this.currentStroke = null;
        this.loadedImage = null;

        setBackground(Color.WHITE);
        setDoubleBuffered(true);

        initComponents();
        cargarDibujos(); // Llenar combo
    }

    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(new JLabel("Dibujos guardados:"));

        dibujoComboBox = new JComboBox<>();
        dibujoComboBox.setPreferredSize(new Dimension(200, 25));
        topPanel.add(dibujoComboBox);

        // NUEVO: Botón "Crear Nuevo"
        nuevoButton = new JButton("Crear Nuevo");
        topPanel.add(nuevoButton);

        eliminarButton = new JButton("Eliminar");
        topPanel.add(eliminarButton);

        guardarButton = new JButton("Guardar");
        topPanel.add(guardarButton);

        add(topPanel, BorderLayout.NORTH);

        // Eventos de combo, nuevo, eliminar, guardar
        // 1) ComboBox: Al seleccionar un dibujo, cargar la imagen
        dibujoComboBox.addActionListener(e -> {
            Dibujo seleccionado = (Dibujo) dibujoComboBox.getSelectedItem();
            if (seleccionado != null && seleccionado.getImagen() != null) {
                try {
                    loadedImage = ImageIO.read(new ByteArrayInputStream(seleccionado.getImagen()));
                    strokes.clear(); // Descarta cualquier trazo anterior
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error al cargar la imagen del dibujo.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 2) Botón "Crear Nuevo": pide nombre y crea un dibujo vacío
        nuevoButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre para el nuevo dibujo:",
                    "Crear Nuevo Dibujo",
                    JOptionPane.PLAIN_MESSAGE
            );
            if (nombre != null && !nombre.trim().isEmpty()) {
                // Crear dibujo con imagen vacía (null) o strokes vacíos
                // Almacenar en BD
                Dibujo nuevo = new Dibujo(null, nombre.trim());
                boolean ok = dibujoService.guardarDibujo(nuevo);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Nuevo dibujo creado con nombre: " + nombre);
                    // Recargar combo y seleccionar este dibujo
                    cargarDibujos();
                    seleccionarDibujoPorNombre(nombre.trim());
                    // Limpiamos el canvas (por si había algo dibujado)
                    loadedImage = null;
                    strokes.clear();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al crear el dibujo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 3) Botón "Eliminar"
        eliminarButton.addActionListener(e -> {
            Dibujo seleccionado = (Dibujo) dibujoComboBox.getSelectedItem();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this,
                        "No hay dibujo seleccionado.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            int c = JOptionPane.showConfirmDialog(this,
                    "¿Eliminar el dibujo \"" + seleccionado.getNombreArchivo() + "\"?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                boolean ok = dibujoService.eliminarDibujo(seleccionado.getId());
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Dibujo eliminado.");
                    cargarDibujos();
                    loadedImage = null;
                    strokes.clear();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 4) Botón "Guardar"
        guardarButton.addActionListener(e -> {
            boolean exito = guardarDibujo();
            if (exito) {
                JOptionPane.showMessageDialog(this, "Dibujo guardado/actualizado correctamente.");
                cargarDibujos();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al guardar el dibujo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Busca en el combo un dibujo cuyo nombreArchivo coincida y lo selecciona.
     */
    private void seleccionarDibujoPorNombre(String nombre) {
        ComboBoxModel<Dibujo> model = dibujoComboBox.getModel();
        for(int i=0; i<model.getSize(); i++) {
            Dibujo d = model.getElementAt(i);
            if (d.getNombreArchivo() != null
                    && d.getNombreArchivo().equalsIgnoreCase(nombre)) {
                dibujoComboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    /**
     * Carga la lista de dibujos desde la BD y refresca el combo.
     */
    private void cargarDibujos() {
        List<Dibujo> dibujos = dibujoService.listarDibujos();
        DefaultComboBoxModel<Dibujo> model = new DefaultComboBoxModel<>();
        if (dibujos != null) {
            for (Dibujo d : dibujos) {
                model.addElement(d);
            }
        }
        dibujoComboBox.setModel(model);
    }

    /**
     * Toma el contenido actual (sea loadedImage o strokes) y lo guarda en la BD.
     * Si hay un dibujo seleccionado, se actualiza; si no, se crea uno nuevo con un nombre genérico.
     */
    private boolean guardarDibujo() {
        try {
            // 1. Crear imagen "final" del panel
            BufferedImage imagen = new BufferedImage(
                    getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB
            );
            Graphics2D g2d = imagen.createGraphics();
            paint(g2d);
            g2d.dispose();

            // 2. Convertir a bytes (PNG)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imagen, "png", baos);
            byte[] imagenBytes = baos.toByteArray();
            baos.close();

            // 3. Ver si hay dibujo seleccionado
            Dibujo seleccionado = (Dibujo) dibujoComboBox.getSelectedItem();
            if (seleccionado != null && seleccionado.getId() != null) {
                // Actualizar
                seleccionado.setImagen(imagenBytes);
                // Mantener el nombreArchivo actual
                return dibujoService.guardarDibujo(seleccionado);
            } else {
                // Crear uno nuevo con un nombre genérico
                String nombre = "Nuevo_" + System.currentTimeMillis();
                Dibujo nuevo = new Dibujo(imagenBytes, nombre);
                return dibujoService.guardarDibujo(nuevo);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // ==========================
    // Métodos de dibujo (Paint-like)
    // ==========================
    public void iniciarStroke(Point p) {
        loadedImage = null; // Si empezamos a dibujar, descartamos la imagen
        currentStroke = new ArrayList<>();
        currentStroke.add(p);
        strokes.add(currentStroke);
        repaint();
    }

    public void agregarPunto(Point p) {
        if (currentStroke != null) {
            currentStroke.add(p);
            repaint();
        }
    }

    public void finalizarStroke() {
        currentStroke = null;
    }

    public void limpiarDibujo() {
        loadedImage = null;
        strokes.clear();
        currentStroke = null;
        repaint();
    }

    // ==========================
    // Render
    // ==========================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (loadedImage != null) {
            g.drawImage(loadedImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            for (List<Point> stroke : strokes) {
                if (stroke.size() > 1) {
                    for (int i = 1; i < stroke.size(); i++) {
                        Point p1 = stroke.get(i - 1);
                        Point p2 = stroke.get(i);
                        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                } else if (!stroke.isEmpty()) {
                    Point p = stroke.get(0);
                    g2d.fillOval(p.x, p.y, 5, 5);
                }
            }
        }
    }
}
