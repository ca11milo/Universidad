package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursosInscritos {
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
}
