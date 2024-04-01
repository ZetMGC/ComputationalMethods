package ConjugateGradient;

import java.io.IOException;
import java.util.Arrays;
import base.LinearEquationSystem;
import base.Reader;
import base.SymmetricMatrix;
import base.Vec;

public class ConjugateGradientSolver {

    /**
     * Метод решает СЛАУ методом сопряженных градиентов
     * Если сходимость достигнута, выводится количество итераций
     * и возвращается вектор решения {@code x}. Если максимальное число
     * итераций достигнуто без сходимости, выводится сообщение и
     * возвращается текущее приближение вектора решения {@code x}.
     * <p/>
     * @param A Матрица коэффициентов системы
     * @param b Вектор правой части системы
     * @param x0 начальное приближение
     * @param tol допустимая абсолютная погрешность
     * @param maxIterations максимальное число итераций
     * @return x Вектор решений системы
     */
    public static double[] conjugateGradient(double[][] A, double[] b, double[] x0, double tol, int maxIterations) {
        double[] x = Arrays.copyOf(x0, b.length);
        double[] r = Arrays.copyOf(b, b.length); // начальная невязка
        double[] p = Arrays.copyOf(r, b.length); // текущее направление поиска
        double[] Ap = new double[b.length];

        double rsold = Vec.dotProduct(r, r);  // квадрат нормы вектора невязки

        for (int i = 0; i < maxIterations; i++) {

            Ap = Vec.matrixVectorProduct(A, p);
            double alpha = rsold / Vec.dotProduct(p, Ap);  // Определяется размер шага
            Vec.vectorScalarProduct(p, alpha);  // p *= alpha
            Vec.vectorAddition(x, p);           // x += p
            Vec.vectorScalarProduct(Ap, alpha);
            Vec.vectorSubtraction(r, Ap);       // обновление вектора невязки

            double rsnew = Vec.dotProduct(r, r);  // квадрат нормы вектора невязки на текущей итерации
            if (Math.sqrt(rsnew) < tol) {
                System.out.println();
                System.out.println("Сходимость достигнута после " + (i+1) + " итераций.");
                return x;
            }

            // обновление направления поиска
            double beta = rsnew / rsold;
            Vec.vectorScalarProduct(p, beta);
            Vec.vectorAddition(p, r);
            rsold = rsnew;
        }

        System.out.println("Достигнуто максимальное число итераций.");
        return x;
    }

    public static void main(String[] args) throws IOException {
        LinearEquationSystem les = Reader.readEquations("src/ConjugateGradient/input1.txt");
        les.print();

        double [][] A = SymmetricMatrix.makeSymmetric(les.getMatrixA());
        double [] B = les.getVectorB();
        double [] x0 = {0, 0, 0};
        double tol = 1e-6;

        les.setSolution(conjugateGradient(A, B, x0, tol, 1000));
        les.checkSolution(tol);
        les.printSolution();
    }
}
