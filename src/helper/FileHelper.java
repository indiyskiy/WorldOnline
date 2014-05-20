package helper;

import model.constants.ServerConsts;

import java.io.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class FileHelper {
    public static String readFileAsString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    public static File saveToFile(String text, String filePath, String fileName) throws FileNotFoundException {
        File newDir = new File(filePath.substring(0, filePath.length() - 1));
        if (!newDir.exists()) {
            newDir.mkdirs();
        }
        File file = new File(filePath + fileName);
        PrintWriter writer = new PrintWriter(file);
        writer.print(text);
        writer.flush();
        writer.close();
        return file;
    }


    public static File saveToTempFile(String text) throws FileNotFoundException {
        Random random = new Random(System.currentTimeMillis());
        int rnd = random.nextInt(10000);
        return saveToFile(text, ServerConsts.tempFileStore, String.valueOf(rnd));
    }

}
