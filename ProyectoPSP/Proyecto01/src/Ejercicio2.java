import java.io.IOException;

public class Ejercicio2 {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            p = r.exec("notepad");
            p.destroy();
            System.out.println(p.waitFor());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
