package LectoEscritor;

import java.util.List;

import LectoEscritor.escritor.Escritor;
import LectoEscritor.lector.Lector;
import LectoEscritor.monitor.Libro;

public class App {
    public static void main(String[] args) {
        Libro l = new Libro();

        List<Lector> lectores = List.of(
            new Lector(l, "Juan"),
            new Lector(l, "Pepe"),
            new Lector(l, "Jose"),
            new Lector(l, "Maria")
        );

        List<Escritor> escritores = List.of(
            new Escritor(l, "Karolanne"),
            new Escritor(l, "Dali"),
            new Escritor(l, "Daniel"),
            new Escritor(l, "Si"),
            new Escritor(l, "Ivan")
        );

        lectores.forEach(Thread::start);
        escritores.forEach(Thread::start);
    }
}
