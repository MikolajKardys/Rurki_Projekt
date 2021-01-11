package main;
public class LineFunction {
    public final double a;
    public final double b;
    public LineFunction (double a, double b){
        this.a = a;
        this.b = b;
    }

    public double getValAt(double x){
        return a * x + b;
    }

    public LineFunction derivative(){
        return new LineFunction(0, a);
    }
}
