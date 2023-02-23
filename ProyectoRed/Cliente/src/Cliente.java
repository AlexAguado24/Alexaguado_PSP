import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        File ficheroZapas = new File("src/ficheroZapas");
        PrintWriter printWriter = null;

        try {

            Socket cliente = new Socket("127.0.0.1",6666);
            ObjectInputStream inObject = new ObjectInputStream(cliente.getInputStream());
            printWriter = new PrintWriter(new FileWriter(ficheroZapas));
            for (int i = 0; i < 7; i++) {
                Zapatilla zapa = (Zapatilla) inObject.readObject();
                System.out.println(zapa.getMarca());
                printWriter.println(zapa.getMarca());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
