import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Escritor extends Thread{

    private String nombre;
    private int repeticiones;
    private Libro libro;

    @Override
    public void run() {

        for(int i = 0;i<repeticiones;i++){
            libro.escribir(this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
