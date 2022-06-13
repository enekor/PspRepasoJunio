package ProductorConsumidor.monitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ProductorConsumidor.model.*;

public class Tienda {

    private Queue<Producto> productos = new LinkedList<>();
    private boolean vacio = false;
    private boolean lleno = false;
    private final int MAXIMO = 30;

    public synchronized long insertarProductos(List<Producto> insertados, String nombreProductor){

        while(lleno){
            System.out.println("El almacen esta lleno, toca esperar");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long tiempoEspera = 0L;

        for (Producto producto: insertados){
            productos.add(producto);
            tiempoEspera+=producto.getPeso();
        }

        System.out.println("El productor "+nombreProductor+" ha insertado 5 productos");
        vacio = false;

        if(productos.size() == MAXIMO){
            lleno = true;
        }

        System.out.println("productos en el almacen: "+productos.size());
        notifyAll();

        return tiempoEspera;
    }
    
    public synchronized List<Producto> sacarproductos(String nombre){

        while(vacio){
            System.out.println("No hay productos suficientes para sacar");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El consumidor "+nombre+" se lleva 5 productos");
        List<Producto> returner = new ArrayList<>();
        
        for(int i =0;i<5;i++){
            returner.add(productos.poll());
        }

        if(productos.size()<=0){
            vacio = true;
        }
        lleno = false;

        System.out.println("productos en el almacen: "+productos.size());
        notifyAll();

        return returner;
    }
}
