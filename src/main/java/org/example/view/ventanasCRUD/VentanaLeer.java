package org.example.view.ventanasCRUD;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public abstract class VentanaLeer<T> extends JPanel {
    protected JTable tabla;
    protected DefaultTableModel modeloTabla;

    public VentanaLeer(String titulo, String[] columnas) {
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Lista de " + titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    protected abstract List<T> obtenerDatos();
    protected abstract Object[] mapearFila(T objeto);

    public void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<T> datos = obtenerDatos();
        for (T objeto : datos) {
            modeloTabla.addRow(mapearFila(objeto));
        }
    }
}
