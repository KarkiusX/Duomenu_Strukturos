package edu.ktu.ds.lab3.cpu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUNIT {

    static CPU_Generator generator = new CPU_Generator();
    static CPU[] CPU_List;
    static CPU cpu = new CPU();
    @BeforeAll
    public static void Create()
    {
        Random rand = new Random();
        CPU_List = generator.generateShuffleCpuAndKeys(50, 10);
        cpu = CPU_List[rand.nextInt(CPU_List.length - 1)];
    }
    @DisplayName("Checking Year")
   @Test
   public void Check_Year()
   {
       assertTrue(cpu.validateYear(2019, 2020), String.format("Error year is too low or too high %d", CPU_List[0].Get_Cpu_Release_Year()));
   }
   @DisplayName("Checking Price")
   @Test
    public void Check_Price()
   {
       assertTrue(cpu.validatePrice(2500, 3000), String.format("Error price is out of bound %.2f", CPU_List[0].Get_Cpu_Price()));
   }
}
