package conMasDeUno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class Ping implements Callable<String> {

    private String uri;

    public Ping(String uri){
        this.uri = uri;
    }


    @Override
    public String call() throws Exception {
        try {
            ProcessBuilder proceso = new ProcessBuilder();
            proceso.command("powershell","/c","wsl ping -c 4 "+uri+" | wsl grep mdev");

            Process grep = proceso.start();

            BufferedReader input = new BufferedReader(new InputStreamReader(grep.getInputStream()));

            return uri+" -> "+input.readLine();

        } catch (IOException e) {
            System.err.println("No se ha podido completar la operacion");
            return "";
        }
    }
}

