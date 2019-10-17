package edu.ktu.ds.lab1b.stasaitis;

public class CPU_Select {

    public CPU_Data Current_Data = new CPU_Data();

    public CPU_Data getCpuByBase(int min, int max)
    {
        CPU_Data data = new CPU_Data();
        for (CPU cpu: Current_Data
             ) {
            if(cpu.Get_Cpu_Base_Min() >= min && cpu.Get_Cpu_Base_Max() <= max)
            {
                data.add(cpu);
            }
        }
        return data;
    }
    public CPU_Data getCpuByPrice(boolean lessOr, double price)
    {
        CPU_Data data = new CPU_Data();
        for(CPU cpu : Current_Data)
        {
            if(lessOr ? cpu.Get_Cpu_Price() <= price : cpu.Get_Cpu_Price() >= price)
            {
                data.add(cpu);
            }
        }
        return data;
    }
}
