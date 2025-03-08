package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablaCurso extends JPanel {
    private CursoController cursoController;
    private JTable tablaCursos;
    private DefaultTableModel modeloTabla;

    public TablaCurso(CursoController cursoController) {
        this.cursoController = cursoController;
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Lista de Cursos", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(tituloLabel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Programa", "Activo"}, 0);
        tablaCursos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCursos);
        add(scrollPane, BorderLayout.CENTER);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Curso> cursos = cursoController.obtenerListaCursos();
        for (Curso c : cursos) {
            modeloTabla.addRow(new Object[]{c.getID(), c.getNombre(), c.getPrograma().getNombre(), c.isActivo()});
        }
    }
}
