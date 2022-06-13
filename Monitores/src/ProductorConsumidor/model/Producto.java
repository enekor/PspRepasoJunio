package ProductorConsumidor.model;

public class Producto {
    private int numeroSerie;
    private String nombreProductor;
    private long peso;

    public Producto(int numeroSerie, String nombreProductor, long peso) {
        this.numeroSerie = numeroSerie;
        this.nombreProductor = nombreProductor;
        this.peso = peso;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNombreProductor() {
        return nombreProductor;
    }

    public void setNombreProductor(String nombreProductor) {
        this.nombreProductor = nombreProductor;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }
    
    
}
