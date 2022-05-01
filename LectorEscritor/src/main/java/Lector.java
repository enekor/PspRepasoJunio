import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Lector extends Thread{
    
    private String nombre;
    private int repeticiones;
    private Libro libro;

    @Override
    public void run() {

        for(int i = 0;i<repeticiones;i++){
            libro.leer(this);
        }
    }
}
