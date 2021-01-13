package main;

public class ShowResults {
    public static String show(Matrix A, Matrix B, Matrix res, int N){
        String results = "";

        System.out.println("Macierz A:");
        if (N <= 7){
            for (int i = 0; i < N; i++){
                System.out.print("[");
                for (int j = 0; j < N; j++){
                    System.out.printf("%11.5f  ", A.getVal(i, j));
                }
                System.out.println("   ]");
            }
        }
        else {
            System.out.print("[");
            for (int i = 0; i < 5; i++){
                System.out.printf("%11.5f  ", A.getVal(0, i));
            }
            System.out.println(". . . ]");
            System.out.print("[");
            for (int i = 0; i < 5; i++){
                System.out.printf("%11.5f  ", A.getVal(1, i));
            }
            System.out.println(". . . ]");
            System.out.print("[");
            for (int i = 0; i < 5; i++){
                System.out.printf("%11.5f  ", A.getVal(2, i));
            }
            System.out.println(". . . ]");

            System.out.println("    .     ");
            System.out.println("    .     ");
            System.out.println("    .     ");

            System.out.print("[ . . . ");
            for (int i = 0; i < 5; i++){
                System.out.printf("%11.5f  ", A.getVal(N - 3, N - 5 + i));
            }
            System.out.println("]");
            System.out.print("[ . . . ");
            for (int i = 0; i < 5; i++){
                System.out.printf("%11.5f  ", A.getVal(N - 2, N - 5 + i));
            }
            System.out.println("]");
            System.out.print("[ . . . ");
            for (int i = 0; i < 5; i++){
                System.out.printf("%11.5f  ", A.getVal(N - 1, N - 5 + i));
            }
            System.out.println("]");
        }

        System.out.println("Macierz B:");
        System.out.print("[");
        for (int i = 0; i < N; i++){
            System.out.printf("%11.5f  ", B.getVal(i, 0));
        }
        System.out.println("   ]");

        System.out.println("Macierz rozwiązań:");
        System.out.print("[");
        for (int i = 0; i < N; i++){
            System.out.printf("%11.5f  ", res.getVal(i, 0));
        }
        System.out.println("   ]");

        System.out.println("Wykres wyświetlony jest w zewnętrznym oknie, wraz z funkcją otrzymaną w programie Wolfram Alpha");
        return results;
    }
}
