package Smoothing;

import java.util.Random;
import base.GraphHandler;
import base.SmoothingParameters;
import base.Reader;

public class DataSmoothingGauss {
    /**
     * Метод для вычисления сглаженных значений на основе гауссовского ядра.
     */
    public static double[] smoothData(double[] x, double[] y, int k, int m) {
        double[] smoothedY = new double[y.length];

        for (int i = 0; i < x.length; i++) {
            double sumY = 0;
            double sumX = 0;
            double sumXY = 0;
            double sumXX = 0;

            int start = Math.max(0, i - k);
            int end = Math.min(x.length, i + k + 1);

            for (int j = start; j < end; j++) {
                double weight = gaussianWeight(j - i, k);
                sumY += y[j] * weight;
                sumX += x[j] * weight;
                sumXY += x[j] * y[j] * weight;
                sumXX += x[j] * x[j] * weight;
            }

            double denominator = sumXX - (sumX * sumX) / (end - start);
            if (denominator != 0) {
                double slope = (sumXY - sumX * sumY / (end - start)) / denominator;
                double intercept = (sumY - slope * sumX) / (end - start);
                smoothedY[i] = slope * x[i] + intercept;
            } else {
                smoothedY[i] = y[i];
            }
        }

        return smoothedY;
    }

    private static double gaussianWeight(int distance, int k) {
        // Функция веса на основе гауссовского ядра
        double sigma = k / 3.0; // Стандартное отклонение (выбрано эмпирически)
        return Math.exp(-0.5 * distance * distance / (sigma * sigma));
    }

    public static double[] addGaussianNoise(double[] data, double mean, double standardDeviation) {
        Random random = new Random();
        double[] noisyData = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            double noise = mean + standardDeviation * random.nextGaussian();
            noisyData[i] = data[i] + noise;
        }

        return noisyData;
    }

    public static void main(String[] args) {
        // Пример данных
        SmoothingParameters smoothingParameters = Reader.readDataSmoothing("src/Smoothing/input1.txt");

        // Параметры сглаживания
        int k = 2; // Количество точек с каждой стороны
        int m = 10; // Степень многочлена

        double[] noisyData = addGaussianNoise(smoothingParameters.y, 0.0, 0.9);

        // Сглаживание данных
        double[] smoothedY = smoothData(smoothingParameters.x, noisyData, k, m);

        // Вывод сглаженных данных
        for (double v : smoothedY) {
            System.out.println(v);
        }

        GraphHandler.displayGraphs(smoothingParameters.x, smoothingParameters.y, smoothedY, noisyData);
    }
}

