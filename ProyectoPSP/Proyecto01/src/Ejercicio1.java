import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio1 {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            p = r.exec("cmd /c dir");
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = br.readLine())!= null) {
                System.out.println(linea);
            }
            br.close();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
}
