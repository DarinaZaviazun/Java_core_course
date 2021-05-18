package main.part6.part3;

public class Parking {
    private int[] park;

    public Parking(int capacity) {
        park = new int[capacity];
    }

    public boolean arrive(int k) {
        if (k > park.length || k < 0) throw new IllegalArgumentException();
        for (int a = k; a < park.length; a++) {
            if (park[a] == 0) {
                park[a] = 1;
                return true;
            }
        }
        for (int a = 0; a < k; a++){
            if (park[a] == 0) {
                park[a] = 1;
                return true;
            }
        }
        return false;
    }

    public boolean depart(int k) {
        if (k > park.length || k < 0) throw new IllegalArgumentException();
        if (park[k] == 1){
            park[k] = 0;
            return true;
        }
        return false;
    }

    public void print() {
        for (int a = 0; a < park.length; a++)
            System.out.print(park[a]);
    }
}
