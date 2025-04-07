package org.example.view.estudiante;

import org.example.controller.CursoController;
import org.example.controller.EstudianteController;
import org.example.controller.InscripcionController;
import org.example.controller.ProfesorController;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import org.example.model.Profesor;
import org.example.patterns.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DetalleEstudiante extends JPanel implements Observer {
    private JTextField codigoField;
    private JTextField nombreField;
    private EstudianteController estudianteController;
    private InscripcionController inscripcionController;
    private CursoController cursoController;
    private ProfesorController profesorController;
    private JTabbedPane tabbedPane;
    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloCursos;
    private DefaultTableModel modeloDocentes;

    public DetalleEstudiante(EstudianteController estudianteController, InscripcionController inscripcionController, CursoController cursoController, ProfesorController profesorController) {
        this.estudianteController = estudianteController;
        this.inscripcionController = inscripcionController;
        this.cursoController = cursoController;
        this.profesorController = profesorController;

        // Registro como observador
        inscripcionController.agregarObservador(this);
        cursoController.agregarObservador(this);
        profesorController.agregarObservador(this);

        setLayout(new BorderLayout());

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buscarPanel.add(new JLabel("Código:"));
        codigoField = new JTextField(10);
        buscarPanel.add(codigoField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(e -> buscarEstudiante());
        buscarPanel.add(buscarButton);

        buscarPanel.add(new JLabel("Estudiante:"));
        nombreField = new JTextField(20);
        nombreField.setEditable(false);
        buscarPanel.add(nombreField);

        add(buscarPanel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Historial Cursos", crearPanelHistorial());
        tabbedPane.addTab("Inscribir Curso", crearPanelInscribir());
        tabbedPane.addTab("Cursos", crearPanelCursos());
        tabbedPane.addTab("Docentes", crearPanelDocentes());

        add(tabbedPane, BorderLayout.CENTER);

        cargarInscripciones();
        cargarCursos();
        cargarDocentes();
    }

    private void buscarEstudiante() {
        try {
            double codigo = Double.parseDouble(codigoField.getText().trim());
            Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codigo);
            if (estudiante != null) {
                nombreField.setText(estudiante.getNombreCompleto());
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.");
                nombreField.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código ingresado no es válido.");
        }
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Curso", "Año", "Semestre", "Estudiante"}, 0);
        tablaHistorial = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void cargarInscripciones() {
        modeloTabla.setRowCount(0);
        List<Inscripcion> inscripciones = inscripcionController.obtenerListaInscripciones();

        for (Inscripcion inscripcion : inscripciones) {
            Object[] fila = {
                    inscripcion.getId(),
                    inscripcion.getCurso().getNombre(),
                    inscripcion.getAño(),
                    inscripcion.getSemestre(),
                    inscripcion.getEstudiante().getNombreCompleto()
            };
            modeloTabla.addRow(fila);
        }
    }

    private JPanel crearPanelInscribir() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblIdCurso = new JLabel("ID Curso:");
        JTextField txtIdCurso = new JTextField(10);

        JLabel lblIdEstudiante = new JLabel("ID Estudiante:");
        JTextField txtIdEstudiante = new JTextField(10);

        JLabel lblAnio = new JLabel("Año:");
        JTextField txtAnio = new JTextField(10);

        JLabel lblSemestre = new JLabel("Semestre:");
        JComboBox<Integer> comboSemestre = new JComboBox<>(new Integer[]{1, 2});

        JButton btnGuardar = new JButton("Guardar Inscripción");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblIdCurso, gbc);
        gbc.gridx = 1;
        panel.add(txtIdCurso, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblIdEstudiante, gbc);
        gbc.gridx = 1;
        panel.add(txtIdEstudiante, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblAnio, gbc);
        gbc.gridx = 1;
        panel.add(txtAnio, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblSemestre, gbc);
        gbc.gridx = 1;
        panel.add(comboSemestre, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(btnGuardar, gbc);

        btnGuardar.addActionListener(e -> {
            try {
                String idCursoTexto = txtIdCurso.getText().trim();
                String idEstudianteTexto = txtIdEstudiante.getText().trim();
                String anioTexto = txtAnio.getText().trim();

                if (idCursoTexto.isEmpty() || anioTexto.isEmpty() || idEstudianteTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int idCurso = Integer.parseInt(idCursoTexto);
                int anio = Integer.parseInt(anioTexto);
                int semestre = (Integer) comboSemestre.getSelectedItem();
                int idEstudiante = Integer.parseInt(idEstudianteTexto);

                if (anio < 1974 || anio > 2100) {
                    JOptionPane.showMessageDialog(panel, "El año ingresado no es válido (debe ser entre 1974 y 2100).", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Estudiante estudiante = estudianteController.obtenerEstudiantePorId(idEstudiante);
                if (estudiante == null) {
                    JOptionPane.showMessageDialog(panel, "No existe un estudiante con el ID ingresado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Curso curso = new Curso(idCurso);
                Inscripcion inscripcion = new Inscripcion(0, curso, anio, semestre, estudiante);

                inscripcionController.guardarInscripcion(inscripcion);
                JOptionPane.showMessageDialog(panel, "Inscripción guardada exitosamente.");

                txtIdCurso.setText("");
                txtIdEstudiante.setText("");
                txtAnio.setText("");
                comboSemestre.setSelectedIndex(0);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Verifica que todos los valores numéricos sean válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error al guardar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel crearPanelCursos() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnas = {"ID", "Nombre", "Programa", "Activo"};
        modeloCursos = new DefaultTableModel(columnas, 0);
        JTable tablaCursos = new JTable(modeloCursos);
        JScrollPane scrollPane = new JScrollPane(tablaCursos);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void cargarCursos() {
        if (modeloCursos != null) {
            modeloCursos.setRowCount(0);
            List<Curso> listaCursos = cursoController.obtenerListaCursos();
            for (Curso curso : listaCursos) {
                Object[] fila = {
                        curso.getID(),
                        curso.getNombre(),
                        curso.getPrograma().getNombre(),
                        curso.isActivo()
                };
                modeloCursos.addRow(fila);
            }
        }
    }

    private JPanel crearPanelDocentes() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnas = {"ID", "Nombre", "Apellido", "Email", "Tipo de Contrato"};
        modeloDocentes = new DefaultTableModel(columnas, 0);
        JTable tablaDocentes = new JTable(modeloDocentes);
        JScrollPane scrollPane = new JScrollPane(tablaDocentes);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void cargarDocentes() {
        if (modeloDocentes != null) {
            modeloDocentes.setRowCount(0);
            List<Profesor> listaProfesores = profesorController.obtenerListaProfesores();
            for (Profesor profesor : listaProfesores) {
                Object[] fila = {
                        profesor.getID(),
                        profesor.getNombres(),
                        profesor.getApellidos(),
                        profesor.getEmail(),
                        profesor.getTipoContrato()
                };
                modeloDocentes.addRow(fila);
            }
        }
    }

    @Override
    public void actualizar() {
        cargarInscripciones();
        cargarCursos();
        cargarDocentes();
    }
}
