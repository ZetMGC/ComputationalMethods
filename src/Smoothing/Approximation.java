package Smoothing;

import Gauss.GaussianElimination;

public class Approximation {
    /**
     * Предполагается, что массивы x и y имеют одинаковую длину,
     * где x содержит значения по оси X, а y - соответствующие значения по оси Y.
     * Функция находит ближайшие к targetX значения x[i] и x[i+1],
     * и затем интерполирует соответствующие значения y[i] и y[i+1].
     * @param x Массив значений по оси X.
     * @param y Массив соответствующих значений по оси Y.
     * @param targetX Точка по оси X, для которой нужно выполнить интерполяцию.
     */
    public static double interpolate(double[] x, double[] y, double targetX) {
        for (int i = 0; i < x.length - 1; i++) {
            if (x[i] <= targetX && targetX <= x[i + 1]) {
                double proportion = (targetX - x[i]) / (x[i + 1] - x[i]);
                return y[i] + (y[i + 1] - y[i]) * proportion;
            }
        }
        return Double.NaN; // Возвращаем NaN в случае, если точка не может быть интерполирована
    }


    /**
     * Реализует аппроксимацию полиномом методом наименьших квадратов (МНК).
     * @param x Массив значений по оси X.
     * @param y Массив соответствующих значений по оси Y.
     * @param m Степень полинома, который мы хотим аппроксимировать.
     * @return Полученные коэффициенты полинома в виде массива
     */
    public static double[] leastSquaresFit(double[] x, double[] y, int m) {
        int n = x.length;  // колличество точек данных
        double[] coefficients;  //  массив, в котором будут храниться коэффициенты полинома

        // Создаем матрицу системы уравнений
        double[][] A = new double[m + 1][m + 1];
        double[] B = new double[m + 1];

        // Заполняем матрицу A и вектор B
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n; k++) {
                    A[i][j] += Math.pow(x[k], i + j);
                }
            }
            for (int k = 0; k < n; k++) {
                B[i] += y[k] * Math.pow(x[k], i);
            }
        }

        // Решаем систему уравнений
        coefficients = GaussianElimination.solve(A, B);

        return coefficients;
    }
}
