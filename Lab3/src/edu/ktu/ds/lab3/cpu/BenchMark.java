package edu.ktu.ds.lab3.cpu;


import edu.ktu.ds.lab3.demo.Timekeeper;;
import edu.ktu.ds.lab3.gui.ValidationException;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.ParsableHashMap;
import edu.ktu.ds.lab3.utils.ParsableMap;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;


public class BenchMark {

    public static final String FINISH_COMMAND = "                       ";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab3.gui.messages");

    private final String[] BENCHMARK_NAMES = {"HashMapOa_Put", "HashMap_Put", "HashMapOa_Remove", "HashMap_Remove"};
    private static final int[] COUNTS = {1000, 2000, 6000, 8000};

    private final Timekeeper timeKeeper;


    private final int start = 0;
    private final int end = 1000;

    private final HashMapOa<String, String> oa = new HashMapOa<>();
    private final HashMap<String, String> hash = new HashMap<>();
    private final List<String> array = new ArrayList<>();

    /**
     * For console benchmark
     */
    public BenchMark() {
        timeKeeper = new Timekeeper(COUNTS);
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
    }

    public static void main(String[] args) {
        executeTest();
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
                array.clear();
                oa.clear();
                hash.clear();
                Read_File(System.getProperty("user.dir") + "\\Duom\\zodynas.txt", array, start, k);
                Random rand = new Random();
                timeKeeper.startAfterPause();
                timeKeeper.start();
                for(int i = 0; i < array.size(); i++)
                {
                    oa.put(array.get(i), array.get(i));
                }
                timeKeeper.finish(BENCHMARK_NAMES[0]);
                for(int i = 0; i < array.size(); i++)
                {
                    hash.put(array.get(i), array.get(i));
                }
                timeKeeper.finish(BENCHMARK_NAMES[1]);
                for(int i = 0; i < array.size() / 2; i++)
                {
                    int remove_index = rand.nextInt(array.size());
                    oa.remove(array.get(remove_index));
                    array.remove(array.get(remove_index));
                }
                timeKeeper.finish(BENCHMARK_NAMES[2]);
                for(int i = 0; i < array.size() / 2; i++)
                {
                    int remove_index = rand.nextInt(array.size());
                    hash.remove(array.get(remove_index));
                    array.remove(array.get(remove_index));
                }
                timeKeeper.finish(BENCHMARK_NAMES[3]);
                timeKeeper.seriesFinish();
            }
            timeKeeper.logResult(FINISH_COMMAND);
        } catch (ValidationException e) {
            timeKeeper.logResult(e.getMessage());
        }
    }
    private void Read_File(String filePath, List<String> array, int start, int end)
    {
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        try (BufferedReader fReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while((line = fReader.readLine()) != null)
            {
                if(index >= start && index <= end) {
                    array.add(line);
                    index++;
                }
            }
        } catch (FileNotFoundException e) {
            Ks.ern("Tinkamas duomenų failas nerastas: " + e.getLocalizedMessage());
        } catch (IOException | UncheckedIOException e) {
            Ks.ern("Failo skaitymo klaida: " + e.getLocalizedMessage());
        }
    }
}
