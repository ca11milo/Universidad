package org.example.view.factory;

import org.example.controller.*;
import org.example.view.curso.*;
import org.example.view.estudiante.*;
import org.example.view.facultad.*;
import org.example.view.inscripcion.*;
import org.example.view.persona.*;
import org.example.view.profesor.*;
import org.example.view.programa.*;
import org.example.view.cursoProfesor.*;

import javax.swing.*;
import java.util.Map;

public class VentanaFactory {

    public static JPanel crearFormulario(String accion, String entidad, Map<String, Object> controladores) {

        PersonaController personaController = (PersonaController) controladores.get("persona");
        EstudianteController estudianteController = (EstudianteController) controladores.get("estudiante");
        ProfesorController profesorController = (ProfesorController) controladores.get("profesor");
        FacultadController facultadController = (FacultadController) controladores.get("facultad");
        ProgramaController programaController = (ProgramaController) controladores.get("programa");
        CursoController cursoController = (CursoController) controladores.get("curso");
        InscripcionController inscripcionController = (InscripcionController) controladores.get("inscripcion");
        CursoProfesorController cursoProfesorController = (CursoProfesorController) controladores.get("cursoProfesor");

        switch (entidad) {
            case "Persona":
                switch (accion) {
                    case "Crear": return new GuardarPersona(personaController);
                    case "Actualizar": return new ActualizarPersona(personaController);
                    case "Listar": return new LeerPersona(personaController);
                    case "Eliminar": return new BorrarPersona(personaController);
                }
                break;

                case "Estudiante":
              switch (accion) {
                    case "Crear": return new GuardarEstudiante(estudianteController, programaController);
                    case "Actualizar": return new ActualizarEstudiante(estudianteController, programaController);
                    case "Listar": return new LeerEstudiante(estudianteController);
                    case "Eliminar": return new BorrarEstudiante(estudianteController);
                    case "Detalle": return new DetalleEstudiante(estudianteController);
                }
                break;

            case "Profesor":
                switch (accion) {
                    case "Crear": return new GuardarProfesor(profesorController);
                    case "Actualizar": return new ActualizarProfesor(profesorController);
                    case "Listar": return new LeerProfesor(profesorController);
                    case "Eliminar": return new BorrarProfesor(profesorController);
                }
                break;

            case "Facultad":
                switch (accion) {
                    case "Crear": return new GuardarFacultad(facultadController, personaController);
                    case "Actualizar": return new ActualizarFacultad(facultadController, personaController);
                    case "Listar": return new LeerFacultad(facultadController);
                    case "Eliminar": return new BorrarFacultad(facultadController);
                }
                break;

            case "Programa":
                switch (accion) {
                    case "Crear": return new GuardarPrograma(programaController, facultadController);
                    case "Actualizar": return new ActualizarPrograma(programaController, facultadController);
                    case "Listar": return new LeerPrograma(programaController);
                    case "Eliminar": return new BorrarPrograma(programaController);
                }
                break;

            case "Curso":
                switch (accion) {
                    case "Crear": return new GuardarCurso(cursoController, programaController);
                    case "Actualizar": return new ActualizarCurso(cursoController, programaController);
                    case "Listar": return new LeerCurso(cursoController);
                    case "Eliminar": return new BorrarCurso(cursoController);
                }
                break;

            case "Inscripción":
                CursoController cursoControllerInsc = (CursoController) controladores.get("curso");
                switch (accion) {
                    case "Crear": return new GuardarInscripcion(inscripcionController, estudianteController);
                    case "Actualizar": return new ActualizarInscripcion(inscripcionController, estudianteController);
                    case "Listar": return new LeerInscripcion(inscripcionController);
                    case "Eliminar": return new BorrarInscripcion(inscripcionController);
                }
                break;

            case "Curso-Profesor":
                switch (accion) {
                    case "Crear": return new GuardarCursoProfesor(cursoProfesorController, profesorController, cursoController);
                    case "Actualizar": return new ActualizarCursoProfesor(cursoProfesorController,  profesorController, cursoController);
                    case "Listar": return new LeerCursoProfesor(cursoProfesorController);
                    case "Eliminar": return new BorrarCursoProfesor(cursoProfesorController);
                }
                break;
        }

        return null;
    }
}
