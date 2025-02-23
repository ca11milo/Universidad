package org.example;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        CursosProfesores cursosProfesores = new CursosProfesores();
        CursosInscritos cursosInscritos1 = new CursosInscritos();


        Persona persona1 = new Persona("Juan", "juan@example.com", "Burgos", 1.0);
        Persona decano = new Persona("Elvis", "elvis@gmail.com", "Perez", 123);

        Facultad fcbi = new Facultad(16000, "Ciencias Basicas e Ingenieria", decano);

        Programa sistemas = new Programa(1112, "ingenieria de sistemas", new Date(125, 1,23), 10, fcbi);

        Estudiante persona2 = new Estudiante("camilo", "camilo@example.com", "Londoño", 2,123, sistemas, 3.9, true );

        Curso tecnologiasAvanzadas = new Curso(1, "matematicas", sistemas, true);

        Inscripcion inscripcion1 = new Inscripcion(tecnologiasAvanzadas, 2025, 2, persona2);

        Profesor roger = new Profesor("Roger", "roger@gmail.com", "Calderon", 8765, "Planta");
        CursoProfesor cursoProfesor1 = new CursoProfesor(roger, 1, 2025, tecnologiasAvanzadas);

        cursosProfesores.inscribir(cursoProfesor1);
        cursosProfesores.imprimirListado();
        cursosProfesores.guardarInformacion(cursoProfesor1);

        cursosInscritos1.inscribirCurso(inscripcion1);
        cursosInscritos1.guardarInformacion(inscripcion1);


        inscripcionesPersonas.inscribir(persona1);
        inscripcionesPersonas.inscribir(persona2);
        
        Persona personaActualizada = new Persona("Cristian", "cristian@example.com", "rojas", 1);
        //inscripcionesPersonas.eliminar(persona2);
        inscripcionesPersonas.actualizar(personaActualizada);

        
        System.out.println("\nLista ya actualizada:");
        for (Persona p : inscripcionesPersonas.getListado()) {
            System.out.println(p);
        }

        inscripcionesPersonas.guardarInformacion(persona1);

    inscripcionesPersonas.cargarDatos();

        System.out.println("Lista:");
        for (Persona p : inscripcionesPersonas.getListado()) {
            System.out.println(p);
        }

    cursosProfesores.cargarDatos();
        cursosProfesores.imprimirListado();

    cursosInscritos1.cargarDatos();
    cursosInscritos1.imprimirListado();

    }
}