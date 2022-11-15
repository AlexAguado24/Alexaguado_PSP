import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Hijo extends Thread {
    @Override
    public void run() {
        ArrayList<Zapatilla> zapatillas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Zapatilla zapatilla9 = new Zapatilla("nike");
            Zapatilla zapatilla10 = new Zapatilla("puma");
            zapatillas.add(zapatilla9);
            System.out.println(zapatilla9.marca);
            zapatillas.add(zapatilla10);
            System.out.println(zapatilla10.marca);
            System.out.println("Compraste "+(i+2)+" zapatillas");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static class Zapatilla implements Serializable {
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

    public static void main(String[] args) {

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
        Zapatilla zapatilla9 = new Zapatilla("nike");
        Zapatilla zapatilla10 = new Zapatilla("puma");


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
            enviar = br.readLine();
            for (Zapatilla item : zapatillas) {
                if (enviar.equalsIgnoreCase(item.getMarca())) {
                    cantidad++;
                }
            }
            System.out.println("Tengo " + cantidad + " zapatillas de esa marca");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
