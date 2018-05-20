import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class JavaSESecurityApiExample {

    public static void main(String[] args) throws Exception {
        exampleDigest();
        exampleSecureRandom();
        exampleKeyPairGenerator();

    }

    public static void exampleSecureRandom() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            System.out.print(random.nextInt() + "\n");
        }
    }

    public static void exampleDigest() throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] digest = sha.digest("Hello".getBytes());
        System.out.println(new String(digest));
    }

    public static void exampleKeyPairGenerator() throws NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, random);
        KeyPair keyPair = keyGen.genKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encoded = cipher.doFinal("hello".getBytes());

        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        System.out.println(new String(cipher.doFinal(encoded)));

    }
}