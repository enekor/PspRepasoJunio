import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> ping = new ArrayList<>();
        String resultado = "";
        ProcessBuilder proceso = new ProcessBuilder();
        proceso.command("wsl","-c","ping -c 4 google.es");

        try {
            Process p = proceso.start();
            BufferedReader inputPing = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String linea = "";
            while((linea = inputPing.readLine())!=null){
                ping.add(linea);
            }

            Process grep = Runtime.getRuntime().exec("wsl grep mdev");
            OutputStream osGrep = grep.getOutputStream();
            PrintWriter grepWritter = new PrintWriter(new OutputStreamWriter(osGrep));
            ping.forEach(grepWritter::println);
            grepWritter.close();

            BufferedReader inputGrep = new BufferedReader(new InputStreamReader(grep.getInputStream()));

            String lineaGrep = "";
            while((lineaGrep = inputGrep.readLine())!=null){
                resultado = lineaGrep;
            }

            String x = Arrays.stream(resultado.split("/")).collect(Collectors.toList()).get(5);
            System.out.println(x);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
