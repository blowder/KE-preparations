import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class JavaSESecurityApiExample {

    public static void main(String[] args) throws Exception {
        exampleSecureRandom();
        exampleDigest();
        exampleSignature();
        exampleAsymmetricCipher();
        exampleSymmetricCipher();
    }

    public static void exampleSecureRandom() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        System.out.println("exampleSecureRandom() number is " + random.nextInt());
    }

    public static void exampleDigest() throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] digest = sha.digest("Hello".getBytes());
        System.out.println("exampleDigest() digest is " + new String(digest));
    }

    public static void exampleSignature() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KeyPair keyPair = generateKeyPair();
        Signature signatureService = Signature.getInstance("SHA256withRSA");
        signatureService.initSign(keyPair.getPrivate());
        signatureService.update("Hello".getBytes());
        byte[] signature = signatureService.sign();
        System.out.println("exampleSignature() signature is " + new String(signature));
        // now validate data
        signatureService = Signature.getInstance("SHA256withRSA");
        signatureService.initVerify(keyPair.getPublic());
        signatureService.update("Hello".getBytes());
        if (true != signatureService.verify(signature))
            throw new IllegalStateException("exampleSignature() signature is not valid");
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512, random);
        return keyGen.genKeyPair();
    }

    public static Key generateKey() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56, random);
        return keyGenerator.generateKey();
    }

    public static void exampleAsymmetricCipher() throws NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException {
        KeyPair keyPair = generateKeyPair();

        Cipher cipher = Cipher.getInstance(keyPair.getPublic().getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encoded = cipher.doFinal("hello".getBytes());

        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        System.out.println("exampleAsymmetricCipher() data is " + new String(cipher.doFinal(encoded)));
    }

    public static void exampleSymmetricCipher() throws NoSuchPaddingException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException {
        Key key = generateKey();

        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encoded = cipher.doFinal("hello".getBytes());

        cipher.init(Cipher.DECRYPT_MODE, key);
        System.out.println("exampleSymmetricCipher() data is " + new String(cipher.doFinal(encoded)));
    }
}