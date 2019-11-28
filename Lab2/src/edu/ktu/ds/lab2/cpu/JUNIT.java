package edu.ktu.ds.lab2.cpu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUNIT {

    static CPU_Generator generator = new CPU_Generator();
    static CPU[] CPU_List;
    static CPU cpu = new CPU();
    @BeforeAll
    public static void Create()
    {
        CPU_List = generator.generateShuffle(30,10, 0.8);
        cpu = generator.takeCpu();
    }
    @DisplayName("Checking Year")
   @Test
   public void Check_Year()
   {
       assertTrue(cpu.validateYear(2019, 2020), String.format("Error year is too low or too high %d", cpu.Get_Cpu_Release_Year()));
   }
   @DisplayName("Checking Price")
   @Test
    public void Check_Price()
   {
       assertTrue(cpu.validatePrice(1999, 2000), String.format("Error price is out of bound %.2f", cpu.Get_Cpu_Price()));
   }
}
