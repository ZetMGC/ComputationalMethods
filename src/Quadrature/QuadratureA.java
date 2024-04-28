package Quadrature;

import java.util.function.DoubleUnaryOperator;

public class QuadratureA {

    /**
     * Вычисляет приближенное значение определенного интеграла методом аппроксимации с использованием формулы суммы прямоугольников.
     *
     * @param n количество узлов для аппроксимации
     * @param f функция, которую необходимо проинтегрировать
     * @param x массив узлов интегрирования
     * @return приближенное значение определенного интеграла
     */
    public static double integrate(int n, DoubleUnaryOperator f, double[] x) {
        double result = 0.0;

        // Вычисляем значение интеграла
        for (int k = 0; k < n + 1; k++) {
            double x_k = x[k];
            double x_k1 = x[k + 1];
            result += f.applyAsDouble((x_k + x_k1) / 2.0) * (x_k1 - x_k);
        }

        return result;
    }

    /**
     * Вычисляет коэффициент C_k для заданного узла k в методе квадратур.
     *
     * @param a          нижний предел интегрирования
     * @param b          верхний предел интегрирования
     * @param n          количество узлов для аппроксимации
     * @param k          индекс текущего узла
     * @param x          массив узлов интегрирования
     * @param integrals  массив предварительно вычисленных значений интегралов для каждого узла
     * @return коэффициент C_k
     */
    private static double calculateC(double a, double b, int n, int k, double[] x, double[] integrals) {
        double product = 1.0;

        // Вычисляются произведение весов w(x)
        for (int i = 0; i <= n + 1; i++) {
            if (i != k) {
                product *= (x[k] - x[i]);
            }
        }

        // Вычисляем значение интеграла для C_k
        return integrals[k] / product;
    }

    /**
     * Метод для вычисления определенного интеграла методом квадратур.
     *
     * @param a нижний предел интегрирования
     * @param b верхний предел интегрирования
     * @param n количество узлов для аппроксимации
     * @param f функция, которую необходимо проинтегрировать
     * @return приближенное значение определенного интеграла
     */
    public static double QuadratureMethod(double a, double b, int n, DoubleUnaryOperator f) {
        double[] x = new double[n + 2];
        double h = (b - a) / (n + 1);
        for (int i = 0; i < x.length; i++) {
            x[i] = a + i * h;
        }

        // Вычисляются значения интегралов для каждого узла x_k
        double[] integrals = new double[n + 2];
        for (int i = 0; i < integrals.length; i++) {
            int finalI = i;
            integrals[i] = integrate(n, x_k -> 1.0 / (x_k - x[finalI]), x);
        }

        double integral = 0.0;
        // Вычисляется значение интеграла по формуле
        for (int k = 0; k < n + 1; k++) {
            double C_k = calculateC(a, b, n, k, x, integrals);
            integral += C_k * f.applyAsDouble(x[k]);
        }
        return integral;
    }

    public static double LagrangeIP(int n) {
        double sum = 0;


        return sum;
    }



    public static void main(String[] args) {
        double a = 0.0;
        double b = 1.0;
        int n = 4;

        // Функция f(x) = x^2
        DoubleUnaryOperator f = x -> x * x;

        System.out.println("Integral approximation: " + QuadratureMethod(a, b, n, f));
    }
}
