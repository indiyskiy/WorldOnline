package model.textparser;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class StringCrypter {


    private static final Random random = new Random(System.currentTimeMillis());
    private Cipher ecipher;
    private Cipher dcipher;

    /**
     * Упрощенный конструктор. Создает StringCrypter с ключом
     * DESSecretKey (алгоритм шифрования DES) со значением key.
     * Ключ key должен иметь длину 8 байт
     */
    public StringCrypter(byte[] key) {
        try {
            updateSecretKey(new DESSecretKey(key));
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public StringCrypter(SecretKey key) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {
        updateSecretKey(key);
    }


    private void updateSecretKey(SecretKey key) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {
        ecipher = Cipher.getInstance(key.getAlgorithm());
        dcipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return Base64.encodeBase64String(enc);
        } catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public String decrypt(String str) {
        try {
            byte[] dec = Base64.decodeBase64(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(StringCrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte[] getRandomKey() {
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        for (byte aByte : bytes) {
            System.out.print(aByte + " ");
        }
        return bytes;
    }
}