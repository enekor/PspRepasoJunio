package clienteServidorSeguro.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLSocket;

import clienteServidorSeguro.cifrado.AES;

public class GestionCliente extends Thread{

    private SSLSocket cliente = null;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean salir = false;

    public GestionCliente(SSLSocket cliente){
        this.cliente = cliente;
        try {
            output = new DataOutputStream(cliente.getOutputStream());
            input = new DataInputStream(cliente.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        iniciarIntercambio();
    }

    private void iniciarIntercambio(){
        AES aes = AES.getInstance();
        String pass = "pass123456789012";

        while(!salir){
            try {

                String mensaje =aes.decodificar(input.readUTF(), pass);
                
                if(Integer.parseInt(mensaje) == 53){
                    output.writeUTF(aes.codificar(String.valueOf(true),pass));
                    salir = true;
                }else{
                    output.writeUTF(aes.codificar(String.valueOf(false),pass));
                }

                if(salir){
                    cliente.close();
                    System.out.println("Numero 53 adquirido, cerrando cliente");
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
