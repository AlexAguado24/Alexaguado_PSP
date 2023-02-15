import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class Ejemplo10 {
    public static void main(String args[]) {
        /*try {
            //ALGORITMO DES
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
            Key key = kg.generateKey();

            c.init(Cipher.ENCRYPT_MODE, key);
            byte input[] = "Stand and unfold yourself".getBytes();
            byte encrypted[] = c.doFinal(input);
            byte iv[] = c.getIV();

            IvParameterSpec dps = new IvParameterSpec(iv);
            c.init(Cipher.DECRYPT_MODE, key, dps);
            byte output[] = c.doFinal(encrypted);
            System.out.println("The string was ");
            System.out.println(new String(output));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //ALGORITMO AES
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init (128);
            SecretKey clave = kg.generateKey();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clave);

            //CIFRAMOS TEXTO
            byte textoPlano[] = "adios".getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);
            System.out.println("Encriptado: "+ new String(textoCifrado));
            clave = kg.generateKey();

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("Clave.secreta"));
            out.writeObject(clave);
            out.close();

            //DESCIFRAMOS TEXTO
            /*c.init(Cipher.DECRYPT_MODE, clave);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: "+ new String(desencriptado));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
