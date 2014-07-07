package helper;

import model.constants.Component;
import model.constants.ServerConsts;
import model.logger.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Random;

public class FileHelper {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, FileHelper.class);

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

    public static void saveToFile(InputStream is, File file) throws IOException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            loggerFactory.error(e);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }


    public static void copyFile(File file, String fileName, String path) throws IOException {
        try {
            File outFolder = new File(path);
            if (!outFolder.exists()) {
                outFolder.mkdirs();
            }
            File out = new File(path + "/" + fileName);
            FileCopyUtils.copy(file, out);
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

}
