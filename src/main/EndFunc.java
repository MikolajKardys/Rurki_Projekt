package main;

import java.util.List;

public class EndFunc {
    Matrix M;
    List<NthLine> eList;

    public EndFunc(Matrix M, List<NthLine> eList){
        this.M = M;
        this.eList = eList;
    }

    public double getEndVal(double x){
        double result = 0;
        for (int i = 0; i < eList.size(); i++){
            result += eList.get(i).getValAt(x) * M.getVal(i, 0);
        }
        return result;
    }
}
