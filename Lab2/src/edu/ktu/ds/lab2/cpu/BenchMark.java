package edu.ktu.ds.lab2.cpu;


import edu.ktu.ds.lab2.demo.Car;
import edu.ktu.ds.lab2.demo.CarsGenerator;
import edu.ktu.ds.lab2.demo.SimpleBenchmark;
import edu.ktu.ds.lab2.demo.Timekeeper;
import edu.ktu.ds.lab2.gui.ValidationException;
import edu.ktu.ds.lab2.utils.*;
import edu.ktu.ds.lab2.utils.SortedSet;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;


public class BenchMark {

    public static final String FINISH_COMMAND = "                       ";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab2.gui.messages");

    private static final String[] BENCHMARK_NAMES = {"TreecontainsAll", "TreeContains", "HashContainAll", "HashContains"};
    private static final int[] COUNTS = {10000, 20000, 40000, 80000};

    private final Timekeeper timeKeeper;
    private final String[] errors;

    private final TreeSet<Integer> tree = new TreeSet<>();
    private final HashSet<Integer> hast = new HashSet<>();
    private final Collection<Integer> col = new ArrayList<>();

    /**
     * For console benchmark
     */
    public BenchMark() {
        timeKeeper = new Timekeeper(COUNTS);
        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
    }

    /**
     * For Gui benchmark
     *
     * @param resultsLogger
     * @param semaphore
     */
    public BenchMark(BlockingQueue<String> resultsLogger, Semaphore semaphore) {
        semaphore.release();
        timeKeeper = new Timekeeper(COUNTS, resultsLogger, semaphore);
        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
    }

    public static void main(String[] args) {
        executeTest();
    }
    public void GenerateNumbers(int k)
    {
        for(int i =0; i < k; i++)
        {
            tree.add(i);
            hast.add(i);
        }
    }
    public void NumberCollection(int k)
    {
        k = (int)Math.floor(k / 2);
        for(int i =0; i < k; i++)
        {
            Random num = new Random();
            int number = num.nextInt(k + 1000);
            col.add(number);
        }
    }
    public static void executeTest() {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        Ks.out("Greitaveikos tyrimas:\n");
        new BenchMark().startBenchmark();
    }

    public void startBenchmark() {
        try {
            benchmark();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void benchmark() throws InterruptedException {
        try {
            boolean keep = false;
            for (int k : COUNTS) {
                Random numb = new Random();
                int numbertofind = numb.nextInt(k / 2);
                tree.clear();
                hast.clear();
                col.clear();
                GenerateNumbers(k);
                NumberCollection(k);
                timeKeeper.startAfterPause();
                timeKeeper.start();
                keep = tree.containsAll(col);
                timeKeeper.finish(BENCHMARK_NAMES[0]);
                tree.contains(numbertofind);
                timeKeeper.finish(BENCHMARK_NAMES[1]);
                hast.containsAll(col);
                timeKeeper.finish(BENCHMARK_NAMES[2]);
                hast.contains(numbertofind);
                timeKeeper.finish(BENCHMARK_NAMES[3]);
                timeKeeper.seriesFinish();
            }
            timeKeeper.logResult(FINISH_COMMAND);
        } catch (ValidationException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                timeKeeper.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                timeKeeper.logResult(MESSAGES.getString("allSetIsPrinted"));
            } else {
                timeKeeper.logResult(e.getMessage());
            }
        }
    }
}
