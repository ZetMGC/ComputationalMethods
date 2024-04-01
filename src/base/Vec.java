package base;

public class Vec {

    /**
     * Вычисляет скалярное произведение двух векторов.
     */
    public static double dotProduct(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    /**
     * Вычисляет произведение матрицы на вектор.
     *
     * @param A Матрица
     * @param x вектор, на который происходит умножение
     */
    public static double[] matrixVectorProduct(double[][] A, double[] x) {

        int n = A.length;
        double [] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = dotProduct(A[i], x);
        }
        return result;
    }

    /**
     * Умножает вектор {@code v} на скаляр.
     * @param v вектор.
     * @param scalar скаляр
     */
    public static void vectorScalarProduct(double[] v, double scalar) {
        for (int i = 0; i < v.length; i++) {
            v[i] *= scalar;
        }
    }

    /**
     * Вычисление длины вектора в пространстве. Это вычисление соответствует евклидовой норме.
     * @param vector вектор, норму которого необходимо найти
     */
    public static double vectorNorm(double[] vector) {
        double sumOfSquares = 0.0;
        for (double element : vector) {
            sumOfSquares += element * element;
        }
        return Math.sqrt(sumOfSquares); // Вычисление квадратного корня из суммы квадратов элементов
    }

    /**
     * Выполняет поэлементное сложение двух векторов.
     */
    public static double[] vectorAddition(double[] a, double[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] += b[i];
        }
        return a;
    }

    /**
     * Выполняет поэлементное вычитание двух векторов.<br/>
     * Фактически, выполняет действие: {@code a -= b}
     *
     * @return возвращает вектор a
     */
    public static double[] vectorSubtraction(double[] a, double[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] -= b[i];
        }
        return a;
    }

    /**
     * Вычисляет значение полинома в заданной точке X
     * @param coefficients Массив коэффициентов полинома. Каждый элемент массива
     *                     представляет собой коэффициент для соответствующей степени полинома.
     * @param x Точка, в которой нужно вычислить значение полинома.
     */
    public static double evaluatePolynomial(double[] coefficients, double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }
}
