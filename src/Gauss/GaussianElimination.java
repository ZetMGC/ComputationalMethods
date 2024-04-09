package Gauss;

import base.Matrix;
import base.Reader;
import base.LinearEquationSystem;

import java.io.IOException;

public class GaussianElimination {

    // Метод Гаусса для решения системы линейных уравнений
    public static double[] solve(double[][] A, double[] B) {
        int n = B.length;

        // Прямой ход метода Гаусса
        for (int pivot = 0; pivot < n - 1; pivot++) {
            for (int row = pivot + 1; row < n; row++) {
                double factor = A[row][pivot] / A[pivot][pivot];
                for (int col = pivot; col < n; col++) {
                    A[row][col] -= factor * A[pivot][col];
                }
                B[row] -= factor * B[pivot];
            }
        }

        // Обратный ход метода Гаусса
        double[] solution = new double[n];
        for (int row = n - 1; row >= 0; row--) {
            double sum = 0.0;
            for (int col = row + 1; col < n; col++) {
                sum += A[row][col] * solution[col];
            }
            solution[row] = (B[row] - sum) / A[row][row];
        }

        return solution;
    }

    /**
     * Example
     * */
    public static void main(String[] args) throws IOException {
        LinearEquationSystem les = Reader.readEquations("src/Gauss/equations.txt");

        double[][] A = les.getMatrixA();
        double[] B = les.getVectorB();

        Matrix.print(A, B);

        // Решение системы линейных уравнений
        les.setSolution(solve(A, B));

        // Вывод решения
        les.printSolution();
    }
}
