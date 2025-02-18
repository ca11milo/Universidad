package org.example;

import java.util.ArrayList;

public class InscripcionesPersonas {
    private ArrayList<Persona> listado;

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
        System.out.println("Persona {"+ persona.getNombres()+", "+ persona.getApellidos() +"} agregada correctamente");
    }
    public void eliminar(Persona persona){
        listado.remove(persona);
        System.out.println("Persona {"+ persona.getNombres()+", "+ persona.getApellidos() +"} eliminada correctamente");
    }
    public void actualizar(Persona persona){

        double id = persona.getID();
        for(Persona personaEnLaLista: listado){
            if(personaEnLaLista.getID()==persona.getID()){
                personaEnLaLista.setNombres(persona.getNombres());
                personaEnLaLista.setApellidos(persona.getApellidos());
                personaEnLaLista.setEmail(persona.getEmail());
                System.out.println("Persona actualizada correctamente");
                return;
            }
        }
        System.out.println("Persona no encontrada en la lista");

    }
    public void guardarInformacion(){

    }
    public void cargarDatos(){

    }

}

