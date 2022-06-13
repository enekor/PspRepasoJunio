package ClienteServidor.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    private Socket servidor;
    private final int PORT = 5555;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean salir = false;

    public Cliente(){
        try {
            servidor = new Socket("localhost",PORT);
            input = new DataInputStream(servidor.getInputStream());
            output = new DataOutputStream(servidor.getOutputStream());

            while(!salir){
                int random = (int)(Math.random()*10)+1;

                output.writeInt(random);
                System.out.println("Server message -> "+input.readUTF());

                if(random == 5) 
                    salir = true;
            }
            System.out.println("Cliente ha terminado");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Cliente();
    }
    
}
