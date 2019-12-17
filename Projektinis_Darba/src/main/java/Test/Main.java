package Test;

public class Main {
    public static void main(String[] args)
    {
        SparseArray sparseArray = new SparseArray();
        sparseArray.put(10, "horse");
        sparseArray.put(3, "cow");
        sparseArray.put(1, "camel");
        sparseArray.put(99, "sheep");
        sparseArray.put(30, "goat");
        sparseArray.put(17, "pig");

        sparseArray.Iterate();
    }
}
