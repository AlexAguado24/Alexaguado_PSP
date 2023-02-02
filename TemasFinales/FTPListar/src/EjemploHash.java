import java.security.*;

public class EjemploHash {
    public static void main(String[] args) {

        MessageDigest md;

        String palabra = "hola";

        try {
            md = MessageDigest.getInstance("SHA512");
            byte dataByte[] = palabra.getBytes();
            md.update(dataByte);

            byte resumen[] = md.digest();

            System.out.println(new String(resumen));
            System.out.println(Hexadecimal(resumen));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static String Hexadecimal(byte[] resumen) {
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) hex += "0";
            hex += h;
        }
        return hex.toUpperCase();
    }
}
