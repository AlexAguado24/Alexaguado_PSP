import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionPOST {
    public static void main(String[] args) {
        String datos = "nombre=ddd&edad=66";

        URL url = null;
        try {
            url = new URL("http://localhost/GetPost/examplepost.php");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        String inputline;

        HttpURLConnection hc = null;

        try {
            hc = (HttpURLConnection) url.openConnection();
            hc.setRequestMethod("POST");
            hc.setDoOutput(true);
            hc.getOutputStream().write(datos.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(hc.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true)
        {
            try {
                if (!((inputline = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(inputline);
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

