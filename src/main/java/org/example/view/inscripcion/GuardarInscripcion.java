package org.example.view.inscripcion;

import org.example.controller.InscripcionController;
import org.example.model.Curso;
import org.example.model.Estudiante;
import org.example.model.Inscripcion;
import org.example.view.ventanasCRUD.VentanaGuardar;

import javax.swing.*;

public class GuardarInscripcion extends VentanaGuardar<Inscripcion> {
    private InscripcionController inscripcionController;

    public GuardarInscripcion(InscripcionController inscripcionController) {
        super("Registrar Inscripción", new String[]{"ID Curso", "Año", "Semestre", "ID Estudiante"});
        this.inscripcionController = inscripcionController;
    }

    @Override
    protected void guardarEntidad() throws Exception {
        try {
            int idCurso = Integer.parseInt(campos[0].getText());
            int anio = Integer.parseInt(campos[1].getText());
            int semestre = Integer.parseInt(campos[2].getText());
            int idEstudiante = Integer.parseInt(campos[3].getText());

            Curso curso = new Curso(idCurso);
            Estudiante estudiante = new Estudiante(idEstudiante);

            Inscripcion inscripcion = new Inscripcion(0, curso, anio, semestre, estudiante);
            inscripcionController.guardarInscripcion(inscripcion);

            JOptionPane.showMessageDialog(this, "Inscripción guardada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifica los valores.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
