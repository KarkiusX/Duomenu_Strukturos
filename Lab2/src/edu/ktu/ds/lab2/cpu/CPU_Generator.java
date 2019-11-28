package edu.ktu.ds.lab2.cpu;

import edu.ktu.ds.lab2.gui.ValidationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CPU_Generator {
    private int startIndex = 0, lastIndex = 0;
    private boolean isStart = true;

    private CPU[] cars;

    public CPU[] generateShuffle(int setSize,
                                 double shuffleCoef) throws ValidationException {

        return generateShuffle(setSize, setSize, shuffleCoef);
    }

    /**
     * @param setSize
     * @param setTake
     * @param shuffleCoef
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws ValidationException
     */
    public CPU[] generateShuffle(int setSize,
                                 int setTake,
                                 double shuffleCoef) throws ValidationException {

        CPU[] cars = IntStream.range(0, setSize)
                .mapToObj(i -> new CPU.Builder().buildRandom())
                .toArray(CPU[]::new);
        return shuffle(cars, setTake, shuffleCoef);
    }

    public CPU takeCpu() throws ValidationException {
        if (lastIndex < startIndex) {
            throw new ValidationException(String.valueOf(lastIndex - startIndex), 4);
        }
        // Vieną kartą Automobilis imamas iš masyvo pradžios, kitą kartą - iš galo.
        isStart = !isStart;
        return cars[isStart ? startIndex++ : lastIndex--];
    }

    private CPU[] shuffle(CPU[] cpuss, int amountToReturn, double shuffleCoef) throws ValidationException {
        if (cpuss == null) {
            throw new IllegalArgumentException("Automobilių nėra (null)");
        }
        if (amountToReturn <= 0) {
            throw new ValidationException(String.valueOf(amountToReturn), 1);
        }
        if (cpuss.length < amountToReturn) {
            throw new ValidationException(cpuss.length + " >= " + amountToReturn, 2);
        }
        if ((shuffleCoef < 0) || (shuffleCoef > 1)) {
            throw new ValidationException(String.valueOf(shuffleCoef), 3);
        }

        int amountToLeave = cpuss.length - amountToReturn;
        int startIndex = (int) (amountToLeave * shuffleCoef / 2);

        CPU[] takeToReturn = Arrays.copyOfRange(cpuss, startIndex, startIndex + amountToReturn);
        CPU[] takeToLeave = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(cpuss, 0, startIndex)),
                        Arrays.stream(Arrays.copyOfRange(cpuss, startIndex + amountToReturn, cpuss.length)))
                .toArray(CPU[]::new);

        Collections.shuffle(Arrays.asList(takeToReturn)
                .subList(0, (int) (takeToReturn.length * shuffleCoef)));
        Collections.shuffle(Arrays.asList(takeToLeave)
                .subList(0, (int) (takeToLeave.length * shuffleCoef)));

        System.out.println(takeToReturn.length);
        this.startIndex = 0;
        this.lastIndex = takeToLeave.length - 1;
        this.cars = takeToLeave;
        return takeToReturn;
    }
}
