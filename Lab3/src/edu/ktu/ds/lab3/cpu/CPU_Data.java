package edu.ktu.ds.lab3.cpu;

import edu.ktu.ds.lab3.demo.Car;
import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.ParsableHashMap;
import edu.ktu.ds.lab3.utils.ParsableMap;

import java.util.Locale;


public class CPU_Data {
   ParsableMap<String, CPU> cpuMap= new ParsableHashMap<>(String::new, CPU::new, HashType.DIVISION);
   ParsableMap<String , CPU> cpuMap1= new ParsableHashMap<>(String::new, CPU::new, HashType.DIVISION);
   HashMapOa<String, CPU> test = new HashMapOa<>();
   String CPU_Keys[] ={
           "CPU512", "CPU105","CPU130","CPU230"
   };

   public void CPU_Data(boolean Parse)
   {
      if(Parse)
      {
         CPU CPU4 = new CPU();
         CPU CPU5 = new CPU();
         CPU CPU6 = new CPU();
         CPU CPU7 = new CPU();
         CPU4.parse("Intel Core i7-8700,2017,6,3.2,4.6,304.99");
         CPU5.parse("Intel Core i7-9700K,2019,8,3.3,4.9,370.99");
         CPU6.parse("Intel Core i9-9900K,2018,8,3.6,5.0,484.99");
         CPU7.parse("AMD RYZEN 9,2019,12,3.8,4.6,499.99");
         CPU[] cpus ={
                 CPU4, CPU5, CPU6, CPU7
         };
         for(int i =0; i < CPU_Keys.length; i++) {
            test.put(CPU_Keys[i], cpus[i]);
         }
      }
      else
      {
         CPU Cpu1 = new CPU("AMD RYZEN 9", 2019, 12, 3.8,4.6, 499.99);
         CPU Cpu2 = new CPU("Intel Core i9-9900K", 2018, 8, 3.6, 5.0, 484.99);
         CPU Cpu8= new CPU("Intel Core i7-9700K", 2019, 8, 3.3, 4.9, 370.99);
         CPU Cpu3= new CPU("Intel Core i5-5800", 2015, 4, 2.5, 3.5, 250.99);
         CPU[] cpus ={
                 Cpu1, Cpu2, Cpu8,Cpu3
         };
         for(int i =0; i < CPU_Keys.length; i++) {
            test.put(CPU_Keys[i], cpus[i]);
         }
      }
   }
}
