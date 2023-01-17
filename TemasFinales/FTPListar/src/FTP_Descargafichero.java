import org.apache.commons.net.ftp.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Daniel Marcos Lorrio
 */
public class FTP_Descargafichero {

    public static void main(String[] args) {

        FTPClient cliente = new FTPClient(); //cliente
        String servidor = "ftp.rediris.es"; //servidor
        String user = "dam2";
        String pasw = "dam2";

        try {
            System.out.println("Conectandose a " + servidor);
            cliente.connect(servidor);
            boolean login = cliente.login(user, pasw);
            //String direc = "curso";

            if (login) {
                // cliente.changeWorkingDirectory(direc);
                cliente.setFileType(FTP.ASCII_FILE_TYPE);
                //  System.out.println("hola");
                //Stream de entrada con el fichero a descargar
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\PROFESOR\\Desktop\\ejecutarfatal\\mensaje.msg"));

                if (cliente.retrieveFile("wellcome.msg", out)) {
                    System.out.println("Recuperado correctamente...");
                } else {
                    System.out.println("No se ha podido descargar...");
                }

                out.close(); //cerrar flujo
                cliente.logout(); //logout del usuario
                cliente.disconnect(); // desconexion del servidor
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}