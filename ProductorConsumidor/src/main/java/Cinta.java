import model.Producto;
import java.util.LinkedList;
import java.util.Queue;

public class Cinta {
    private Queue<Producto> productos = new LinkedList();
    private final int MAX = 5;
    private boolean lleno = false;

    public synchronized void put(String nombre, Producto producto){
        while(lleno){
            try {
                System.out.println("Cinta llena, "+nombre+" tendra que esperar");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        productos.add(producto);
        System.out.println("Productos en la cinta -> "+productos.size());

        if(productos.size()==MAX) {
            lleno = true;
        }else{
            lleno = false;
        }

        notifyAll();
    }

    public synchronized long read(String nombre){
        while(productos.size()==0){
            try {
                System.out.println("No hay productos en la cinta, esperando productos");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Producto p = productos.peek();
        System.out.println("La cajera "+nombre+" esta combrando el producto "+p.getNombre());

        productos.remove();
        System.out.println("Productos en la cinta -> "+productos.size());

        if(productos.size()!=MAX){
            lleno = false;
        }
        notifyAll();
        return p.getPeso();
    }

    public int getListaProductos(){
        return productos.size();
    }
}
