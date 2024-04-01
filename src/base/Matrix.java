package base;
import exceptions.EmptyMatrixException;

public class Matrix {
    public static void print(double[] solution) throws EmptyMatrixException {
        if (solution.length == 0 || solution == null)
            throw new EmptyMatrixException();

        System.out.println("\nРешение:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i+1) + " = " + solution[i]);
        }
    }

    public static void print(double[][] matrix) throws EmptyMatrixException {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new EmptyMatrixException();
        }
        System.out.println("\nМатрица");
        for(double[] row : matrix) {
            for (double var : row) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
    }


    public static void print(double[][] matrix, double[] vals) {
        System.out.println("\nСистама ЛУ");
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("= " + vals[i] + "\n");
        }
    }
}
