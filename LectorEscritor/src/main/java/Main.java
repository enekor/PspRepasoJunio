import java.util.List;

public class Main {
    public static void main(String[] args) {
        Libro l = new Libro();
        List<Escritor> escritors = List.of(
                new Escritor("Pepe",5,l),
                new Escritor("Juan",5,l)
        );
        List<Lector> lectores = List.of(
                new Lector("Alberto",5,l),
                new Lector("Jose",5,l)
        );
        escritors.forEach(Thread::start);
        lectores.forEach(Thread::start);
    }
}
