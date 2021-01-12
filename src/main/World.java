package main;
import org.knowm.xchart.*;

import java.util.List;

public class World {
    private static double B(NthLine u, NthLine v, int same) {
        double result = 0;

        result -= u.getValAt(0) * v.getValAt(0);

        if (same != 0){
            result += Integral.getIntegralOn(x -> u.getDerivative().getValAt(x)*v.getDerivative().getValAt(x), u.nonZeroWith(v));
        }
        else {
            result += Integral.getIntegralOn(x -> u.getDerivative().getValAt(x)*v.getDerivative().getValAt(x), u.getNonZeroRight());
            result += Integral.getIntegralOn(x -> u.getDerivative().getValAt(x)*v.getDerivative().getValAt(x), u.getNonZeroLeft());
        }

        return result;
    }

    private static double L(NthLine v) {
        double result = 0;

        result -= 20 * v.getValAt(0);

        return result;
    }

    public static void main (String [] args){
        int N = 1000;
        List<NthLine> eList = NthLine.getLines(0, 2, N);

        double [][] A = new double[N+1][N+1];
        for (int i = 0; i < eList.size() - 1; i++){
            for (int j = 0; j < eList.size(); j++) {
                A[i][j] = B(eList.get(i), eList.get(j), i-j);
            }
        }
        A[N][N] = 1;

        double [][] B = new double[N+1][1];
        for (int i = 0; i < eList.size() - 1; i++){
            B[i][0] = L(eList.get(i));
        }
        B[N][0] = 0;

        Matrix matA = new Matrix(A);
        Matrix matB = new Matrix(B);
        Matrix res = matA.solve(matB);

        EndFunc f = new EndFunc(res, eList);
        double [] xData = new double [201];
        double [] yData = new double [201];
        int ind = 0;
        for (double i = 0.00; i < 2.0; i += 0.01, ind++){
            xData[ind] = i;
            yData[ind] = f.getEndVal(i);
        }
        xData[200] = 2.0;
        yData[200] = f.getEndVal(2.0);

        XYChart results = new XYChartBuilder().width(600).height(500).title("Results").xAxisTitle("X").yAxisTitle("Y").build();
        results.getStyler().setMarkerSize(2);
        XYSeries approx = results.addSeries("Approximation", xData, yData);
        new SwingWrapper(results).displayChart();
    }
}
