package ClienteServidor.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GestionCliente extends Thread{

    private Socket cliente = null;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean salir = false;

    public GestionCliente(Socket cliente) {
        
        try {
            this.cliente = cliente;
            this.input = new DataInputStream(cliente.getInputStream());
            this.output = new DataOutputStream(cliente.getOutputStream());

            while(!salir){
                int numero = input.readInt();

                if(numero !=5){
                    output.writeUTF("Numero recibido "+numero);
                }else{
                    output.writeUTF("recibido "+numero+", cerrando sesion");
                    salir = true;
                }
            }

            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        
    }
    
}
