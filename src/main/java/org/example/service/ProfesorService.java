package org.example.service;

import org.example.dao.ProfesorDAO;
import org.example.model.Profesor;
import java.util.List;
import java.util.Optional;

public class ProfesorService {
    private ProfesorDAO profesorDAO;

    public ProfesorService(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    public void guardarProfesor(Profesor profesor){
        profesorDAO.guardarProfesor(profesor);
    }

    public Profesor obtenerProfesorPorId(int id){
        Optional<Profesor> profesor = profesorDAO.obtenerProfesorPorId(id);
        return profesor.orElse(null);
    }

    public List<Profesor> obtenerListaProfesores(){
        return profesorDAO.obtenerListaProfesores();
    }

    public boolean eliminarProfesor(int id) {
        return profesorDAO.eliminarProfesor(id);
    }

    public boolean actualizarProfesor(Profesor profesor) {
        return profesorDAO.actualizarProfesor(profesor);
    }
}
