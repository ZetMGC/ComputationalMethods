package base;
import exceptions.EmptyMatrixException;
import org.apache.commons.math.linear.SingularMatrixException;

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

    // Вычисление обратной матрицы
    public static double[][] invertMatrix(double[][] matrix) throws SingularMatrixException {
        int n = matrix.length;
        double[][] identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            identity[i][i] = 1.0;
        }
        double[][] inverse = matrix.clone();
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            double max = inverse[i][i];
            int maxRow = i;
            for (int j = i + 1; j < n; j++) {
                if (inverse[j][i] > max) {
                    max = inverse[j][i];
                    maxRow = j;
                }
            }
            if (maxRow != i) {
                double[] temp = inverse[i];
                inverse[i] = inverse[maxRow];
                inverse[maxRow] = temp;
                temp = identity[i];
                identity[i] = identity[maxRow];
                identity[maxRow] = temp;
            }
            double pivot = inverse[i][i];
            if (pivot == 0.0) {
                throw new SingularMatrixException();
            }
            for (int j = 0; j < n; j++) {
                inverse[i][j] /= pivot;
                identity[i][j] /= pivot;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = inverse[k][i];
                    for (int j = 0; j < n; j++) {
                        inverse[k][j] -= factor * inverse[i][j];
                        identity[k][j] -= factor * identity[i][j];
                    }
                }
            }
        }

        return identity;
    }
}
