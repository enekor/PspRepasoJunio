package ProductorConsumidor.productor;

import java.util.List;

import ProductorConsumidor.model.Producto;
import ProductorConsumidor.monitor.Tienda;

public class Productor extends Thread {

    private Tienda tienda;
    private String nombre;

    public Productor(Tienda t,String nombre){
        this.tienda = t;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        
        for(int i = 0;i<10;i++){
            long espera = tienda.insertarProductos(
                List.of(
                    new Producto((int)(Math.random()*34)+1, nombre, (int)(Math.random()*3000)+1000),
                    new Producto((int)(Math.random()*34)+1, nombre, (int)(Math.random()*3000)+1000),
                    new Producto((int)(Math.random()*34)+1, nombre, (int)(Math.random()*3000)+1000),
                    new Producto((int)(Math.random()*34)+1, nombre, (int)(Math.random()*3000)+1000),
                    new Producto((int)(Math.random()*34)+1, nombre, (int)(Math.random()*3000)+1000)
                ), 
                nombre);

                System.out.println("El productor "+this.nombre+" procede a esperar "+espera+" segundos");
            try {
                Thread.sleep(espera);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
