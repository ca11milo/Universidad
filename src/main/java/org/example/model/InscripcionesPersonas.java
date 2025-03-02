package org.example.model;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;


public class InscripcionesPersonas {
    private ArrayList<Persona> listado;
    private static final String ARCHIVO = Paths.get("data", "InscripcionesPersonas.dat").toString();


    public InscripcionesPersonas(){
        listado = new ArrayList<>();
    }

    public InscripcionesPersonas(ArrayList<Persona> listado) {
        this.listado = listado;
    }

    public ArrayList<Persona> getListado() {
        return listado;
    }

    public void setListado(ArrayList<Persona> listado) {
        this.listado = listado;
    }

    public void inscribir(Persona persona){
        listado.add(persona);
        System.out.println("Persona {"+ persona.getNombres()+", "+ persona.getApellidos() +"} agregada correctamente\n");
    }

    public void eliminar(Persona persona){
        listado.remove(persona);
        System.out.println("Persona {"+ persona.getNombres()+", "+ persona.getApellidos() +"} eliminada correctamente\n");
    }

    public int obtenerPosicionPorId(double IdPersona){
        for(int i=0; i < listado.size(); i++){
            if(Double.compare(listado.get(i).getID(), IdPersona) == 0){
                return i;
            }
        }
        return -1; // -1 es no encontrado
    }

    public void actualizar(Persona persona){

        int indice = obtenerPosicionPorId(persona.getID());

        if(indice == -1){
            System.out.println("la Persona con ID " + persona.getID() + " no se encuentra inscrita");
            return;
        }

        Persona personaEnLaLista = listado.get(indice);
        personaEnLaLista.setNombres(persona.getNombres());
        personaEnLaLista.setApellidos(persona.getApellidos());
        personaEnLaLista.setEmail(persona.getEmail());
        System.out.println("Persona actualizada correctamente");
    }
    public void guardarInformacion(Persona persona) {

        ArrayList<Persona> copiaListado = new ArrayList<>(listado);

        cargarDatos();
        listado.add(persona);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            for (Persona p : listado) {
                out.writeObject(p);
            }
            System.out.println("Datos guardados correctamente en " + ARCHIVO);
        } catch (IOException exception) {
            System.out.println("Ocurrió un error al guardar los datos:");
            exception.printStackTrace();
        }
        listado = copiaListado;
    }


    public void cargarDatos() {
        listado.clear();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            while (true) {
                try {
                    Persona persona = (Persona) in.readObject();
                    listado.add(persona);
                } catch (EOFException e) {
                    break; // Se llegó al final del archivo
                }
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("No se pudo cargar el archivo o el archivo no existe.");
        }
    }
}

