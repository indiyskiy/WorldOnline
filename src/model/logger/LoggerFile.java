package model.logger;

import model.constants.Component;
import model.constants.ServerConsts;

import java.io.*;

public class LoggerFile {
    private final String root;
    private final String date;
    private final String fileName = "Log";
    private boolean isOpened;
    private PrintWriter out;
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Global, LoggerFile.class);

    public LoggerFile(String root, String date) {
        this.root = root;
        this.date = date;
        isOpened = false;

    }

    public void open() {
        try {
            File newDir = new File(ServerConsts.home + ServerConsts.globalLogDir + date + "/" + root);
            if (!newDir.exists()) {
                newDir.mkdirs();
            }
            File file = new File(newDir, fileName + ".txt");
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
            out = new PrintWriter(new BufferedWriter(writer));
            isOpened = true;
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }

    public void printToFile(String data) {
        if (isOpened) {
            out.print(data + "\r\n");
            out.flush();
        }
    }

    public void close() {
        if (isOpened) {
            out.close();
        }
    }

    public int hashCode() {
        int hash = 0;
        hash += root.hashCode();
        hash = hash * 13 + date.hashCode();
        return hash;
    }

    public boolean equals(Object o) {
        if (o.getClass() != LoggerFile.class) {
            return false;
        }
        LoggerFile that = (LoggerFile) o;
        if (!that.root.equals(this.root)) {
            return false;
        }
        if (!that.date.equals(this.date)) {
            return false;
        }
        return true;
    }

    public String getSignature() {
        return root;
    }

    public String getDate() {
        return date;
    }
}
