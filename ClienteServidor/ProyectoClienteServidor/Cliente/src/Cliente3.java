import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Period;

public class Cliente3 {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("127.0.0.1",9999);

            ObjectInputStream inObject = new ObjectInputStream(cliente.getInputStream());

            Persona persona = (Persona) inObject.readObject();
            System.out.println(persona.getNombre());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
