package LectoEscritor.lector;

import LectoEscritor.monitor.Libro;

public class Lector extends Thread{

    private Libro libro;
    private String nombre;

    public Lector(Libro libro,String nombre) {
        this.libro = libro;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for(int i = 0;i<5;i++){
            
            var numbers = libro.leer(nombre);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Los numeros que ha leido el lector "+this.nombre+" son "+numbers);
        }
    }
}
