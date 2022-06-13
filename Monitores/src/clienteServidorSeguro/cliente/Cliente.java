package clienteServidorSeguro.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import clienteServidorSeguro.cifrado.AES;

public class Cliente {

    private final int PUERTO = 5555;
    private SSLSocket servidor;
    private SSLSocketFactory sslSocketFactory;
    private boolean salir = false;
    private DataInputStream input;
    private DataOutputStream output;

    public void iniciar(){
        String ficheroJks = System.getProperty("user.dir")+File.separator+"src"+File.separator+"clienteServidorSeguro"+File.separator+"claves"+File.separator+"myKeyStore.jks";
        
        System.setProperty("javax.net.ssl.trustStore", ficheroJks);
        System.setProperty("javax.net.ssl.trustStorePassword","123456");

        try{
            sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            servidor = (SSLSocket) sslSocketFactory.createSocket("localhost",PUERTO);
            input = new DataInputStream(servidor.getInputStream());
            output = new DataOutputStream(servidor.getOutputStream());

            System.out.println("conectado al servidor");

            iniciarIntercambio();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void iniciarIntercambio(){
        AES aes = AES.getInstance();
        String pass = "pass123456789012";
        try {

            while(!salir){
                int random = (int)(Math.random()*100)+1;
                System.out.println("numero generado: "+random);
                
                String codificado = aes.codificar(String.valueOf(random),pass);
                System.out.println("decodificado "+aes.decodificar(codificado,pass));

                System.out.println("Enviando numero");
                output.writeUTF(codificado);
                salir = Boolean.parseBoolean(aes.decodificar(input.readUTF(),pass));

                if(salir){
                    System.out.println("Se consiguio el numero de salida, buenas tardes");
                }else{
                    System.out.println("No es el numero deseado, seguimos buscando");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Cliente().iniciar();
    }
}
