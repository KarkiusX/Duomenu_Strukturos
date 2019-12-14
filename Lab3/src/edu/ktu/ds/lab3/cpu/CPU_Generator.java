package edu.ktu.ds.lab3.cpu;

import edu.ktu.ds.lab3.gui.ValidationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CPU_Generator {
    private int startIndex = 0, lastIndex = 0;
    private boolean isStart = true;

    private int currentCpiIndex;

    private CPU[] cars;

    private String[] keys;

    private static final String CODE = "CP";
    private static int Nr = 200;

    public static String[] generateShuffleKeys(int size) {
        String[] keys = IntStream.range(0, size)
                .mapToObj(i -> CODE + (Nr++))
                .toArray(String[]::new);
        Collections.shuffle(Arrays.asList(keys));
        return keys;
    }
    public CPU[] generateShuffleCpuAndKeys(int setSize,
                                           int setTakeSize) throws ValidationException {
        if (setTakeSize > setSize) {
            setTakeSize = setSize;
        }
        cars = shuffle(setSize);
        keys = generateShuffleKeys(setSize);
        this.currentCpiIndex = setTakeSize;
        return Arrays.copyOf(cars, setTakeSize);
    }

    private CPU[] shuffle(int size) throws ValidationException {
        CPU[] cars = IntStream.range(0, size)
                .mapToObj(i -> new CPU.Builder().buildRandom())
                .toArray(CPU[]::new);
        Collections.shuffle(Arrays.asList(cars));

        return cars;
    }
    public CPU getCpu() {
        if (cars == null) {
            throw new ValidationException("carsNotGenerated");
        }
        if (currentCpiIndex < cars.length) {
            return cars[currentCpiIndex++];
        } else {
            throw new ValidationException("allSetStoredToMap");
        }
    }

    public String getCpuId() {
        if (keys == null) {
            throw new ValidationException("carsIdsNotGenerated");
        }
        if (currentCpiIndex < keys.length) {
            return keys[currentCpiIndex++];
        } else {
            throw new ValidationException("allKeysStoredToMap");
        }
    }
}
