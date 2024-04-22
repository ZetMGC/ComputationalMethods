package NumericalIntegration;

import java.util.function.Function;

public class AdamsMethod {

    /**
     * Реализация четырехшагового неявного метода Адамса для решения обыкновенных дифференциальных уравнений.
     *
     * @param yn Значение функции на текущем временном шаге.
     * @param h  Шаг интегрирования.
     * @param f  Функция, представляющая производную функции.
     * @return Значение функции на следующем временном шаге.
     */
    public static double adamsFourthOrder(double yn, double h, Function<Double, Double> f) {
        // Вычисляем значения производных на текущем, предыдущем и двух предыдущих временных шагах
        double fn = f.apply(yn);
        double fnMinus1 = f.apply(yn - h * fn);
        double fnMinus2 = f.apply(yn - 2 * h * fn);
        double fnMinus3 = f.apply(yn - 3 * h * fn);

        // Вычисляем значение функции на следующем временном шаге
        return yn + (h / 24) * (9 * fn + 19 * fnMinus1 - 5 * fnMinus2 + fnMinus3);
    }

    public static void main(String[] args) {
        // Пример использования метода для решения дифференциального уравнения y' = -2y
        double yn = 1; // Начальное значение функции
        double h = 0.1; // Шаг интегрирования
        int steps = 10; // Количество шагов интегрирования

        // Функция для вычисления значения производной
        Function<Double, Double> f = y -> -2 * y;

        // Интегрирование с использованием четырехшагового неявного метода Адамса
        for (int i = 0; i < steps; i++) {
            System.out.println("y(" + (i+1) + ") = " + yn);
            yn = adamsFourthOrder(yn, h, f);
        }
    }
}
