import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        Socket cliente;
        PrintStream p;
        //BufferedReader b;
        {
            try {
                cliente = new Socket("192.168.2.21", 1234);

                p = new PrintStream(cliente.getOutputStream());
                p.println("hola");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
