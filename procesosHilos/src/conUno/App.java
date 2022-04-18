package conUno;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Solo con hilo
        /*conUno.Ping p = new conUno.Ping("google.es");
        p.start();*/

        // con future
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<String> google = es.submit(new Ping("google.es"));


        while(!google.isDone()){
            System.out.println("procesando");
            Thread.sleep(500);
        }

        var result = google.get();
        System.out.println(result);

    }
}
