import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

public class CifradoAES {
    public static void main(String[] args) {
        File claves = new File("C:\\Users\\Usuario DAM2\\Desktop\\claves\\claves.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe nombre");
        String nombre = sc.next();
        System.out.println("Escribe clave a encriptar");
        String claveEncriptar = sc.next();
        CifradoAES main = new CifradoAES();
        PrintWriter printWriter = null;
        SecretKey clave = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init (128);
            clave = kg.generateKey();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clave);

            //CIFRAMOS TEXTO
            byte textoPlano[] = claveEncriptar.getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);
            System.out.println("Encriptado: "+ new String(textoCifrado));
            printWriter = new PrintWriter(new FileWriter(claves));
            printWriter.println(nombre+"#"+new String(textoCifrado));

            //DESCIFRAMOS TEXTO
            c.init(Cipher.DECRYPT_MODE, clave);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: "+ new String(desencriptado));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
        BufferedReader bufferedReader = null;
        String lecturaCompleta2 = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(claves));
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


        /*SecretKey clave =  main.escribirClave(nombre,claveEncriptar,claves);
        String lectura = main.leerFichero(claves);*/

        //System.out.println(lectura);

        String[] nombreClave =  lecturaCompleta2.split("#");
        String claveEscrita = nombreClave[1];
        System.out.println(claveEscrita);
        //main.comprobarClave(claveEscrita,clave);

        //Scanner sc = new Scanner(System.in);
        System.out.println("Escribe claveNueva");
        String claveNueva = sc.next();
        try {

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clave);

            //CIFRAMOS TEXTO
            byte textoPlano[] = claveEscrita.getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);
            //CIFRAMOS TEXTO
            c.init(Cipher.DECRYPT_MODE, clave);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: "+ new String(desencriptado));


            if (desencriptado.equals(claveNueva)) {
                System.out.println("Las claves son iguales");
            } else {
                System.out.println(claveEscrita+"despues");
                System.out.println("Claves diferentes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void comprobarClave(String claveComprobar, SecretKey clave){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe claveNueva");
        String claveNueva = sc.next();
        try {

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clave);

            //CIFRAMOS TEXTO
            byte textoPlano[] = claveComprobar.getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);
            //CIFRAMOS TEXTO
            c.init(Cipher.DECRYPT_MODE, clave);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: "+ new String(desencriptado));


            if (new String(desencriptado).equals(claveNueva)) {
                System.out.println("Las claves son iguales");
            } else {
                System.out.println(claveComprobar+"despues");
                System.out.println("Claves diferentes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public SecretKey escribirClave(String nombre,String claveEncriptar,File file){
        PrintWriter printWriter = null;
        SecretKey clave = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init (128);
            clave = kg.generateKey();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, clave);

            //CIFRAMOS TEXTO
            byte textoPlano[] = claveEncriptar.getBytes();
            byte textoCifrado[] = c.doFinal(textoPlano);
            System.out.println("Encriptado: "+ new String(textoCifrado));
            printWriter = new PrintWriter(new FileWriter(file));
            printWriter.println(nombre+","+new String(textoCifrado));

            //DESCIFRAMOS TEXTO
            c.init(Cipher.DECRYPT_MODE, clave);
            byte desencriptado[] = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: "+ new String(desencriptado));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
        return clave;
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
