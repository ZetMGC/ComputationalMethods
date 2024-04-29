package Quadrature;

import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;
import Gauss.GaussQuadrature;

public class QuadratureG {

    /**
     * Определяет количество узлов интегрирования на каждом подотрезке.
     * */
    static final int n = 5;

    /**
     * Определяет количество подотрезков, на которые разбивается интервал интегрирования
     * */
    static final int s = 4;

    /**
     * Метод для численного вычисления определенного интеграла методом Гауссовой квадратуры.
     *
     * @param a нижний предел интегрирования
     * @param b верхний предел интегрирования
     * @param f функция, которую необходимо проинтегрировать
     * @return приближенное значение определенного интеграла
     */
    static double integral(double a, double b, DoubleUnaryOperator f) {
        double[] nodes = new double[n * s];
        double[] weights = new double[n * s];
        GaussQuadrature.NodesCalculation(weights, nodes, a, b, n, s);

        double sum = 0.0;
        for (int i = 0; i < n * s; i++)
            sum += weights[i] * f.applyAsDouble(nodes[i]);
        return sum;
    }

    public static void main(String[] args) {
        DoubleUnaryOperator f = x -> Math.sin(x);

        Scanner scanner = new Scanner(System.in);

        System.out.print("a = ");
        double a = scanner.nextDouble();
        System.out.print("b = ");
        double b = scanner.nextDouble();

        System.out.println("Ответ = " + integral(a, b, f));
    }
}

