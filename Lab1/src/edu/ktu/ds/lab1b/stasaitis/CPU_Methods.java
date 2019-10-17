package edu.ktu.ds.lab1b.stasaitis;

public class CPU_Methods{
    public void AddToCurrentListParse(CPU_Data data, String datatype)
    {
        CPU Cpu = new CPU();
        Cpu.parse(datatype);
        data.add(Cpu);
    }
}
