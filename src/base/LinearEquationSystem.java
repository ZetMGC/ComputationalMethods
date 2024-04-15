package base;

import base.Matrix;
import base.Vec;
import exceptions.EmptyMatrixException;

/**
 * Класс системы линейных уравнений.
 */
public class LinearEquationSystem {

    /**
     * Интерфейс, представляющий функцию и ее якобиан.
     */
    public interface Function {

        /**
         * Вычисляет значение функции в заданной точке.
         *
         * @param x массив значений переменных функции
         * @return массив значений функции в заданной точке
         */
        double[] value(double[] x);

        /**
         * Вычисляет якобиан функции в заданной точке.
         *
         * @param x массив значений переменных функции
         * @return матрица якобиана функции в заданной точке
         */
        double[][] jacobian(double[] x);
    }

    private double[][] matrixA;
    private double[] vectorB;
    private double[] solution;

    public LinearEquationSystem(double[][] matrixA, double[] vectorB) {
        this.matrixA = matrixA;
        this.vectorB = vectorB;
        this.solution = new double[vectorB.length];
    }

    /**
     * Метод используется для проверки решения системы линейных уравнений.
     * @param tolerance точность необходимого решения
     */
    public void checkSolution(double tolerance) {
        // Вычисляем произведение матрицы A на вектор x
        double[] Ax = Vec.matrixVectorProduct(this.matrixA, this.solution);

        // Вычисляем разность между полученным вектором Ax и вектором правой части b
        double[] difference = Vec.vectorSubtraction(Ax, this.vectorB);

        // Проверяем, что норма разности меньше допустимой погрешности (tolerance)
        double normDifference = Vec.vectorNorm(difference);
        if (normDifference < tolerance)
            System.out.println("Решение является допустимым решением СЛУ");
        else System.out.println("Решение недопустимо");
    }


    /**
     * Выводит решение системы линейных уравнений.
     */
    public void printSolution() {
        try {
            Matrix.print(this.solution);
        } catch (EmptyMatrixException e) {
            System.out.println("Получено исключение: " + e.getMessage());
        }
    }

    public void print() {
        Matrix.print(this.matrixA, this.vectorB);
    }

    public double[][] getMatrixA() {
        return matrixA;
    }

    public double[] getVectorB() {
        return vectorB;
    }

    public double[] getSolution() {
        return solution;
    }

    public void setSolution(double[] solution) {
        this.solution = solution;
    }
}
