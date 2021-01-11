package main;

public class Interval {
    public final double a;
    public final double b;

    public Interval(double a, double b){
        this.a = a;
        this.b = b;
    }

    public String toString(){
        return  "(" + a + "," + b + ")";
    }

    public boolean isEmpty(){
        return a >= b;
    }
}
