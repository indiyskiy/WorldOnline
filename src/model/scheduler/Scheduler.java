package model.scheduler;

import model.constants.Component;
import model.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

public class Scheduler implements Runnable {
    //all time in seconds
    private static Scheduler scheduler = new Scheduler();
    private static LoggerFactory logger;
    private ArrayList<Executable> executableList = new ArrayList<>();
    private int maxPeriod = 1;

    private Scheduler() {
        logger = new LoggerFactory(Component.Global, Scheduler.class);
        logger.debug("scheduler start");
        addTasksToList();
        countPeriod();
        Thread thread = new Thread(this);
        thread.start();
        logger.debug("scheduler end");
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
                Thread.sleep(1000 * 60);
            } while (true);
        } catch (Exception e) {
            logger.error("The Endless Cycle was stopped");
            logger.error(e);
        }
    }
}
