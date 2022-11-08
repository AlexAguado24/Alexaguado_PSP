import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Conection {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.jesusninoc.com/");
        String imputLine;
        URLConnection hc = url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(hc.getInputStream()));

        while (((imputLine = br.readLine()) != null)) {
            System.out.println(imputLine);
        }
        br.close();
    }
}
