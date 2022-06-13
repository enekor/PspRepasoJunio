package ClienteServidor.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private ServerSocket server;
    private Socket cliente;
    private final int PORT = 5555;

    public Servidor(){
        try {
            server = new ServerSocket(PORT);
            do{
                System.out.println("Esperando cliente");
                cliente = server.accept();

                new GestionCliente(cliente).start();
                System.out.println("Cliente delegado a hilo de gestion");
            }while(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Servidor();
    }
}
