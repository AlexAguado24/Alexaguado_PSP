import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente2 {
    public static void main(String[] args) {
        {
            Socket cliente;

            try {
                cliente = new Socket("localhost",9999);
                PrintWriter out = new PrintWriter(cliente.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                out.println("hola papi");
                out.println("que mirás bobo");
                out.println("anda palla bobo");

                System.out.println(in.readLine());

                out.close();
                in.close();
                cliente.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}