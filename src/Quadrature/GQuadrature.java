package Quadrature;

import base.MathMeth;
import java.util.function.DoubleUnaryOperator;

public class GQuadrature {

    /**
     * Узлы для метода
     */
    private static final double[] nodes = {-0.9061798459, -0.5384693101, 0.0, 0.5384693101, 0.9061798459};

    /**
     * Метод для вычисления веса в узле.
     *
     * @param k индекс узла
     * @return вес в узле
     */
    private static double weight(int k) {
        double weight = 1.0;
        for (int i = 0; i < nodes.length; i++) {
            if (i != k) {
                weight *= (nodes[k] - nodes[i]);
            }
        }
        return weight;
    }

    /**
     * Метод для численного интегрирования функции на заданном интервале с использованием квадратоурных формул.
     *
     * @param f функция, которую требуется проинтегрировать
     * @param a начальная граница интервала интегрирования
     * @param b конечная граница интервала интегрирования
     * @return приближенное значение определенного интеграла функции на заданном интервале
     */
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double sum = 0.0;
        double scale = 0.5 * (b - a);
        double offset = 0.5 * (b + a);

        for (int i = 0; i < nodes.length; i++) {
            double node = scale * nodes[i] + offset;
            sum += f.applyAsDouble(node) / weight(i);
        }

        return scale * sum;
    }

    /**
     * Метод для вычисления погрешности численного интегрирования функции на заданном интервале.
     *
     * @param derivative производная функции, для которой вычисляется максимальное значение
     * @param a начальная граница интервала интегрирования
     * @param b конечная граница интервала интегрирования
     * @param n количество узлов интегрирования
     * @return приближенное значение погрешности численного интегрирования
     */
    public static double error(DoubleUnaryOperator derivative, double a, double b, int n) {
        // Вычисляем максимальное значение (n+1)-й производной
        double maxDerivative = Double.NEGATIVE_INFINITY;
        for (double x = a; x <= b; x += 0.001) { // Простой численный метод для нахождения максимума
            double currentDerivative = Math.abs(derivative.applyAsDouble(x));
            if (currentDerivative > maxDerivative) {
                maxDerivative = currentDerivative;
            }
        }

        // Вычисляется значение интеграла p(x) * |w(x)|
        double integral = 0.0;
        for (double node : nodes) {
            integral += Math.abs(weight(nodes.length - 1) * weight(nodes.length - 1));
        }

        // Вычисляется погрешность
        double error = maxDerivative / MathMeth.factorial(n + 1) * integral;
        return error;
    }

    public static void main(String[] args) {
        // Пример использования: вычисление определенного интеграла от x^2 на интервале [0, 1]
        DoubleUnaryOperator function = x -> x * x;
        double integral = integrate(function, -1, 1);
        System.out.println("Значение интеграла: " + integral);

        // Рассчет погрешности для n = 4
        DoubleUnaryOperator derivative = x -> 2; // Производная от x^2
        double a = -1;
        double b = 1;
        int n = 4;
        double error = error(derivative, a, b, n);
        System.out.println("Погрешность: " + error);
    }
}
