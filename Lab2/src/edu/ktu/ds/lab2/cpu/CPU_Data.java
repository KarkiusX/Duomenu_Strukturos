package edu.ktu.ds.lab2.cpu;

import edu.ktu.ds.lab2.demo.Car;
import edu.ktu.ds.lab2.utils.AvlSet;
import edu.ktu.ds.lab2.utils.ParsableAvlSet;
import edu.ktu.ds.lab2.utils.ParsableBstSet;
import edu.ktu.ds.lab2.utils.ParsableSortedSet;

import java.util.Locale;


public class CPU_Data {
   public ParsableSortedSet<CPU> CPUSA = new ParsableAvlSet<>(CPU::new);
   public ParsableSortedSet<CPU> CPUSB = new ParsableBstSet<>(CPU::new);

   public void CPU_Data(boolean Parse, boolean BST)
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
         if(BST)
         {
            CPUSB.add(CPU4);
            CPUSB.add(CPU5);
            CPUSB.add(CPU6);
            CPUSB.add(CPU7);
         }
         else
         {
            CPUSA.add(CPU4);
            CPUSA.add(CPU5);
            CPUSA.add(CPU6);
            CPUSA.add(CPU7);
         }
      }
      else
      {
         CPU Cpu1 = new CPU("AMD RYZEN 9", 2019, 12, 3.8,4.6, 499.99);
         CPU Cpu2 = new CPU("Intel Core i9-9900K", 2018, 8, 3.6, 5.0, 484.99);
         CPU Cpu8= new CPU("Intel Core i7-9700K", 2019, 8, 3.3, 4.9, 370.99);
         CPU Cpu3= new CPU("Intel Core i5-5800", 2015, 4, 2.5, 3.5, 250.99);

         if(BST)
         {
            CPUSB.add(Cpu1);
            CPUSB.add(Cpu2);
            CPUSB.add(Cpu3);
            CPUSB.add(Cpu8);
         }
         else {
            CPUSA.add(Cpu1);
            CPUSA.add(Cpu2);
            CPUSA.add(Cpu3);
            CPUSA.add(Cpu8);
         }
      }
   }
}
