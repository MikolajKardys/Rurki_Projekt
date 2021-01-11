package main;

import java.util.function.Function;


//Dla dwóch punktów:
// w_i = 1; x_i = +/- 1/sqrt(3)
//Dostajemy już funkcje tylko na przedziałach na  których są dodatnie

public class Integral {

    public static double getIntegralOn(Function <Double, Double>  f, Interval interval){
        if (interval.isEmpty()) return 0;

        double result = 0;
        double a = interval.a;
        double b = interval.b;

        double x_1 = ((b - a) / 2) * (1 / Math.sqrt(3)) + ((a + b) / 2);
        result += ((b - a) / 2) * f.apply(x_1);

        double x_2 = -((b - a) / 2) * (1 / Math.sqrt(3)) + ((a + b) / 2);
        result += ((b - a) / 2) * f.apply(x_2);

        return result;
    }
}
