import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilo extends Thread{

    private Socket cliente;

    public Hilo(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        try{
            PrintWriter out = new PrintWriter(cliente.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String inputLine = in.readLine();

            System.out.println("recibido: "+ inputLine);

            out.println("Nos ha llegado el mensaje");

            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
