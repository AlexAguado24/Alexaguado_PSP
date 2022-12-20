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
            String inputLine2 = in.readLine();
            String inputLine3 = in.readLine();

            System.out.println("recibido: "+ inputLine);
            System.out.println(inputLine2);
            System.out.println(inputLine3);


            out.println("Nos ha llegado el mensaje");

            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
