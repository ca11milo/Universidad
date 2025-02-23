package org.example;

public class Main {
    public static void main(String[] args) {



        // Crear instancia de inscripcionesPersonas
        InscripcionesPersonas inscripciones = new InscripcionesPersonas();

        // Agregar personas a la lista
        Persona persona1 = new Persona("Juan", "juan@example.com", "Burgos", 1);
        Persona persona2 = new Persona("camilo", "camilo@example.com", "Londoño", 2);

        inscripciones.inscribir(persona1);
        inscripciones.inscribir(persona2);

        
        System.out.println("Lista:");
        for (Persona p : inscripciones.getListado()) {
            System.out.println(p);
        }

        
        Persona personaActualizada = new Persona("Cristian", "cristian@example.com", "rojas", 1);
        inscripciones.eliminar(persona2);
        inscripciones.actualizar(personaActualizada);

        
        System.out.println("\nLista ya actualizada:");
        for (Persona p : inscripciones.getListado()) {
            System.out.println(p);
        }



















    }
}