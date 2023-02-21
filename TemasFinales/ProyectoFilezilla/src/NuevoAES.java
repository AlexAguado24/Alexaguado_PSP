import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class NuevoAES {

    private static Cipher cipher = null;

    public static void main(String[] args) throws Exception{
        // Clave -> Clave en base 64
        // 1234567891234567 -> MTIzNDU2Nzg5MTIzNDU2Nw==
        // decode the base64 encoded string



        byte[] decodedKey = Base64.getDecoder().decode("MTIzNDU2Nzg5MTIzNDU2Nw==");

        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        System.out.println("originalKey: " + decodedKey);

        cipher = Cipher.getInstance("AES");

        String clearText = "alex";
        byte[] clearTextBytes = clearText.getBytes("UTF8");
        System.out.println("Texto en claro: " + clearText);

        cipher.init(Cipher.ENCRYPT_MODE, originalKey);
        byte[] cipherBytes = cipher.doFinal(clearTextBytes);
        String cipherText = new String(cipherBytes, "UTF8");
        System.out.println("Texto cifrado: " + cipherText);

        cipher.init(Cipher.DECRYPT_MODE, originalKey);
        byte[] decryptedBytes = cipher.doFinal(cipherBytes);
        String decryptedText = new String(decryptedBytes, "UTF8");
        System.out.println("Texto descifrado: " + decryptedText);

        if (clearText.equals(decryptedText)){
            System.out.println("HOLA");
        }
    }


}
