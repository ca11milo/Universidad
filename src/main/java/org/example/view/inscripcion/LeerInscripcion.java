package org.example.view.inscripcion;

import org.example.controller.InscripcionController;
import org.example.model.Inscripcion;
import org.example.patterns.observer.Observer;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerInscripcion extends VentanaLeer<Inscripcion> implements Observer {
        private InscripcionController inscripcionController;

        public LeerInscripcion(InscripcionController inscripcionController) {
            super("Inscripción", new String[]{"ID", "Curso", "Año", "Semestre", "Estudiante"});
            this.inscripcionController = inscripcionController;
            inscripcionController.agregarObservador(this);
            cargarDatos();
        }

        @Override
        protected List<Inscripcion> obtenerDatos() {

            return inscripcionController.obtenerListaInscripciones();
        }

        @Override
        protected Object[] mapearFila(Inscripcion inscripcion) {
            return new Object[]{inscripcion.getId(), inscripcion.getCurso().getNombre(), inscripcion.getAño(), inscripcion.getSemestre(), inscripcion.getEstudiante().getNombreCompleto()};
        }

        @Override
        public void actualizar() {
            cargarDatos();
        }
}

