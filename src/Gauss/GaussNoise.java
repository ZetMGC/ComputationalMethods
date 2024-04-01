package Gauss;

import java.util.Random;

public class GaussNoise {
    /**
     * Добавляет гауссовский шум к исходным данным.
     *
     * @param data массив исходных данных.
     * @param mean среднее значение шума.
     * @param standardDeviation стандартное отклонение шума.
     * @return массив, содержащий исходные данные с добавленным гауссовским шумом.
     */
    public static double[] addGaussianNoise(double[] data, double mean, double standardDeviation) {
        Random random = new Random();
        double[] noisyData = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            double noise = mean + standardDeviation * random.nextGaussian();
            noisyData[i] = data[i] + noise;
        }

        return noisyData;
    }
}
