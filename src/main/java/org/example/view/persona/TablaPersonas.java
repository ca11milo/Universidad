package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaPersonas extends JPanel {
    private PersonaController personaController;
    private JTable tablaPersonas;
    private DefaultTableModel modeloTabla;

    public TablaPersonas(PersonaController personaController) {
        this.personaController = personaController;
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Lista de Personas", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Email"}, 0);
        tablaPersonas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPersonas);
        add(scrollPane, BorderLayout.CENTER);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Persona> personas = personaController.obtenerListaPersonas();
        for (Persona p : personas) {
            modeloTabla.addRow(new Object[]{p.getID(), p.getNombres(), p.getApellidos(), p.getEmail()});
        }
    }
}
