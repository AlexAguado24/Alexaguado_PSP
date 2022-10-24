import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hijo {

    public static void main(String[] args) throws IOException {
        String enviar;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        enviar = new String();
        enviar=br.readLine();
        System.out.println("Soy el hijo "+enviar);
    }
}
