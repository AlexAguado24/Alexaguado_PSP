import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        ServerSocket server;
        BufferedReader br = null;
        FileOutputStream fs;
        String lecturaFichero = "";
        FileWriter fw;
        BufferedWriter bwr;
        File ficheroBBDD = new File("src/ficheroBaseDatos.txt");
        PrintWriter printWriter = null;
        ArrayList<String> usuariosCreados = new ArrayList<>();
        ArrayList<Usuario> usuariosRecuperados = new ArrayList<>();
        Usuario usuarioRecuperado = null;
        Usuario usuario = null;

        try {
            System.out.println(InetAddress.getLocalHost());
            System.out.println("Puerto: " + 1234);
            System.out.println("CREANDO");
            System.out.println("Ingrese 0 y 0 para dejar de registrar usuarios");
            server = new ServerSocket(1234);
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroBBDD));
            bw.write("");
            bw.close();
            String cero = " 31BCA02094EB78126A517B206A88C73CFA9EC6F704C7030D18212CACE820F025F00BF0EA68DBF3F3A5436CA63B53BF7BF80AD8D5DE7D8359D0B7FED9DBC3AB99";
            while (!lecturaFichero.equals(0 + cero)) {
                //leyendo fichero
                String lectura = null;
                br = new BufferedReader(new FileReader(ficheroBBDD));
                while ((lectura = br.readLine()) != null) {
                    String[] separoNomyCont = lectura.split(" ");
                    if (separoNomyCont.length != 2) {
                        int conteo = 3;
                        String nombre = null;
                        String pasword = null;
                        for (String item : separoNomyCont) {
                            if (conteo % 2 != 0) {
                                nombre = item;
                                conteo++;
                            } else {
                                pasword = item;
                                conteo++;
                            }
                        }
                        usuarioRecuperado = new Usuario(nombre, pasword);
                        usuariosRecuperados.add(usuarioRecuperado);

                        // envio a android el hilo

                        for (Usuario usuarioDeFichero : usuariosRecuperados) {
                            System.out.println("DentroDelArraylist " + usuarioDeFichero.getNombre());
                        }
                        //termina el hilo
                    } else {
                        int cont = 0;
                        String nomPrimer = null, contPrimer = null;

                        for (String it : separoNomyCont) {
                            if (cont == 0) {
                                nomPrimer = it;
                                //System.out.println("it "+ it);
                                cont++;
                            } else {
                                contPrimer = it;
                            }
                        }
                        usuarioRecuperado = new Usuario(nomPrimer, contPrimer);
                        //        System.out.println("Nom " + usuarioRecu.getNombre() + " COntr " + usuarioRecu.getContrasenia());
                        System.out.println("Usuario " + usuarioRecuperado.getNombre() + " ingresado");
                        usuariosRecuperados.add(usuarioRecuperado);  //Meto el 1er usuario
                    }
                }
                //termina la lectura
                //empieza a escribir
                Socket cliente = server.accept();
                br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                fw = new FileWriter(ficheroBBDD.getAbsoluteFile(), true);
                bwr = new BufferedWriter(fw);
                lecturaFichero = br.readLine();
                String[] nombreYApellido = lecturaFichero.split(" ");

                printWriter = new PrintWriter(ficheroBBDD);
                String usuarioCompleto = nombreYApellido[0] + " " + nombreYApellido[1];
                //Escribo el usuario
                for (int i = 0; i < 1; i++) {
                    usuariosCreados.add(usuarioCompleto);
                }
                fw.write(String.valueOf(usuariosCreados));
                cliente.close();
                fw.close();
            }
            System.out.println("Buscando el usuario");
            System.out.println("Ingresa 1 y 0 para terminar la busqueda");
            while (!lecturaFichero.equals(1 + cero)) {
                Socket cliente = server.accept();
                br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                fw = new FileWriter(ficheroBBDD.getAbsoluteFile(), true);
                lecturaFichero = br.readLine();
                String[] usuDos = lecturaFichero.split(" ");

                for (Usuario usuarioDeFichero : usuariosRecuperados) {
                    if (usuDos[0].equals(usuarioDeFichero.getNombre())) {
                        System.out.println(usuDos[0] + " coincide con " + usuarioDeFichero.getNombre());
                        System.out.println("Loguin");
                    } else {
                        System.out.println("No coincide");
                    }
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}