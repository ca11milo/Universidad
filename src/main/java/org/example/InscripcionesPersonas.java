package org.example;

import java.util.ArrayList;

public class InscripcionesPersonas {
    private ArrayList<Persona> listado;

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

    public void guardarInformacion(){

    }
    public void cargarDatos(){

    }

}

