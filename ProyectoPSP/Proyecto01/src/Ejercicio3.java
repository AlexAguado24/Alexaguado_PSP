import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd","/c","dir");
        File fout = new File();
        pb.redirectOutput(fout);
        pb.start();
    }
}
