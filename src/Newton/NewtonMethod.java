package Newton;

import org.apache.commons.math.linear.SingularMatrixException;
import java.util.Arrays;
import Smoothing.Approximation;
import base.Matrix;
import base.LinearEquationSystem;
import base.Vec;

public class NewtonMethod {
    /**
     * Точность решения
     * */
    private static final double TOLERANCE = 1e-9;
    /**
     * Максимум итераций
     * */
    private static final int MAX_ITERATIONS = 100;

    /**
     * Решает систему уравнений методом Ньютона с использованием якобиана функции и начального приближения.
     *
     * @param function      функция, представляющая систему уравнений и ее якобиан
     * @param initialGuess  начальное приближение для решения
     * @return решение системы уравнений
     */
    public static double[] solve(LinearEquationSystem.Function function, double[] initialGuess) {
        double[] x = initialGuess.clone();
        int iter = 0;
        while (iter < MAX_ITERATIONS) {
            double[] f = function.value(x);
            double[][] jacobian = function.jacobian(x);
            double[][] inverseJacobian;
            try {
                inverseJacobian = Matrix.invertMatrix(jacobian);
            } catch (SingularMatrixException e) {
                // Если матрица сингулярная, используем другой метод для аппроксимации обратной матрицы
                inverseJacobian = Approximation.approximateInverseJacobian(jacobian, 2);
            }
            double[] step = Vec.matrixVectorProduct(inverseJacobian, f);
            for (int i = 0; i < x.length; i++) {
                x[i] -= step[i];
            }
            double norm = Vec.vectorNorm(step);
            if (norm < TOLERANCE) {
                break; // Критерий сходимости
            }
            iter++;
        }
        return x;
    }

    // Пример использования
    public static void main(String[] args) {
        // Пример функции
        LinearEquationSystem.Function function = new LinearEquationSystem.Function() {
            public double[] value(double[] x) {
                return new double[] {
                        x[0] * x[0] + x[1] * x[1] - 1,
                        Math.exp(x[0]) + x[1] - Math.E
                };
            }

            public double[][] jacobian(double[] x) {
                return new double[][] {
                        {2 * x[0], 2 * x[1]},
                        {Math.exp(x[0]), 1}
                };
            }
        };

        // Начальное приближение
        double[] initialGuess = new double[] {1, 1};

        // Решение
        double[] solution = solve(function, initialGuess);
        System.out.println("Solution: " + Arrays.toString(solution));
    }
}

