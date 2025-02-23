package org.example;

import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios{
    private ArrayList<CursoProfesor> listado;

    public CursosProfesores(){
        listado = new ArrayList<>();
    }

    public CursosProfesores(ArrayList<CursoProfesor> listado) {
        this.listado = listado;
    }

    public ArrayList<CursoProfesor> getListado() {
        return listado;
    }

    public void setListado(ArrayList<CursoProfesor> listado) {
        this.listado = listado;
    }

    public void inscribir(CursoProfesor cursoProfesor){
        listado.add(cursoProfesor);
        System.out.println("Curso añadido correctamente");
    }

    public void guardarInformacion(CursoProfesor cursoProfesor){

    }

    @Override
    public void cargarDatos(){}

    @Override
    public List<String> ToString(){

        List<String> lista = new ArrayList<>();
        for(CursoProfesor cursoProfesor : listado) {
            lista.add(cursoProfesor.toString());
        }
        return lista;
    }

    @Override
    public String imprimirPosicion(int posicion){
        return "";
    }

    @Override
    public Integer cantidadActual(){
        return listado.size();
    }

    @Override
    public List<String> imprimirListado(){
        List<String> cursosParaImprimir = new ArrayList<>();
        for(CursoProfesor curso : listado){
            cursosParaImprimir.add(curso.toString());
        }
        return cursosParaImprimir;
    }
}
