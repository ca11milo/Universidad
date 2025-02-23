package org.example;

import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Servicios{
    private List<Inscripcion> listado;

    public CursosInscritos(){
        listado = new ArrayList<>();
    }

    public CursosInscritos(ArrayList<Inscripcion> listado) {
        this.listado = listado;
    }

    public List<Inscripcion> getListado() {
        return listado;
    }

    public void setListado(ArrayList<Inscripcion> listado) {
        this.listado = listado;
    }

    public void inscribirCurso(Inscripcion inscripcion){
        listado.add(inscripcion);
    }

    public void eliminar(Inscripcion inscripcion){
        listado.remove(inscripcion);
    }

    public void actualizar(Inscripcion inscripcion ){

        Integer IdCurso = inscripcion.getCurso().getID();
        Double codigoEstudiante = inscripcion.getEstudiante().getCodigo();
        for(Inscripcion inscripcionEnLaLista : listado){
            if(inscripcionEnLaLista.getCurso().getID() == IdCurso &&
                    inscripcionEnLaLista.getEstudiante().getCodigo() == codigoEstudiante){
                    //TODO
            }
        }

    }

    public void guardarInformacion(){

    }

    @Override
    public List<String> ToString() {
        List<String> lista = new ArrayList<>();
        for(Inscripcion inscripcion : listado) {
            lista.add(inscripcion.toString());
        }
        return lista;
    }



    @Override
    public void cargarDatos(){

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
        for(Inscripcion curso : listado){
            cursosParaImprimir.add(curso.toString());
        }
        return cursosParaImprimir;
    }
}
