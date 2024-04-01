package base;

/**
 * Вспомогательный класс для хранения параметров сглаживания
 */
public class SmoothingParameters {
    public final double[] x;
    public final double[] y;

    public SmoothingParameters(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }
}
