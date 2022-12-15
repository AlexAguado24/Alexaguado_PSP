import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor3 {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(9999);

            Socket cliente = servidor.accept();

            ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

            Persona persona = new Persona("Juanito");
            outObjeto.writeObject(persona);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
