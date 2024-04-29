package base;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.function.Function2D;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphHandler {
    /**
     * Метод для отображения графика с шумом.
     *
     * @param xData         Массив x-ов.
     * @param yData         Массив изначальных y-ов.
     * @param smoothedYData Массив сглаженных y-ов.
     * @param noiseData     Массив "зашумленных" y-ов.
     */
    public static void displayGraphs(double[] xData, double[] yData, double[] smoothedYData, double[] noiseData) {
        // Создание серии для исходных данных
        XYSeries series = new XYSeries("Исходная функция");
        XYSeries smoothedSeries = new XYSeries("Сглаженная функция");
        XYSeries noiseSeries = new XYSeries("Шум");

        // Заполнение серий данными
        for (int i = 0; i < xData.length; i++) {
            series.add(xData[i], yData[i]);
            smoothedSeries.add(xData[i], smoothedYData[i]);
            noiseSeries.add(xData[i], noiseData[i]);
        }

        // Добавление серий в коллекцию
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(smoothedSeries);
        dataset.addSeries(noiseSeries);

        // Создание графика
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График функции, сглаженной функции и шума",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Получение объекта XYPlot
        XYPlot plot = (XYPlot) chart.getPlot();

        // Установка белого фона для графика
        plot.setBackgroundPaint(Color.WHITE);

        // Установка черного цвета для сетки
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        // Создание панели для отображения графика
        ChartPanel chartPanel = new ChartPanel(chart);

        // Создание чекбокса для переключения видимости графиков
        JCheckBox seriesCheckbox = new JCheckBox("Исходная функция", true);
        JCheckBox smoothedSeriesCheckbox = new JCheckBox("Сглаженная функция", true);
        JCheckBox noiseSeriesCheckbox = new JCheckBox("Шум", true);

        // Создание панели для чекбоксов
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new FlowLayout());
        checkboxPanel.add(seriesCheckbox);
        checkboxPanel.add(smoothedSeriesCheckbox);
        checkboxPanel.add(noiseSeriesCheckbox);

        // Добавление обработчиков событий для чекбоксов
        seriesCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.getRenderer().setSeriesVisible(0, seriesCheckbox.isSelected());
            }
        });

        smoothedSeriesCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.getRenderer().setSeriesVisible(1, smoothedSeriesCheckbox.isSelected());
            }
        });

        noiseSeriesCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plot.getRenderer().setSeriesVisible(2, noiseSeriesCheckbox.isSelected());
            }
        });

        // Устанавливаем предпочтительный размер для панели графика
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Создание окна для отображения графика и чекбоксов
        JFrame frame = new JFrame("График функции, сглаженной функции и шума");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.add(checkboxPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void displayGraph(double[] xData, double[] yData) {
        // Создание серии для исходных данных
        XYSeries series = new XYSeries("Заданная функция");

        // Заполнение серий данными
        for (int i = 0; i < xData.length; i++) {
            series.add(xData[i], yData[i]);
        }

        // Добавление серий в коллекцию
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Создание графика
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График функции",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Получение объекта XYPlot
        XYPlot plot = (XYPlot) chart.getPlot();

        // Установка белого фона для графика
        plot.setBackgroundPaint(Color.WHITE);

        // Установка черного цвета для сетки
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        // Создание панели для отображения графика
        ChartPanel chartPanel = new ChartPanel(chart);

        // Создание чекбокса для переключения видимости графиков
        JCheckBox seriesCheckbox = new JCheckBox("Заданная функция", true);

        // Устанавливаем предпочтительный размер для панели графика
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Создание окна для отображения графика
        JFrame frame = new JFrame("График функции");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
