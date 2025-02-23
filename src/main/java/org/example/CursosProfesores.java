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
    public List<String> ToString(){

        List<String> cursosParaImprimir = new ArrayList<>();
        for(CursoProfesor cursoProfesor : listado) {
            cursosParaImprimir.add(cursoProfesor.toString());
        }
        return cursosParaImprimir;
    }

    @Override
    public String imprimirPosicion(int posicion){

        return listado.get(posicion).toString();
    }

    @Override
    public Integer cantidadActual(){
        return listado.size();
    }

    @Override
    public List<String> imprimirListado(){
        List<String> cursosParaImprimir = new ArrayList<>();
        System.out.println("Listado de cursos de Profesores: ");
        for(CursoProfesor cursoProfesor : listado){
            cursosParaImprimir.add(cursoProfesor.toString());
            System.out.println(cursoProfesor.toString());
        }
        return cursosParaImprimir;
    }


    @Override
    public void cargarDatos(){}
}
