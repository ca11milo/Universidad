package org.example.view.curso;

import org.example.controller.CursoController;
import org.example.model.Curso;
import org.example.patterns.observer.Observer;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerCurso extends VentanaLeer<Curso> implements Observer {
    private CursoController cursoController;

    public LeerCurso(CursoController cursoController) {
        super("Curso", new String[]{"ID", "Nombre", "Programa", "Activo"});
        this.cursoController = cursoController;
        cursoController.agregarObservador(this);
        cargarDatos();
    }

    @Override
    protected List<Curso> obtenerDatos() {
        return cursoController.obtenerListaCursos();
    }

    @Override
    protected Object[] mapearFila(Curso curso) {
        return new Object[]{curso.getID(), curso.getNombre(), curso.getPrograma().getNombre(), curso.isActivo()};
    }

    @Override
    public void actualizar() {
        cargarDatos();
    }
}

