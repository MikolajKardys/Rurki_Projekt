package main;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.List;
import java.util.Scanner;

public class World {
    private static double B(NthLine u, NthLine v, int same) {
        double result = 0;

        result -= u.getValAt(2) * v.getValAt(2);

        if (same != 0){
            result += Integral.getIntegralOn(x -> u.getDerivative().getValAt(x)*v.getDerivative().getValAt(x), u.nonZeroWith(v));
            result -= Integral.getIntegralOn(x -> u.getValAt(x)*v.getValAt(x), u.nonZeroWith(v));
        }
        else {
            result += Integral.getIntegralOn(x -> u.getDerivative().getValAt(x)*v.getDerivative().getValAt(x), u.getNonZeroRight());
            result += Integral.getIntegralOn(x -> u.getDerivative().getValAt(x)*v.getDerivative().getValAt(x), u.getNonZeroLeft());

            result -= Integral.getIntegralOn(x -> u.getValAt(x)*v.getValAt(x), u.getNonZeroRight());
            result -= Integral.getIntegralOn(x -> u.getValAt(x)*v.getValAt(x), u.getNonZeroLeft());

        }

        return result;
    }

    private static double L(NthLine v) {
        double result = 0;

        result += Integral.getIntegralOn(x -> v.getValAt(x) * Math.sin(x), v.getNonZeroLeft());
        result += Integral.getIntegralOn(x -> v.getValAt(x) * Math.sin(x), v.getNonZeroRight());

        return result;
    }

    public static void main (String [] args){
        System.out.println("Input your N:");
        Scanner myObj = new Scanner(System.in);
        int N = -1;
        while (N < 1) {
            try{
                String input = myObj.nextLine();
                N = Integer.parseInt(input);
            }
            catch (NumberFormatException e){
                N = -1;
            }
            if (N < 1){
                System.out.println("Please supply a positive integer");
            }
        }

        List<NthLine> eList = NthLine.getLines(0, 2, N);
        double [][] A = new double[N+1][N+1];
        for (int i = 1; i < eList.size(); i++){
            for (int j = 1; j < eList.size(); j++) {
                A[i][j] = B(eList.get(i), eList.get(j), i-j);
            }
        }
        A[0][0] = 1;

        double [][] B = new double[N+1][1];
        for (int i = 1; i < eList.size(); i++){
            B[i][0] = L(eList.get(i));
        }
        B[0][0] = 0;

        Matrix matA = new Matrix(A);
        Matrix matB = new Matrix(B);
        Matrix res = matA.solve(matB);



        EndFunc f = new EndFunc(res, eList);
        double [] xData = new double [201];
        double [] yApprox = new double [201];
        double [] yActual = new double [201];
        double constant = (2*Math.sin(2) + Math.cos(2))/(Math.cos(2) - Math.sin(2));
        int ind = 0;
        for (double i = 0.00; i < 2.0; i += 0.01, ind++){
            xData[ind] = i;
            yApprox[ind] = f.getEndVal(i);

            yActual[ind] = (i * Math.cos(i) + Math.sin(i) * constant) / 2;
        }
        xData[200] = 2.0;
        yApprox[200] = f.getEndVal(2.0);
        yActual[200] = (2 * Math.cos(2) + Math.sin(2) * constant) / 2;

        XYChart results = new XYChartBuilder().width(1200).height(700).title("Results").xAxisTitle("X").yAxisTitle("Y").build();
        results.getStyler().setMarkerSize(2);
        results.addSeries("Approximation", xData, yApprox);
        results.addSeries("Actual", xData, yActual);

        new SwingWrapper(results).displayChart();
    }
}
