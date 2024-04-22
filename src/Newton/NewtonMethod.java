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
        for (int iter = 0; iter < MAX_ITERATIONS; iter++) {
            double[] f = function.value(x);
            double[][] jacobian = function.jacobian(x);
            double[][] inverseJacobian;
            try {
                inverseJacobian = Matrix.invertMatrix(jacobian);
            } catch (SingularMatrixException e) {
                // Если матрица выраженная, используется другой метод для аппроксимации обратной матрицы
                inverseJacobian = Approximation.approximateInverseJacobian(jacobian, 2);
            }
            double[] step = Vec.matrixVectorProduct(inverseJacobian, f);
            for (int i = 0; i < x.length; i++) {
                x[i] -= step[i];
            }
            if (Vec.vectorNorm(step) < TOLERANCE) {
                break; // Критерий сходимости
            }
        }
        return x;
    }

    // Пример использования
    public static void main(String[] args) {
        // Пример функции
        LinearEquationSystem.Function function = new LinearEquationSystem.Function() {
            public double[] value(double[] x) {
                return new double[]{
                        0.5 * Math.cos(x[1]) + 0.3 - x[0],
                        Math.sin(x[0] - 0.5) - 1.5 - x[1]
                };
            }

            public double[][] jacobian(double[] x) {
                return Matrix.calculateJacobian(this, x, 1e-6); // Метод calculateJacobian для вычисления якобиана
            }
        };

        // Начальное приближение
        double[] initialGuess = new double[] {1, 1};

        // Решение
        double[] solution = solve(function, initialGuess);
        System.out.println("Solution: " + Arrays.toString(solution));
    }
}

