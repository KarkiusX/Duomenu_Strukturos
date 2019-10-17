package edu.ktu.ds.lab1b.stasaitis;

import edu.ktu.ds.lab1b.stasaitis.util.Ks;
import edu.ktu.ds.lab1b.stasaitis.util.LinkedList;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class BenchMark {

    static int[] counts = {10_000, 20_000, 40_000, 80_000};

    ArrayList<Integer> test = new ArrayList<>();
    LinkedList<Integer> test1 = new LinkedList<>();

    double test2;
    double test3;
    int test4;
    int test5;

    void AddListElements(int count)
    {
        for(int i =0; i < count; i++)
        {
            test.add(i * 100);
            test1.add(i * 100);
        }
    }
    void generateAndExecute(int skaicius) {
        long t0 = System.nanoTime();
        AddListElements(skaicius);
        int intfind = skaicius * 100;
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        for(int i =0; i < skaicius; i++) {
            test2 = Math.pow(i, 0.5);
        }
        long t3 = System.nanoTime();
        for(int i =0; i < skaicius; i++) {
            test3 = Math.sqrt(i);
        }
        long t4 = System.nanoTime();
        test4 = test.lastIndexOf(intfind);
        long t5 = System.nanoTime();
        test5 = test.lastIndexOf(intfind);
        long t6 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", skaicius,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9, (t5 - t4) / 1e9, (t6 - t5) / 1e9);

       // System.out.println(test2 + test3+ test4 + test5);

    }

     void execute() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - POW");
        Ks.oun("4 - Sqrt");
        Ks.oun("5 - ArrayList IndexOf");
        Ks.oun("6 - LinkedList IndexOf");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5, 6);
        for (int n : counts) {
            generateAndExecute(n);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("LT"));
        new BenchMark().execute();
    }
}
