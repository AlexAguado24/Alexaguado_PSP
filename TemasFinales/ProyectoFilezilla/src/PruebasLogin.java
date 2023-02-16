import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

public class PruebasLogin {
    public static void main(String[] args) {
        File claves = new File("C:\\Users\\Usuario\\Desktop\\claves\\claves.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe nombre");
        String nombre = sc.next();
        PruebasLogin main = new PruebasLogin();

        main.escribirClave(nombre,claves);
        String lectura = main.leerFichero(claves);

        //System.out.println(lectura);

        String[] nombreClave =  lectura.split(",");
        String clave = nombreClave[1];

        main.comprobarClave(clave);


    }
    public void comprobarClave(String claveComprobar){
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Escribe claveNueva");
        String claveNueva = sc.next();*/
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init (128);
            SecretKey clave = kg.generateKey();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clave);

            //CIFRAMOS TEXTO
            byte textoPlano[] = "adios".getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);


            System.out.println(claveComprobar+"antes");
            System.out.println(new String(textoCifrado)+"antes");

            if (new String(textoCifrado).equals(claveComprobar)) {
                System.out.println("Las claves son iguales");
            } else {
                System.out.println(claveComprobar+"despues");
                System.out.println(new String(textoCifrado)+"despues");
                System.out.println("Claves diferentes joder");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void escribirClave(String nombre,File file){
        PrintWriter printWriter = null;
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
            /*printWriter = new PrintWriter(new FileWriter(file));
            printWriter.println(nombre+","+new String(textoCifrado2));*/

            //DESCIFRAMOS TEXTO
            /*c.init(Cipher.DECRYPT_MODE, clave);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: "+ new String(desencriptado));*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    public String leerFichero(File file){
        BufferedReader bufferedReader = null;
        String lecturaCompleta2 = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String lectura = null;
            StringBuilder lecturaCompleta = new StringBuilder();
            /*String linea = bufferedReader.readLine();
            System.out.println(linea);*/
            while ((lectura = bufferedReader.readLine()) != null) {
                //System.out.println(lectura);
                lecturaCompleta.append(lectura);
            }
            System.out.println("La lectura completa es: " + lecturaCompleta);
            lecturaCompleta2 = String.valueOf(lecturaCompleta);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader!=null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return lecturaCompleta2;
    }
}
