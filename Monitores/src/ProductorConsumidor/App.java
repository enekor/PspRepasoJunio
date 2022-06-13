package ProductorConsumidor;

import java.util.List;

import ProductorConsumidor.consumidor.Consumidor;
import ProductorConsumidor.monitor.Tienda;
import ProductorConsumidor.productor.Productor;

public class App {
    public static void main(String[] args) {
        Tienda t = new Tienda();

        List<Productor> productores = List.of(
            new Productor(t, "Juan"),
            new Productor(t, "Alberto"),
            new Productor(t, "Pablo")
        );

        List<Consumidor> consumidores = List.of(
            new Consumidor(t, "Amazon"),
            new Consumidor(t, "Aliexpress"),
            new Consumidor(t, "BestBuy")
        );

        productores.forEach(Thread::start);
        consumidores.forEach(Thread::start);
    }
    
}
