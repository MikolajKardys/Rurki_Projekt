package main;
import java.util.List;

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
        int N = 1000;
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
