import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket servidor;
        BufferedReader b;

        {
            try {
                servidor = new ServerSocket(1234);
                Socket cliente = servidor.accept();

                b = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                System.out.println(b.readLine());
                //cliente.close();
                //servidor.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
