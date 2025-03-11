package org.example.view.estudiante;

import org.example.controller.EstudianteController;
import org.example.model.Estudiante;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerEstudiante extends VentanaLeer<Estudiante> {
    private EstudianteController estudianteController;

    public LeerEstudiante(EstudianteController estudianteController) {
        super("Estudiante", new String[]{"ID", "Nombre", "Apellido", "Email", "Codigo", "Programa", "Activo", "Promedio"});
        this.estudianteController = estudianteController;
        cargarDatos();
    }

    @Override
    protected List<Estudiante> obtenerDatos() {
        return estudianteController.obtenerListaEstudiantes();
    }

    @Override
    protected Object[] mapearFila(Estudiante estudiante) {
        return new Object[]{estudiante.getID(), estudiante.getNombres(), estudiante.getApellidos(), estudiante.getEmail(), estudiante.getCodigo(), estudiante.getPrograma(), estudiante.getActivo(), estudiante.getPromedio()};
    }
}
