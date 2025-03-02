package org.example.model;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Servicios{
    private List<Inscripcion> listado;
    private static final String ARCHIVO = Paths.get("data", "CursosInscritos.dat").toString();


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

    public int encontrarCursoInscrito(Integer IdCurso,  Double codigoEstudiante){
        for(Inscripcion inscripcionEnLaLista : listado){
            if( IdCurso.equals(inscripcionEnLaLista.getCurso().getID()) &&
                    codigoEstudiante.equals(inscripcionEnLaLista.getEstudiante().getCodigo())){
                return listado.indexOf(inscripcionEnLaLista);
            }
        }
        return -1;
    }

    public void inscribirCurso(Inscripcion inscripcion){

        Integer idCurso = inscripcion.getCurso().getID();
        Double codigoEstudiante = inscripcion.getEstudiante().getCodigo();
        if(encontrarCursoInscrito(idCurso,codigoEstudiante) != -1){
            System.out.println("El curso ya fue inscrito por el estudiante, no se puede inscribir más de una vez");
            return;
        }
        listado.add(inscripcion);
        System.out.println("Curso agregado correctamente");

    }

    public void eliminar(Inscripcion inscripcion){
        listado.remove(inscripcion);
    }

    public void actualizar(Inscripcion inscripcion ){

        Integer idCurso = inscripcion.getCurso().getID();
        Double codigoEstudiante = inscripcion.getEstudiante().getCodigo();
        int indiceInscripcion= encontrarCursoInscrito(idCurso,codigoEstudiante);
        if( indiceInscripcion==-1){
            System.out.println("El curso no ha sido inscrito por el estudiante");
        }else{
            listado.get(indiceInscripcion).setAño(inscripcion.getAño());
            listado.get(indiceInscripcion).setSemestre(inscripcion.getSemestre());
            System.out.println("Inscripción actualizada con exito");
        }
    }

    @Override
    public List<String> ToString() {
        List<String> cursosParaImprimir = new ArrayList<>();
        for(Inscripcion inscripcion : listado) {
            cursosParaImprimir.add(inscripcion.toString());
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
        System.out.println("Listado de Cursos Inscritos de Estudiantes: ");
        for(Inscripcion curso : listado){
            cursosParaImprimir.add(curso.toString());
            System.out.println(curso.toString());
        }
        return cursosParaImprimir;
    }

    public void guardarInformacion(Inscripcion inscripcion) {

        ArrayList<Inscripcion> copiaListado = new ArrayList<>(listado);

        cargarDatos();
        listado.add(inscripcion);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            for (Inscripcion insc : listado) {
                out.writeObject(insc);
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
                    Inscripcion inscripcion = (Inscripcion) in.readObject();
                    listado.add(inscripcion);
                } catch (EOFException e) {
                    break; // Se llegó al final del archivo
                }
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("No se pudo cargar el archivo o el archivo no existe.");
        }
    }
}
