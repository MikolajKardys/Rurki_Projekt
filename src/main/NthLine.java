package main;

import java.util.ArrayList;

public class NthLine {
    private final double mid;
    private final Interval nonZero;
    private final LineFunction leftLine;
    private final LineFunction rightLine;

    private NthLine(NthLine derivativeTarget){
        this.mid = derivativeTarget.mid;
        this.nonZero = derivativeTarget.nonZero;

        if (derivativeTarget.leftLine != null){
            this.leftLine = derivativeTarget.leftLine.derivative();
        }
        else {
            this.leftLine = null;
        }

        if (derivativeTarget.rightLine != null){
            this.rightLine = derivativeTarget.rightLine.derivative();
        }
        else {
            this.rightLine = null;
        }
    }

    private NthLine(double begin, double end, int ind, int n) {
        double length = (end - begin) / (n - 1);
        double left = ind * length + 1;
        double right = ind * length - 1;

        this.mid = ind * length;

        if (ind > 0) {
            left = (ind - 1) * length;
            leftLine = new LineFunction(1 / length, (-left) / length);
        }
        else {
            leftLine = null;
        }

        if (ind < n - 1) {
            right = (ind + 1) * length;
            rightLine = new LineFunction((-1) / length, right / length);
        }
        else {
            rightLine = null;
        }
        this.nonZero = new Interval(Math.min(left, mid), Math.max(right, mid));
    }

    public NthLine getDerivative(){
        return new NthLine(this);
    }

    public static ArrayList<NthLine> getLines(double begin, double end, int N){
        ArrayList<NthLine> list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++){
            list.add(new NthLine(begin, end, i, N + 1));
        }

        return list;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double getValAt(double x) {
        if (leftLine != null && x > this.nonZero.a && x <= mid) {
            return leftLine.getValAt(x);
        }
        if (rightLine != null && x >= mid && x < this.nonZero.b) {
            return rightLine.getValAt(x);
        }
        return 0;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Interval getNonZeroLeft (){
        if (leftLine != null){
            return new Interval(nonZero.a, mid);
        }
        return new Interval(0, 0);
    }
    public Interval getNonZeroRight (){
        if (leftLine != null) {
            return new Interval(mid, nonZero.b);
        }
        return new Interval(0, 0);
    }

    public Interval nonZeroWith (NthLine other){
        return new Interval(Math.max(this.nonZero.a, other.nonZero.a), Math.min(this.nonZero.b, other.nonZero.b));
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String toString(){

        String str = "";
        if (leftLine != null)
            str += leftLine.a + " " + leftLine.b + "   ";
        else
            str += 0 + " " + 0 + "   ";

        if (rightLine != null)
            str += rightLine.a + " " + rightLine.b + "   ";
        else
            str += 0 + " " + 0 + "   ";
        return str;

    }
}
