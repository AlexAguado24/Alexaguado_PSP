import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Hilo extends Thread {

    private Socket cliente = null;
    public Hilo(Socket param){
        this.cliente = param;
    }

    public void run(){
        try{
            PrintWriter out = new PrintWriter(cliente.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String inputLine = in.readLine();

            System.out.println("revibido: "+ inputLine);

            out.println("Nos ha llegado el mensaje");

            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
