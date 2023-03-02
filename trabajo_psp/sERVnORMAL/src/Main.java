import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ServerSocket servidor;
        BufferedReader br = null;
        FileOutputStream fs;
        String lectura = "";
        FileWriter fw;
        BufferedWriter bwr;
        File ficheroUsuario = new File("src/ficheroUsuario.txt");

        PrintWriter printWriter = null;

        ArrayList<String> arrayUsuarios = new ArrayList<>();
        ArrayList<Usuario> arrayUsuariosRecu = new ArrayList<>();
        Usuario usuarioRecu = null;

        Usuario usuario = null;

        int socket = 1234;

        try {
            if (!ficheroUsuario.exists()) {
                ficheroUsuario.createNewFile();
            }
            System.out.println(InetAddress.getLocalHost());
            System.out.println("Puerto: " + socket);
            System.out.println("#############################################    CREANDO USUARIO  ######################################");
            System.out.println("Ingrese nom 0 cont 0 para dejar de cargar");
            servidor = new ServerSocket(socket);
            //Vaciar fichero
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroUsuario));
            bw.write("");
            bw.close();
            //fin vaciar
            String cero=" 31BCA02094EB78126A517B206A88C73CFA9EC6F704C7030D18212CACE820F025F00BF0EA68DBF3F3A5436CA63B53BF7BF80AD8D5DE7D8359D0B7FED9DBC3AB99";
            while (!lectura.equals(0+cero)) {



                //######################################                        LECTURA           ###################################################
                String lectura1 = null;
             //   String lecturaCompleta = "";/*String linea = bufferedReader.readLine();System.out.println(linea);*/
                br = new BufferedReader(new FileReader(ficheroUsuario));
                while ((lectura1 = br.readLine()) != null) {
                    String[] usuCortEsp = lectura1.split(" ");
                    if (usuCortEsp.length != 2) {
                        int cont = 3;
                        String nomPrimer = null, contPrimer = null;
                        for (String it : usuCortEsp) {
                            if (cont % 2 != 0) {
                                nomPrimer = it;
                                cont++;
                            } else {
                                contPrimer = it;
                                cont++;
                            }
                        }
                        usuarioRecu = new Usuario(nomPrimer, contPrimer);
                        arrayUsuariosRecu.add(usuarioRecu);  //Meto el 1er usuario

//###################    HILO ENVIA A ANDROID ####################################################
                        Usuario usuEntrante = usuario;
                 /*       class Proceso extends Thread {
                            public Proceso() {
                                super ();
                            }
                            public void run(){
                                try {
                                    Socket socket1=new Socket("192.168.137.1", 1234);
                                    PrintWriter printWriter1=new PrintWriter(socket1.getOutputStream());
                                    BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(socket1.getOutputStream());
                                    System.out.println("Pasando Hilo new");
                                    printWriter1.write(String.valueOf(arrayUsuariosRecu));
                                    printWriter1.close();
                                    socket1.close();

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Thread hilo=new Proceso();
                                Proceso hilo2= new Proceso();
                                hilo.start();
                            }
                        }*/

                                //TODO  VEO LOS USUARIOS QUITADOS DE LA BD Y METIDOS EN EL ARRAYLIST
                       //         System.out.println("UsuEntran "+ usuEntrante.getNombre());

                                for (Usuario usuarioDeFichero : arrayUsuariosRecu) {
                                    System.out.println("DentroDelArraylist "+usuarioDeFichero.getNombre());

                                   /* if (usuEntrante.getNombre().equals(usuarioDeFichero.getNombre())){
                                        System.out.println("el usuario ingresado "+ usuEntrante.getNombre() + " es igual a "+ usuarioDeFichero.getNombre() );
                                    }*/
                                }//System.out.println("Entra "+usuario.getNombre());


//###################    HILO ENVIA A ANDROID    FIN ####################################################




                    } else {
                        int cont = 0;
                        String nomPrimer = null, contPrimer = null;

                        for (String it : usuCortEsp) {
                            if (cont == 0) {
                                nomPrimer = it;
                                //System.out.println("it "+ it);
                                cont++;
                            } else {
                                contPrimer = it;
                            }
                        }
                        usuarioRecu = new Usuario(nomPrimer, contPrimer);
                //        System.out.println("Nom " + usuarioRecu.getNombre() + " COntr " + usuarioRecu.getContrasenia());
                        System.out.println("Usuario "+ usuarioRecu.getNombre() +" ingresado");
                        arrayUsuariosRecu.add(usuarioRecu);  //Meto el 1er usuario
                    }
                }
//##############################################            LECTURA  FIN    #############################################################################
//######################################                        ESCRITURA   DE FICHERO        ###################################################
                Socket cliente = servidor.accept();
                br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                fw = new FileWriter(ficheroUsuario.getAbsoluteFile(), true);
                bwr = new BufferedWriter(fw);
                lectura = br.readLine();
                String[] nomApas = lectura.split(" ");
            //    usuario = new Usuario(nomApas[0], nomApas[1]);

                    printWriter = new PrintWriter(ficheroUsuario);
                    String nomStr = nomApas[0] + " " + nomApas[1];


                        //TODO  INGRESO EL USUARIO
                    for (int i = 0; i < 1; i++) {
                        arrayUsuarios.add(nomStr);


                    }

                fw.write(String.valueOf(arrayUsuarios));
                    cliente.close();
                    fw.close();

               // System.out.println("Entra "+ usuario.getNombre());

//######################################                       FIN  ESCRITURA DE FICHERO           ###################################################
            }
            System.out.println("#############################################    VIENDO SI EXISTE EL USUARIO  ######################################");
            System.out.println("1 y 0 termina la busqueda de usuario");
            while (!lectura.equals(1+cero)) {
                Socket cliente = servidor.accept();
                br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                fw = new FileWriter(ficheroUsuario.getAbsoluteFile(), true);
                lectura = br.readLine();
                String[] usuDos = lectura.split(" ");


                //TODO FALTA AGREGAR EL [
                for (Usuario usuarioDeFichero : arrayUsuariosRecu) {
                    if (usuDos[0].equals(usuarioDeFichero.getNombre())     ) {
                        System.out.println(usuDos[0] + " son iguales " + usuarioDeFichero.getNombre());
                        System.out.println("USUARIO LOGUEADO");
                    }else {
                        System.out.println("no son iguales");
                    }
            }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}






