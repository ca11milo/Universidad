package org.example.model.factory;

import org.example.model.Estudiante;
import org.example.model.Persona;
import org.example.model.Profesor;
import org.example.model.Programa;

public class PersonaFactory {

    public static Persona crearPersona( int id, String nombre, String apellidos, String email) {
        return new Persona(id, nombre, apellidos, email);
    }
    public static Estudiante crearEstudiante(
            int id, String nombres, String apellidos, String email, Double codigo,
            Programa programa, boolean activo, double promedio
    ) {
        return new Estudiante(id, nombres, apellidos, email, codigo, programa, activo, promedio);
    }

    public static Profesor crearProfesor(
            int id, String nombre, String apellidos, String email, String telefono
    ) {
        return new Profesor(id, nombre, apellidos, email, telefono);
    }
}
