package edu.ktu.ds.lab1b.stasaitis;

import edu.ktu.ds.lab1b.stasaitis.util.Ks;
import edu.ktu.ds.lab1b.stasaitis.util.Parsable;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CPU implements Parsable<CPU> {

    private String CPU_NAME;
    private int CPU_RELEASE_YEAR;
    private int CPU_CORES;
    private double CPU_BASE_MIN;
    private double CPU_BASE_MAX;
    private double CPU_PRICE;


    public CPU()
    {

    }
    public CPU(String cpu_name, int cpu_release_date, int cpu_core, double cpu_base_min, double cpu_base_max, double cpu_price)
    {
        CPU_NAME = cpu_name;
        CPU_RELEASE_YEAR = cpu_release_date;
        CPU_CORES = cpu_core;
        CPU_BASE_MIN = cpu_base_min;
        CPU_PRICE = cpu_price;
        CPU_BASE_MAX = cpu_base_max;
    }

    @Override
    public void parse(String dataString) {
        try{
            Scanner p = new Scanner(dataString).useDelimiter(",");
            CPU_NAME = p.next();
            CPU_RELEASE_YEAR = p.nextInt();
            CPU_CORES = p.nextInt();
            CPU_BASE_MIN = Double.parseDouble(p.next());
            CPU_BASE_MAX = Double.parseDouble(p.next());
            CPU_PRICE = Double.parseDouble(p.next());
        }
        catch (NoSuchElementException e)
        {
            Ks.ern("Not enough data about CPU" + dataString);
        }
    }
    @Override
    public String toString()
    {
        return String.format("%s %d %d %.2f %.2f %.2f", CPU_NAME, CPU_RELEASE_YEAR, CPU_CORES, CPU_BASE_MIN,CPU_BASE_MAX, CPU_PRICE);
    }
    /*
    public final static Comparator byPrice = (obj1, obj2) -> {
        double price1 = ((CPU) obj1).Get_Cpu_Price();
        double price2 = ((CPU) obj2).Get_Cpu_Price();

        double new_price = Math.sqrt(price1);
        double new_price1 = Math.sqrt(price2);
        if (new_price < new_price1) {
            return -1;
        }
        if (new_price > new_price1) {
            return 1;
        }
        return 0;
    };
    */
    @Override
    public int compareTo(CPU o) {
        double Price = o.CPU_PRICE;

        if(this.CPU_PRICE > Price)
        {
            return 1;
        }
        else if(this.CPU_PRICE < Price)
        {
            return -1;
        }
        return 0;
    }
    public String Get_Cpu_Name(){return CPU_NAME;}
    public int Get_Cpu_Release_Year() {return CPU_RELEASE_YEAR;}
    public int Get_Cpu_Cores(){return CPU_CORES;}
    public double Get_Cpu_Base_Min(){return CPU_BASE_MIN;}
    public double Get_Cpu_Price(){return CPU_PRICE;}
    public double Get_Cpu_Base_Max(){return CPU_BASE_MAX;}

}
