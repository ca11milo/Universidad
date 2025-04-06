package org.example.view.estudiante;

import org.example.controller.EstudianteController;
import org.example.model.Estudiante;
import org.example.patterns.observer.Observer;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerEstudiante extends VentanaLeer<Estudiante> implements Observer {
    private EstudianteController estudianteController;

    public LeerEstudiante(EstudianteController estudianteController) {
        super("Estudiante", new String[]{"ID", "Nombre", "Apellido", "Email", "Codigo", "Programa", "Activo", "Promedio"});
        this.estudianteController = estudianteController;
        estudianteController.agregarObservador(this);
        cargarDatos();
    }

    @Override
    protected List<Estudiante> obtenerDatos() {
        return estudianteController.obtenerListaEstudiantes();
    }

    @Override
    protected Object[] mapearFila(Estudiante estudiante) {
        return new Object[]{estudiante.getID(), estudiante.getNombres(), estudiante.getApellidos(), estudiante.getEmail(), estudiante.getCodigo().intValue(), estudiante.getPrograma().getNombre(), estudiante.getActivo(), estudiante.getPromedio()};
    }

    @Override
    public void actualizar() {
        cargarDatos();
    }
}
