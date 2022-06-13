package clienteServidorSeguro;

import clienteServidorSeguro.cifrado.AES;

public class app {
    public static void main(String[] args) {
        String pass = "pass123456789012";
        int a = 56;

        String codificado = AES.getInstance().codificar(String.valueOf(a),pass);

        System.out.println(codificado);

        String decodificado = AES.getInstance().decodificar(codificado,pass);

        System.out.println(decodificado);
    }
}
