import java.io.IOException;

public class P3 {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/C", "\"C:\\Program Files\\Java\\jdk-17\\bin\\java.exe\" P2 -cp 'C:\\Users\\Usuario DAM2\\Desktop\\ejecutar fatal'");
        try {
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
