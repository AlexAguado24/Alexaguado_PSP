import java.io.*;
import java.security.*;

public class Firma{

    public static void main(String args[]) {
        try {
            FileOutputStream fileout = new FileOutputStream("firma.dat");
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
            MessageDigest md = MessageDigest.getInstance("SHA");
            String datos = "Texto enviado";
            byte dataBytes[] = datos.getBytes();
            md.update(dataBytes);// TEXTO A RESUMIR
            byte resumen[] = md.digest(); // SE CALCULA EL RESUMEN
            dataOS.writeObject(datos); // se escriben los datos
            dataOS.writeObject(resumen);// se escribe el resumen
            dataOS.close();
            fileout.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}