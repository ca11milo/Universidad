package org.example;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

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
        System.out.println("Persona {"+ persona.getNombres()+", "+ persona.getApellidos() +"} agregada correctamente");
    }
    public void eliminar(Persona persona){
        listado.remove(persona);
        System.out.println("Persona {"+ persona.getNombres()+", "+ persona.getApellidos() +"} eliminada correctamente");
    }

    public Optional<Integer> obtenerPosicionPorId(double IdPersona){
        Optional<Integer> indice = Optional.empty();
        for(Persona persona : listado){
            if(persona.getID()==IdPersona){
                 indice = Optional.of(listado.indexOf(persona));
            }
        }
        return indice;
    }

    public void actualizar(Persona persona){

        Optional<Integer> indicePersona = obtenerPosicionPorId(persona.getID());
        if(indicePersona.isEmpty()){
            System.out.println("Persona no encontrada ");
        }else{
            Persona personaEnLaLista = listado.get(indicePersona.get());
            personaEnLaLista.setNombres(persona.getNombres());
            personaEnLaLista.setNombres(persona.getApellidos());
            personaEnLaLista.setEmail(persona.getEmail());
            System.out.println("Persona actualizada correctamente ");
        }

    }
    public void guardarInformacion(){

    }
    public void cargarDatos(){

    }

}

