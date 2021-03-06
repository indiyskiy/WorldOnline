package model.scheduler;

import model.constants.Component;
import model.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

public class Scheduler implements Runnable {
    //all time in hours
    private static Scheduler scheduler = new Scheduler();
    private static LoggerFactory logger;
    private ArrayList<Executable> executableList = new ArrayList<>();
    private int maxPeriod = 1;
    private static final long oneHour = 1000L * 60L * 60L;

    private Scheduler() {
        logger = new LoggerFactory(Component.Global, Scheduler.class);
        addTasksToList();
        countPeriod();
        Thread thread = new Thread(this);
        thread.start();
    }

    private void countPeriod() {
        HashSet<Integer> times = new HashSet<>();
        for (Executable executable : executableList) {
            times.add(executable.getPeriod());
        }
        for (int time : times) {
            maxPeriod *= time;
        }
    }

    private void addTasksToList() {
        executableList.add(new WeatherTask());
        executableList.add(new ExchangeRatesTask());
        executableList.add(new EventsCleaner());
    }

    public static Scheduler getScheduler() {
        return scheduler;
    }

    public static void start() {
        getScheduler();
    }

    @Override
    public void run() {
        doTheEndlessCycle();
    }

    private void doTheEndlessCycle() {
        try {
            int periodTimer = 0;
            do {
                try {
                    for (Executable executable : executableList) {
                        if (periodTimer % executable.getPeriod() == 0) {
                            executable.execute();
                        }
                    }
                    periodTimer++;
                    if (periodTimer >= maxPeriod) {
                        periodTimer = 0;
                    }
                    System.out.println(String.valueOf(periodTimer));
                    Thread.sleep(oneHour);
                } catch (Exception e) {
                    logger.error("Attention! Error at endless cycle!");
                    logger.error(e);
                }
            } while (true);
        } catch (Exception e) {
            logger.error(e);
        }
        logger.error("The Endless Cycle was stopped");
    }
}
