package org.example.view.cursoProfesor;

import org.example.controller.CursoProfesorController;
import org.example.model.CursoProfesor;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerCursoProfesor extends VentanaLeer<CursoProfesor> {
    private CursoProfesorController cursoProfesorController;

    public LeerCursoProfesor(CursoProfesorController cursoProfesorController) {
        super("Inscripción", new String[]{"ID", "Curso", "Año", "Semestre", "Estudiante"});
        this.cursoProfesorController = cursoProfesorController;
        cargarDatos();
    }

    @Override
    protected List<CursoProfesor> obtenerDatos() {

        return cursoProfesorController.obtenerListaCursosProfesor();
    }

    @Override
    protected Object[] mapearFila(CursoProfesor cursoProfesor) {
        return new Object[]{cursoProfesor.getId(), cursoProfesor.getProfesor(), cursoProfesor.getAño(), cursoProfesor.getSemestre(), cursoProfesor.getCurso()};
    }
}
