package ProductorConsumidor.consumidor;

import ProductorConsumidor.model.Producto;
import ProductorConsumidor.monitor.Tienda;

public class Consumidor extends Thread{

    private Tienda tienda;
    private String nombre;

    public Consumidor(Tienda t,String nombre){
        tienda = t;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        
        for(int i = 0;i<10;i++){
            var productos = tienda.sacarproductos(nombre);
            System.out.println("El cliente "+this.nombre+" va a procesar los productos");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String log = "";
            for(Producto p:productos){
                log+= "Lote: "+p.getNumeroSerie()+", productor: "+p.getNombreProductor()+"\n";
            }

            System.out.println(log);
        }
    }
}