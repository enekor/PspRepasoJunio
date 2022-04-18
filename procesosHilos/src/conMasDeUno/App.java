package conMasDeUno;

import java.util.List;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        List<Callable<String>> callables = List.of(new Ping("google.es"), new Ping("twitter.com"), new Ping("github.com"));

        try {
            System.out.println("procesando todas peticiones");
            List<Future<String>> futures = es.invokeAll(callables);
            System.out.println("resultado");
            futures.forEach(f ->{
                try {
                    System.out.println(f.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("procesando todas peticiones");
            String rest = es.invokeAny(callables);
            System.out.println("resultado "+rest);

            System.exit(0);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
