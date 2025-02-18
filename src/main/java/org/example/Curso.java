package org.example;

public class Curso {

    private int ID;
    private Programa programa;
    private boolean activo;

    public Curso(int ID, Programa programa, boolean activo) {
        this.ID = ID;
        this.programa = programa;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "ID=" + ID +
                ", programa=" + programa +
                ", activo=" + activo +
                '}';
    }
}
