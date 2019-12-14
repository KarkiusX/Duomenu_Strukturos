package edu.ktu.ds.lab3.cpu;

import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.Parsable;

import java.util.*;

public class CPU implements Parsable<CPU> {

    private String CPU_NAME;
    private int CPU_RELEASE_YEAR;
    private int CPU_CORES;
    private double CPU_BASE_MIN;
    private double CPU_BASE_MAX;
    private double CPU_PRICE;

    private static String CPU_REG_PREF = "ID";
    private static int CPU_NUMBER = 100;
    private static String CPU_ID;


    public CPU()
    {
        CPU_ID = CPU_REG_PREF + (CPU_NUMBER++);
    }
    public CPU(String data)
    {

    }
    public CPU(Builder builder) {
        CPU_NAME = builder.Name;
        CPU_RELEASE_YEAR = builder.Year;
        CPU_CORES = builder.Core;
        CPU_BASE_MIN = builder.Base_Min;
        CPU_BASE_MAX = builder.Base_Max;
        CPU_PRICE = builder.Price;
       // validate();
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
    public boolean validateYear(int minRelease, int maxRelease) {
        if (CPU_RELEASE_YEAR < minRelease || CPU_RELEASE_YEAR > maxRelease) {
            return false;
        }
        return true;
    }
    public boolean validatePrice(int minPrice, int maxPrice)
    {
        if (CPU_PRICE < minPrice || CPU_PRICE > maxPrice) {
            return false;
        }
        return true;
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
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.CPU_NAME);
        hash = 29 * hash + this.CPU_RELEASE_YEAR;
        hash = 29 * hash + this.CPU_CORES;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.CPU_BASE_MIN));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.CPU_BASE_MAX));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.CPU_PRICE) ^ (Double.doubleToLongBits(this.CPU_PRICE) >>> 32));
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CPU other = (CPU) obj;
        if (this.CPU_RELEASE_YEAR != other.CPU_RELEASE_YEAR) {
            return false;
        }
        if (this.CPU_CORES != other.CPU_CORES) {
            return false;
        }
        if (Double.doubleToLongBits(this.CPU_PRICE) != Double.doubleToLongBits(other.CPU_PRICE)) {
            return false;
        }
        if (Double.doubleToLongBits(this.CPU_BASE_MAX) != Double.doubleToLongBits(other.CPU_BASE_MAX)) {
            return false;
        }
        if (Double.doubleToLongBits(this.CPU_BASE_MIN) != Double.doubleToLongBits(other.CPU_BASE_MIN)) {
            return false;
        }
        if (!Objects.equals(this.CPU_NAME, other.CPU_NAME)) {
            return false;
        }
        return true;
    }
    /*
    @Override
    public int compareTo(CPU car) {
        return Get_Cpu_Id().compareTo(car.Get_Cpu_Id());
    }
    */
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
    public String Get_Cpu_Name(){return CPU_NAME;}
    public int Get_Cpu_Release_Year() {return CPU_RELEASE_YEAR;}
    public int Get_Cpu_Cores(){return CPU_CORES;}
    public double Get_Cpu_Base_Min(){return CPU_BASE_MIN;}
    public double Get_Cpu_Price(){return CPU_PRICE;}
    public double Get_Cpu_Base_Max(){return CPU_BASE_MAX;}
    public String Get_Cpu_Id() { return CPU_ID;};
    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[][] MODELS = { // galimų automobilių markių ir jų modelių masyvas
                {"Intel", "Quad-Core", "I3", "I5", "I7"},
                {"AMD", "Ahtlon", "Razen"}
        };
        private String Name = "";
        private int Year = -1;
        private int Core = -1;
        private double Base_Min = -1.0;
        private double Base_Max = -1.0;
        private double Price = -1.0;

        public CPU build() {
            return new CPU(this);
        }

        public CPU buildRandom() {
            int ma = RANDOM.nextInt(MODELS.length);        // markės indeksas  0..
            int mo = 1 + RANDOM.nextInt(MODELS.length - 1);
            int serie =  100 + RANDOM.nextInt(9000);
            return new CPU(MODELS[ma][0] + " " + MODELS[ma][mo] + '-' + serie,
                    1990 + RANDOM.nextInt(25),// metai tarp 1990 ir 2009
                    1 + RANDOM.nextInt(8),// rida tarp 6000 ir 228000
                    2.5 + RANDOM.nextDouble() * 4.5,
                    4.5 + RANDOM.nextDouble() * 6.0,
                    200 + RANDOM.nextDouble() * 2000);// kaina tarp 800 ir 88800
        }

        public Builder Year(int year) {
            this.Year = year;
            return this;
        }

        public Builder model(String model) {
            this.Name = model;
            return this;
        }
        public Builder Core(int core)
        {
            this.Core = core;
            return this;
        }
        public Builder Base_Min(double base)
        {
            this.Base_Min = base;
            return this;
        }
        public Builder Base_Max(double base)
        {
            this.Base_Max = base;
            return this;
        }
        public Builder price(double price) {
            this.Price = price;
            return this;
        }
    }

}
