package base;

public class MathMeth {
    /**
     * Метод для вычисления факториала числа.
     *
     * @param n число, для которого нужно вычислить факториал
     * @return факториал числа n
     */
    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
