package model.logger;

import model.constants.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 25.12.13
 * Time: 13:00
 * To change this template use File | Settings | File Templates.
 */
public class Logger implements Runnable {
    private static Logger instance = new Logger();
    private static LoggerFactory loggerFactory=new LoggerFactory(Component.Global,Logger.class);
    private final Object lock;
    private LinkedList<LoggerMessage> loggerMessageList;
    private HashMap<String, LoggerFile> openedFiles;

    private Logger() {
        openedFiles = new HashMap<String, LoggerFile>();
        lock = new Object();
        loggerMessageList = new LinkedList<LoggerMessage>();
        Thread thread = new Thread(this);
        thread.start();
    }

    public static Logger getInstance() {
        return instance;
    }


    @Override
    public void run() {
        try {
            synchronized (lock) {
                do {
                    if (loggerMessageList.size() != 0) {
                        LoggerMessage loggerMessage = loggerMessageList.getFirst();
                        printLoggerMessage(loggerMessage);
                        loggerMessageList.removeFirst();
                    }
                    if (loggerMessageList.size() == 0) {
                        lock.wait();
                    }
                } while (true);
            }
        } catch (InterruptedException e) {
            loggerFactory.error(e);
        } finally {
            closeFiles();
        }
    }

    private void closeFiles() {
        for (LoggerFile loggerFile : openedFiles.values()) {
            loggerFile.close();
        }
    }

    private void printLoggerMessage(LoggerMessage loggerMessage) {
//        loggerMessage.printMyself();
        LoggerFile loggerFile = new LoggerFile(loggerMessage.getComponent().toString(), loggerMessage.getDate());
        if (openedFiles.containsKey(loggerFile.getSignature())) {
            LoggerFile loggerFileTest = openedFiles.get(loggerFile.getSignature());
            if (!loggerFile.getDate().equals(loggerFileTest.getDate())) {
                loggerFileTest.open();
                loggerFile.close();
                openedFiles.remove(loggerFile.getSignature());
                openedFiles.put(loggerFileTest.getSignature(), loggerFileTest);
                loggerFile = loggerFileTest;
            } else {
                loggerFile = loggerFileTest;
            }
        } else {
            loggerFile.open();
            openedFiles.put(loggerFile.getSignature(), loggerFile);
        }
        loggerMessage.logMyself(loggerFile);
//        loggerFile.printToFile(loggerMessage.getMessage());
    }

    public void addMessage(String text, LogLevel logLevel, Component component, Class callerClass){
        LoggerMessage message=new LoggerMessage(text,logLevel,component,callerClass);
        addMessage(message);
    }

    public void addMessage(LoggerMessage message) {
        synchronized (lock) {
            loggerMessageList.add(message);
            lock.notifyAll();
        }
    }
}
