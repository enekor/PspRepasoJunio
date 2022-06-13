package clienteServidorSeguro.cifrado;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static AES aes = null;
    private AES(){}

    public static AES getInstance(){
        if(aes==null){
            aes = new AES();
        }
        return aes;
    }

    public String codificar(String cadena,String pass){

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();
            key = new SecretKeySpec(pass.getBytes("UTF-8"), 0, 16, "AES");

            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] encriptado = aes.doFinal(cadena.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(encriptado);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String decodificar(String cadenaCodificada,String pass){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();
            key = new SecretKeySpec(pass.getBytes(), 0, 16, "AES");
            
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.DECRYPT_MODE, key);
            byte[] encriptado = Base64.getDecoder().decode(cadenaCodificada);
            byte[] desencriptado = aes.doFinal(encriptado);

            return new String(desencriptado);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}