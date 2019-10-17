package edu.ktu.ds.lab1b.stasaitis;

import java.util.Locale;

public class CPU_Main {
    public static void main(String... args) {
        Locale.setDefault(new Locale("LT"));
        CPU Cpu1 = new CPU("AMD RYZEN 9", 2019, 12, 3.8,4.6, 499.99);
        CPU Cpu2 = new CPU("Intel Core i9-9900K", 2018, 8, 3.6, 5.0, 484.99);
        CPU Cpu3 = new CPU("Intel Core i7-9700K", 2019, 8, 3.3, 4.9, 370.99);

        CPU CPU4 = new CPU();
        CPU CPU5 = new CPU();
        CPU CPU6 = new CPU();
        CPU4.parse("Intel Core i7-8700K,2017,6,3.7,4.7,354.99");
        CPU5.parse("Intel Core i7-8700,2017,6,3.2,4.6,304.99");
        CPU6.parse("Intel Core i9-9900K,2018,8,3.6,5.0,484.99");

        CPU_Data data = new CPU_Data(false);
        CPU_Data data1 = new CPU_Data(true);

        System.out.println("Pradinis");
        data.println();
        System.out.println("------");
        data1.println();
        data.retainAll(data1);
        System.out.println("Galutinis");
        data.println();


    }
}
