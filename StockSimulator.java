package stocksimulator;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/* Angel Fernandez */
public class StockSimulator extends Application {

    HBox root;
    Scene scene;
    CategoryAxis xAxis;
    NumberAxis yAxis;
    Random rand;

    int value;
    int inititalValue;
    int time;
    int volatilityLevel;

    LineChart<String, Number> lineChart;
    XYChart.Series<String, Number> data;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        create();
        setChart();
        initailize();
        run();
        show(stage);
    }

    private void create() {
        root = new HBox();
        scene = new Scene(root, 500, 330);
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();

        xAxis.setLabel("Time");
        yAxis.setLabel("Price");

        rand = new Random();
    }

    private void setChart() {
        lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle("Single Stock Price Change");

        data = new XYChart.Series<String, Number>();
    }

    private void initailize() {
        value = rand.nextInt(10) + 1;
        inititalValue = value;
        time = 365;
        volatilityLevel = rand.nextInt(20) + 1;
    }

    private void run() {
        System.out.println("Initial Price: " + value);
        System.out.println("Volatility Level: " + volatilityLevel);

        for (int i = 0; i < time; i++) {

            boolean direction = rand.nextBoolean();
            int variation = rand.nextInt(volatilityLevel);

            if (direction) {
                value += variation;
            } else {
                value -= variation;
            }
            data.getData().add(new XYChart.Data<String, Number>("" + i, value));
            
        }

        System.out.println("Final Price: " + value);
        System.out.println("Price Change: " + (value - inititalValue));
        System.out.println("Time Lapsed: " + time);

        lineChart.getData().add(data);
        root.getChildren().add(lineChart);
    }

    private void show(Stage primaryStage) {
        primaryStage.setTitle("Stock Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
