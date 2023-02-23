import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Cliente2 {

    public static void main(String[] args) {

        ArrayList<Zapatilla> listaZapas = new ArrayList<>();
       // String[] lineas = { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "..." };

        /** FORMA 2 DE ESCRITURA. Con el fichero codificado en UTF-8 **/
        Writer out = null;
        try {
            Socket cliente = new Socket("127.0.0.1",6666);
            ObjectInputStream inObject = new ObjectInputStream(cliente.getInputStream());
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ficheroZapas"), "UTF-8"));

            for (int i = 0; i < 7; i++) {
                Zapatilla zapa = (Zapatilla) inObject.readObject();
                listaZapas.add(zapa);
            }

            // Escribimos linea a linea en el fichero
            for (Zapatilla zap : listaZapas) {
                try {
                    out.write(zap.getMarca()+"\n");
                } catch (IOException ex) {
                    System.out.println("Mensaje excepcion escritura: " + ex.getMessage());
                }
            }

        } catch (UnsupportedEncodingException | FileNotFoundException ex2) {
            System.out.println("Mensaje error 2: " + ex2.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                out.close();
            } catch (IOException ex3) {
                System.out.println("Mensaje error cierre fichero: " + ex3.getMessage());
            }
        }
    }
}
