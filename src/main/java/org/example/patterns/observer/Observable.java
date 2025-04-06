package org.example.patterns.observer;

public interface Observable {
    void agregarObservador(Observer o);
    void eliminarObservador(Observer o);
    void notificarObservadores();
}
