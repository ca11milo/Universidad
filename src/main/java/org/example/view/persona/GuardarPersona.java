package org.example.view.persona;

import org.example.controller.PersonaController;
import org.example.model.Persona;
import org.example.view.ventanasCRUD.VentanaGuardar;

import java.sql.SQLException;

public class GuardarPersona extends VentanaGuardar<Persona> {
    private PersonaController personaController;

    public GuardarPersona(PersonaController personaController) {
        super("Crear Persona", new String[]{"Nombre", "Apellido", "Email"});
        this.personaController = personaController;
    }

    @Override
    protected void guardarEntidad() throws SQLException {
        String nombre = campos[0].getText();
        String apellido = campos[1].getText();
        String email = campos[2].getText();

        personaController.guardarPersona(new Persona(0, nombre, apellido, email));
    }
}
