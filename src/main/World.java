package main;
import java.util.Arrays;
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
        int N = 1;
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
        for (double i = 0.00; i < 2.0; i = i + 0.01){
            String ans = Double.toString(f.getEndVal(i));
            ans = ans.replace(".", ",");
            System.out.println(ans);
        }
        String ans = Double.toString(f.getEndVal(2.0));
        ans = ans.replace(".", ",");
        System.out.println(ans);
    }
}
