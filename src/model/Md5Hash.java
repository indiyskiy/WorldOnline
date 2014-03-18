package model;

import model.constants.Component;
import model.logger.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Илья on 14.03.14.
 */
public class Md5Hash {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, Md5Hash.class);

    public static String getMd5Hash(File file) {
        String checksum = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while ((numOfBytesRead = fis.read(buffer)) > 0) {
                md.update(buffer, 0, numOfBytesRead);
            }
            byte[] hash = md.digest();
            checksum = new BigInteger(1, hash).toString(16);
        } catch (IOException e) {
            loggerFactory.error(e);
        } catch (NoSuchAlgorithmException e) {
            loggerFactory.error(e);
        }
        return checksum;
    }

    public static String getMd5Hash(String string) {
        String checksum = null;
        try {
            InputStream fis = new ByteArrayInputStream(string.getBytes());
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while ((numOfBytesRead = fis.read(buffer)) > 0) {
                md.update(buffer, 0, numOfBytesRead);
            }
            byte[] hash = md.digest();
            checksum = new BigInteger(1, hash).toString(16);
        } catch (IOException e) {
            loggerFactory.error(e);
        } catch (NoSuchAlgorithmException e) {
            loggerFactory.error(e);
        }
        return checksum;
    }
}
