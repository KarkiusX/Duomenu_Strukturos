package edu.ktu.ds.lab2.cpu;

import edu.ktu.ds.lab2.utils.AvlSet;

public class CPU_Methods{
    public void AddToCurrentListParse(AvlSet data, String datatype)
    {
        CPU Cpu = new CPU();
        Cpu.parse(datatype);
        data.add(Cpu);
    }
}
