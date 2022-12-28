import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("127.0.0.1",6666);

            ObjectInputStream inObject = new ObjectInputStream(cliente.getInputStream());
            for (int i = 0; i < 7; i++) {
                Zapatilla zapa = (Zapatilla) inObject.readObject();
                System.out.println(zapa.getMarca());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
