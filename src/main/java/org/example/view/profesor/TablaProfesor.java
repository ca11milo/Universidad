package org.example.view.profesor;

import org.example.controller.ProfesorController;
import org.example.model.Profesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaProfesor extends JPanel {
    private ProfesorController profesorController;
    private JTable tablaProfesores;
    private DefaultTableModel modeloTabla;

    public TablaProfesor(ProfesorController profesorController) {
        this.profesorController = profesorController;
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Lista de Profesores", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Email", "Tipo de Contrato"}, 0);
        tablaProfesores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProfesores);
        add(scrollPane, BorderLayout.CENTER);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Profesor> profesores = profesorController.obtenerListaProfesores();
        for (Profesor p : profesores) {
            modeloTabla.addRow(new Object[]{p.getID(), p.getNombres(), p.getApellidos(), p.getEmail(), p.getTipoContrato()});
        }
    }
}
