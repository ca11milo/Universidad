package org.example;

import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios{
    private ArrayList<CursoProfesor> listado;

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

    public void cargarInformacion(){}

    @Override
    public String toString() {
        return "CursosProfesores{" +
                "listado=" + listado +
                '}';
    }


    public String imprimirPosicion(int posicion){
        return "";
    }

    public Integer cantidadActual(){
        return listado.size();
    }

    public List<String> imprimirListado(){
        List<String> cursosParaImprimir = new ArrayList<String>();
        for(CursoProfesor curso : listado){
            cursosParaImprimir.add(curso.toString());
        }
        return cursosParaImprimir;
    }
}
