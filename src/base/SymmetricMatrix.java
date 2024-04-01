package base;

public class SymmetricMatrix {
    public static double[][] makeSymmetric(double[][] A) {
        int n = A.length;

        // Проверяем, является ли матрица уже симметричной
        boolean isSymmetric = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i][j] != A[j][i]) {
                    isSymmetric = false;
                    break;
                }
            }
            if (!isSymmetric) {
                break;
            }
        }

        // Если матрица уже симметрична, возвращаем ее без изменений
        if (isSymmetric) {
            return A;
        }

        // Иначе приводим матрицу к симметричному виду
        double[][] symmetricA = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                symmetricA[i][j] = 0.5 * (A[i][j] + A[j][i]);
            }
        }

        return symmetricA;
    }
}
