package edu.ktu.ds.lab1b.stasaitis;

import edu.ktu.ds.lab1b.stasaitis.util.ParsableList;


public class CPU_Data extends ParsableList<CPU> {

   public CPU_Data()
   {

   }
   public CPU_Data(String file)
   {
      load(file);
   }
   public CPU_Data(boolean Parse)
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
         add(CPU4);
         add(CPU5);
         add(CPU6);
         add(CPU7);
      }
      else
      {
         CPU Cpu1 = new CPU("AMD RYZEN 9", 2019, 12, 3.8,4.6, 499.99);
         CPU Cpu2 = new CPU("Intel Core i9-9900K", 2018, 8, 3.6, 5.0, 484.99);
         CPU Cpu8= new CPU("Intel Core i7-9700K", 2019, 8, 3.3, 4.9, 370.99);
         CPU Cpu3= new CPU("Intel Core i5-5800", 2015, 4, 2.5, 3.5, 250.99);

         add(Cpu1);
         add(Cpu2);
         add(Cpu3);
         add(Cpu8);
      }
   }
}
