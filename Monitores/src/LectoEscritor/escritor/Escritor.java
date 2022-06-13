package LectoEscritor.escritor;

import java.util.List;

import LectoEscritor.monitor.Libro;

public class Escritor extends Thread {

    private Libro libro;
    private String nombre;

    public Escritor(Libro libro,String nombre) {
        this.libro = libro;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0;i<5;i++){
            var numeros =List.of(
                (int)(Math.random()*14)+1,
                (int)(Math.random()*14)+1,
                (int)(Math.random()*14)+1,
                (int)(Math.random()*14)+1,
                (int)(Math.random()*14)+1,
                (int)(Math.random()*14)+1
            );

            libro.escribir(nombre,numeros);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
