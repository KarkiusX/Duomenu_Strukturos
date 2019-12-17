package Test;

import org.omg.CORBA.TIMEOUT;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotThreadProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Measurement(iterations = 1)
@Warmup(iterations = 1)
@Fork(value = 1, warmups = 0)
public class Benchmark {
    static SparseArray<Integer> sparse = new SparseArray<Integer>();
    static HashMap<Integer, Integer> test = new HashMap<Integer, Integer>();
    static List<Integer> t = new ArrayList<Integer>();
    static int[] index = {30, 40, 20, 5, 4};
    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }
    @org.openjdk.jmh.annotations.State(Scope.Benchmark)
    public static class State
    {
        @Param({"10", "100", "1000", "10000", "10000"})
        int size;
        @Setup
        public void setup() {
        Random random = new Random();
        for(int i = 0; i < size; i++)
        {
            int k = random.nextInt(size);
            int v = random.nextInt(50000);
            sparse.put(k, v);
            test.put(k , v);
            t.add(k);
        }
        }
    }
    @org.openjdk.jmh.annotations.Benchmark
    public static void TestSparse(State state, Blackhole hole)
    {
        for(int i =0; i < sparse.size(); i++)
        {
            int k = t.get(i);
            hole.consume(sparse.get(k));
        }
    }
    @org.openjdk.jmh.annotations.Benchmark
    public static void TestHashMap(State state, Blackhole hole)
    {
        for(int i =0; i < test.size(); i++)
        {
            int k = t.get(i);
            hole.consume(test.get(k));
        }
    }
    @org.openjdk.jmh.annotations.Benchmark
    public static void TestHashMapRemove(State state, Blackhole hole)
    {
        for(int i =0; i < test.size(); i++)
        {
            int k = t.get(i);
            hole.consume(test.remove(k));
        }
    }
    @org.openjdk.jmh.annotations.Benchmark
    public static void TestSparseRemove(State state, Blackhole hole)
    {
        for(int i =0; i < test.size(); i++)
        {
            int k = t.get(i);
            sparse.remove(k);
        }
    }
}
