package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;
import org.example.patterns.observer.Observable;
import org.example.patterns.observer.Observer;
import org.example.view.ventanasCRUD.VentanaLeer;

import java.util.List;

public class LeerPersona extends VentanaLeer<Persona> implements Observer {
    private PersonaController personaController;

    public LeerPersona(PersonaController personaController) {
        super("Personas", new String[]{"ID", "Nombre", "Apellido", "Email"});
        this.personaController = personaController;
        personaController.agregarObservador(this);
        cargarDatos();
    }

    @Override
    protected List<Persona> obtenerDatos() {
        return personaController.obtenerListaPersonas();
    }

    @Override
    protected Object[] mapearFila(Persona persona) {
        return new Object[]{persona.getID(), persona.getNombres(), persona.getApellidos(), persona.getEmail()};
    }

    @Override
    public void actualizar() {
        cargarDatos();
    }
}
