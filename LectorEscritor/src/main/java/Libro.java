public class Libro {

    private int peticionesEscritura = 0;
    private boolean escribiendo = false;
    private int leyendo = 0;

    public synchronized void leer(Lector l){
        while(escribiendo || peticionesEscritura>0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        leyendo++;
        System.out.println("el lector "+l.getNombre()+" esta leyendo");
        try {
            l.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El lector "+l.getNombre()+" ha terminado de leer");
        leyendo--;
        notifyAll();
    }

    public synchronized void escribir(Escritor e){
        peticionesEscritura++;
        while(leyendo>0 || escribiendo){
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        peticionesEscritura--;
        escribiendo = true;
        System.out.println("El escritor "+e.getNombre()+" esta escribiendo");
        try {
            e.sleep(500);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("El escritor "+e.getNombre()+" a dejado de escribir");
        escribiendo=false;
        notifyAll();
    }
}
