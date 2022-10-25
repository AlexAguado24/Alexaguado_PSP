import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Padre {

    public static void main(String[] args) throws IOException {
        String linea;
        String tipo;
        Process hijo = new ProcessBuilder("java","C:\\Users\\Usuario DAM2\\Desktop\\DAM2\\Alexaguado_PSP\\EjercicioPsp\\src\\Hijo.java").start();
        BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
        PrintStream ps = new PrintStream(hijo.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
        System.out.println("-----Hola hijo------");
        System.out.println("");
        linea = in.readLine();
        ps.println(linea);
        linea = br.readLine();
        System.out.println(linea);
    }
}
