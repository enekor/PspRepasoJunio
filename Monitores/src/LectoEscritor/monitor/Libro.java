package LectoEscritor.monitor;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    
    private boolean isWriting = false;
    private boolean isReading = false;
    private int writingQueue = 0;
    private List<Integer> numbers = new ArrayList<>();

    public synchronized void escribir(String name,List<Integer> addNumbers){

        writingQueue++;
    
        while(isWriting || isReading){
            System.out.println("El escritor "+name+" esta esperando su turno");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isWriting = true;
        writingQueue--;

        System.out.println("El escritor "+name+" escribe en la lista de numeros");

        numbers.addAll(addNumbers);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isWriting = false;
        notifyAll();
        
    }

    public synchronized List<Integer> leer(String name){

        while(isWriting && writingQueue>0 && !numbers.isEmpty()){
            System.out.println("El lector "+name+" esta esperando a que se libere el libro");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isReading = true;

        System.out.println("El lector "+name+" empieza a leer");

        int start = (int)(Math.random()*numbers.size())+1;
        int end = (int)(Math.random()*numbers.size())+1;

        List<Integer> ans = new ArrayList<>();

        for(int i = (start-1);i<(end-1);i++){
            ans.add(numbers.get(i));
        }

        isReading = false;
        notifyAll();

        return ans;
    }
}
