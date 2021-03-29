package comp1721.cwk1;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class CovidChart extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int x=0;
        CovidDataset a = new CovidDataset();
        a.readDailyCases("../datafiles/2020-daily.csv");
        a.writeActiveCases("../datafiles/2020-active.csv");
        a.readDailyCases("../datafiles/2020-active.csv");
        //定义X轴，范围1960~2020，间隔10.
        NumberAxis xAxis = new NumberAxis(a.Caselist.get(0).getDate().getDayOfYear(),a.Caselist.get(a.size()-1).getDate().getDayOfYear(),10);
        xAxis.setLabel("Date");

        //定义Y轴，范围0~350，间隔50.
        NumberAxis yAxis = new NumberAxis   (0, 1000, 50);
        yAxis.setLabel("Cases");

        //创建图表对象，传入上面定义的X轴和Y轴
        LineChart linechart = new LineChart(xAxis, yAxis);

        //定义图表需要的数据
        XYChart.Series series = new XYChart.Series();
        series.setName("Leeds University Cases-Date");

        //添加数据
       /* series.getData().add(new XYChart.Data(1970, 15));
        series.getData().add(new XYChart.Data(1980, 30));
        series.getData().add(new XYChart.Data(1990, 60));
        series.getData().add(new XYChart.Data(2000, 120));
        series.getData().add(new XYChart.Data(2013, 240));
        series.getData().add(new XYChart.Data(2014, 300));*/
        while(x < a.Caselist.size())
        {
            series.getData().add(new XYChart.Data(a.Caselist.get(x).getDate().getDayOfYear(),a.Caselist.get(x).totalCases()));
            x++;
        }

        //给图表传入数据
        linechart.getData().add(series);

        //创建场景对象
        Scene scene = new Scene(linechart, 850, 850);

        //设置场景标题
        stage.setTitle("CoxidChart");

        //显示窗口
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}

