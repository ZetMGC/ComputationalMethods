package base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Reader {

    /**
     * Метод чтения системы линейных уравнений из файла.
     * <p/>
     * @param filePath Путь к файлу с матрицей коэффициентов
     * @return Объект класса {@code LinearEquationSystem}. Система линейных уравнений.
     */
    public static LinearEquationSystem readEquations(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        double[][] A = new double[rows][cols];
        double[] B = new double[rows];

        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.trim().split("\\s+");
            for (int col = 0; col < cols; col++) {
                A[row][col] = Double.parseDouble(values[col]);
            }
            B[row] = Double.parseDouble(values[cols]);
            row++;
        }

        reader.close();

        return new LinearEquationSystem(A, B);
    }

    /**
     * Метод для считывания условия из файла для локального сглаживания данных
     */
    public static SmoothingParameters readDataSmoothing(String filePath) {
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Считываем пары x и y
                String[] parts = line.split("\\s+");
                xList.add(Double.parseDouble(parts[0]));
                yList.add(Double.parseDouble(parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Преобразование списков в массивы
        double[] x = xList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] y = yList.stream().mapToDouble(Double::doubleValue).toArray();

        return new SmoothingParameters(x, y);
    }
}

