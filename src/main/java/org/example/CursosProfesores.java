package org.example;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios{
    private ArrayList<CursoProfesor> listado;
    private static final String ARCHIVO = Paths.get("data", "CursosProfesores.dat").toString();


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

    public void guardarInformacion(CursoProfesor cursoProfesor) {

        ArrayList<CursoProfesor> copiaListado = new ArrayList<>(listado);

        cargarDatos();
        listado.add(cursoProfesor);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            for (CursoProfesor curso : listado) {
                out.writeObject(curso);
            }
            System.out.println("Datos guardados correctamente en " + ARCHIVO);
        } catch (IOException exception) {
            System.out.println("Ocurrió un error al guardar los datos:");
            exception.printStackTrace();
        }

        listado = copiaListado;
    }

    @Override
    public void cargarDatos() {
        listado.clear();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            while (true) {
                try {
                    CursoProfesor cursoProfesor = (CursoProfesor) in.readObject();
                    listado.add(cursoProfesor);
                } catch (EOFException e) {
                    break; // Se llegó al final del archivo
                }
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("No se pudo cargar el archivo o el archivo no existe.");
        }
    }
}
