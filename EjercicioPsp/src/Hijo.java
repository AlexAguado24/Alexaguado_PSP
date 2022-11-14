import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Hijo {

    public static class Zapatilla implements Serializable{

        String marca;
        public Zapatilla(String marca) {
            this.marca = marca;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }
    }

    public static void main(String[] args){

        ArrayList<Zapatilla> zapatillas = new ArrayList<>();
        //Scanner sc = new Scanner(System.in);


        Zapatilla zapatilla = new Zapatilla("nike");
        Zapatilla zapatilla2 = new Zapatilla("adidas");
        Zapatilla zapatilla3 = new Zapatilla("puma");
        Zapatilla zapatilla4 = new Zapatilla("nike");
        Zapatilla zapatilla5 = new Zapatilla("adidas");
        Zapatilla zapatilla6 = new Zapatilla("puma");
        Zapatilla zapatilla7 = new Zapatilla("nike");
        Zapatilla zapatilla8 = new Zapatilla("adidas");
        Zapatilla zapatilla9 = new Zapatilla("puma");
        Zapatilla zapatilla10 = new Zapatilla("nike");

        zapatillas.add(zapatilla);
        zapatillas.add(zapatilla2);
        zapatillas.add(zapatilla3);
        zapatillas.add(zapatilla4);
        zapatillas.add(zapatilla5);
        zapatillas.add(zapatilla6);
        zapatillas.add(zapatilla7);
        zapatillas.add(zapatilla8);
        zapatillas.add(zapatilla9);
        zapatillas.add(zapatilla10);

        int cantidad = 0;
        String enviar;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            enviar = new String();
            enviar =br.readLine();
            for (Zapatilla item: zapatillas) {
                if (enviar.equalsIgnoreCase(item.getMarca())) {
                    cantidad++;
                }
            }
            System.out.println("Tengo "+ cantidad +" zapatillas de esa marca");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*System.out.println("¿Cuantas zapatillas quieres vender?");
        int total = sc.nextInt();
        if (total <= cantidad) {
            VenderZapatillas vz = new VenderZapatillas(total);
            for (int i = 0; i < zapatillas.size(); i++) {
                vz.start();
            }
            System.out.println("He vendido "+total+" Zapatillas");
            System.out.println("Ahora me quedan "+(cantidad-total));
        } else {
            System.out.println("No tienes tantas zapatillas");
        }*/
    }

    /*public static class VenderZapatillas extends Thread {

        private int vender;

        public VenderZapatillas(int vender){
            this.vender = vender;
        }
        public void run(){
            for (int i = vender; i >=0; i--) {
                System.out.println("Vendido");
            }
        }
    }*/
}
