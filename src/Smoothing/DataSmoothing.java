package Smoothing;

import base.GraphHandler;
import base.Reader;
import base.SmoothingParameters;
import base.Vec;
import Gauss.GaussNoise;
import java.util.Arrays;

public class DataSmoothing {
    /**
     * Выполняет сглаживание данных с использованием {@linkplain Approximation#leastSquaresFit метода наименьших квадратов (МНК)} для аппроксимации полиномом.
     * @param x Массив значений по оси X.
     * @param y Массив соответствующих значений по оси Y.
     * @param k Параметр, который определяет ширину окна для сглаживания.
     * @param m Степень полинома, который используется для аппроксимации.
     */
    public static double[] smoothData(double[] x, double[] y, int k, int m) {
        double[] smoothedY = new double[y.length]; // Хранит сглаженные значния

        for (int i = 0; i < x.length; i++) {
            int start = Math.max(0, i - k);
            int end = Math.min(x.length, i + k + 1);

            if (i < k || i >= x.length - k) {
                // Если индекс i находится вблизи начала или конца массива,
                // то сглаживание производится с использованием крайних точек
                double[] xSubset = Arrays.copyOfRange(x, start, end);
                double[] ySubset = Arrays.copyOfRange(y, start, end);
                smoothedY[i] = Approximation.interpolate(xSubset, ySubset, x[i]);
            } else {
                // Выбираем данные для аппроксимации
                double[] xSubset = Arrays.copyOfRange(x, i - k, i + k + 1);
                double[] ySubset = Arrays.copyOfRange(y, i - k, i + k + 1);

                // Вычисляем коэффициенты полинома методом наименьших квадратов
                double[] coefficients = Approximation.leastSquaresFit(xSubset, ySubset, m);

                // Вычисляем значение полинома в точке x[i]
                smoothedY[i] = Vec.evaluatePolynomial(coefficients, x[i]);
            }
        }

        return smoothedY;
    }

    public static void main(String[] args) {
        SmoothingParameters smoothingParameters = Reader.readDataSmoothing("src/Smoothing/input1.txt");

        // Параметры сглаживания
        int k = 8; // Количество точек с каждой стороны
        int m = 2; // Степень многочлена

        double[] noisyData = GaussNoise.addGaussianNoise(smoothingParameters.y, 0.0, 2);

        // Сглаживание данных
        double[] smoothedY = smoothData(smoothingParameters.x, noisyData, k, m);

        // Вывод сглаженных данных
        for (double v : smoothedY) {
            System.out.println(v);
        }

        GraphHandler.displayGraphs(smoothingParameters.x, smoothingParameters.y, smoothedY, noisyData);
    }
}
