import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(6666);
            ArrayList<Zapatilla> listaZapas = new ArrayList<>();

            Socket cliente = servidor.accept();

            ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

            Zapatilla zapa = new Zapatilla("adidas");
            Zapatilla zapa2 = new Zapatilla("nike");
            Zapatilla zapa3 = new Zapatilla("puma");
            Zapatilla zapa4 = new Zapatilla("joma");
            Zapatilla zapa5 = new Zapatilla("jordan");
            Zapatilla zapa6 = new Zapatilla("fila");
            Zapatilla zapa7 = new Zapatilla("DC");
            listaZapas.add(zapa);
            listaZapas.add(zapa2);
            listaZapas.add(zapa3);
            listaZapas.add(zapa4);
            listaZapas.add(zapa5);
            listaZapas.add(zapa6);
            listaZapas.add(zapa7);
            for (Zapatilla item : listaZapas) {
                outObjeto.writeObject(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
