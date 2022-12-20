import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(6666);

            Socket cliente = servidor.accept();

            ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

            Zapatilla zapa = new Zapatilla("nike");
            outObjeto.writeObject(zapa);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
