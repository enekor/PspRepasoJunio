package clienteServidorSeguro.servidor;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Servidor {
    private final int PUERTO = 5555;
    private SSLServerSocketFactory serverFactory; 
    private SSLServerSocket servidor;
    private SSLSocket cliente = null;

    public void iniciar(){
        String ficheroJks = System.getProperty("user.dir")+File.separator+"src"+File.separator+"clienteServidorSeguro"+File.separator+"claves"+File.separator+"myKeyStore.jks";

        System.setProperty("javax.net.ssl.keyStore", ficheroJks);
        System.setProperty("javax.net.ssl.keyStorePassword","123456");

        try{
            this.serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            this.servidor = (SSLServerSocket) serverFactory.createServerSocket(this.PUERTO);

            conectar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void conectar(){
        while(true){
            System.out.println("Esperando cliente");
            try {
                cliente = (SSLSocket) servidor.accept();

                new GestionCliente(cliente).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Servidor().iniciar();
    }
}
