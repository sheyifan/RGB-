

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                new PieChart.Data("R", 0.33333),
                new PieChart.Data("G", 0.33333),
                new PieChart.Data("B", 0.33333)
        );
        PieChart chart = new PieChart(chartData);
        chart.setTitle("#000000");
        chart.setStartAngle(0);
        //滑块对象声明与配置
        Slider RSlider = new Slider();
        RSlider.setMin(0);
        RSlider.setMax(255);
        RSlider.setValue(0);
        RSlider.setShowTickLabels(true); //显示刻度
        RSlider.setShowTickMarks(true);
        RSlider.setMajorTickUnit(50);
        RSlider.setMinorTickCount(5);
        RSlider.setBlockIncrement(10);
        //RSlider.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        Label RLabel = new Label();
        HBox RSliderBox = new HBox();
        RSliderBox.setPrefWidth(500);
        RSliderBox.setPadding(new Insets(10, 0, 10, 10));
        RSliderBox.getChildren().add(RSlider);
        RSlider.setPrefWidth(500);
        Slider GSlider = new Slider();
        RLabel.setText("000");
        RLabel.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        GSlider.setMin(0);
        GSlider.setMax(255);
        GSlider.setValue(0);
        GSlider.setShowTickLabels(true);
        GSlider.setShowTickMarks(true);
        GSlider.setMajorTickUnit(50);
        GSlider.setMinorTickCount(5);
        GSlider.setBlockIncrement(10);
        Label GLabel = new Label();
        HBox GSliderBox = new HBox();
        GSliderBox.setPrefWidth(500);
        GSliderBox.setPadding(new Insets(10, 0, 10, 10));
        GSliderBox.getChildren().add(RSlider);
        GSlider.setPrefWidth(500);
        GLabel.setText("000");
        GLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        Slider BSlider = new Slider();
        BSlider.setMin(0);
        BSlider.setMax(255);
        BSlider.setValue(0);
        BSlider.setShowTickLabels(true);
        BSlider.setShowTickMarks(true);
        BSlider.setMajorTickUnit(50);
        BSlider.setMinorTickCount(5);
        BSlider.setBlockIncrement(10);
        Label BLabel = new Label();
        HBox BSliderBox = new HBox();
        BSliderBox.setPrefWidth(500);
        BSliderBox.setPadding(new Insets(10, 0, 10, 10));
        BSliderBox.getChildren().add(RSlider);
        BSlider.setPrefWidth(500);
        BLabel.setText("000");
        BLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        Slider OSlider = new Slider();
        OSlider.setMin(0);
        OSlider.setMax(255);
        OSlider.setValue(255);
        OSlider.setShowTickLabels(true);
        OSlider.setShowTickMarks(true);
        OSlider.setMajorTickUnit(50);
        OSlider.setMinorTickCount(5);
        OSlider.setBlockIncrement(10);
        Label OLabel = new Label();
        OLabel.setText("255");
        OLabel.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        Pane pane =new Pane();
        pane.setMinSize(250, 400);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane.setPadding(new Insets(0));
        HBox paneBox = new HBox();
        paneBox.setMinHeight(500);
        paneBox.getChildren().add(pane);
        VBox.setMargin(paneBox, new Insets(0));
        Button fileButton = new Button();
        fileButton.setText("Open");
        ColorPicker colorPicker = new ColorPicker(Color.BLUE);
        VBox vBox = new VBox();
        //vBox.getChildren().add(fileButton);
        //设置外边距
        VBox.setMargin(fileButton, new Insets(10));
        vBox.setAlignment(Pos.TOP_LEFT);
        //菜单栏
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        Menu fileMenu = new Menu("文件");
        Menu exitMenu = new Menu("退出");
        menuBar.getMenus().addAll(fileMenu, exitMenu);

        /*
        RSlider.setOnMouseReleased((event)->{
            double RNum = RSlider.getValue();
            RLabel.setText(new DecimalFormat("000").format((int)RNum));
        });
        */
        //监听RSlider
        RSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                //System.out.println(new_val.doubleValue());
                RLabel.setText(new DecimalFormat("000").format((int)new_val.doubleValue()));
                String red = Integer.toHexString(Integer.parseInt(RLabel.getText()));
                if(red.length() == 1)
                    red = "0".concat(red);
                String green = Integer.toHexString(Integer.parseInt(GLabel.getText()));
                if(green.length() ==1)
                    green = "0".concat(green);
                String blue = Integer.toHexString(Integer.parseInt(BLabel.getText()));
                if(blue.length() == 1)
                    blue = "0".concat(blue);
                StringBuilder rgb = new StringBuilder("#").append(red).append(green).append(blue);
                pane.setBackground(new Background(new BackgroundFill(Color.valueOf(rgb.toString()), null, null)));
                pane.setOpacity(Double.parseDouble(OLabel.getText())/255);
                chart.setTitle(rgb.toString());
                double redDouble =Double.parseDouble(RLabel.getText());
                double greenDouble =Double.parseDouble(GLabel.getText());
                double blueDouble = Double.parseDouble(BLabel.getText());
                if(redDouble==0 && greenDouble==0 && blueDouble==0)
                {
                    chartData.get(0).setPieValue(0.33333);
                    chartData.get(1).setPieValue(0.33333);
                    chartData.get(2).setPieValue(0.33333);
                }
                else{
                    double redPer = redDouble/(redDouble+greenDouble+blueDouble);
                    double greenPer = greenDouble/(redDouble+greenDouble+blueDouble);
                    double bluePer = blueDouble/(redDouble+greenDouble+blueDouble);
                    chartData.get(0).setPieValue(bluePer);
                    chartData.get(1).setPieValue(greenPer);
                    chartData.get(2).setPieValue(redPer);
                }
            }
        });
        GSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                //System.out.println(new_val.doubleValue());
                GLabel.setText(new DecimalFormat("000").format((int)new_val.doubleValue()));
                String red = Integer.toHexString(Integer.parseInt(RLabel.getText()));
                if(red.length() == 1)
                    red = "0".concat(red);
                String green = Integer.toHexString(Integer.parseInt(GLabel.getText()));
                if(green.length() ==1)
                    green = "0".concat(green);
                String blue = Integer.toHexString(Integer.parseInt(BLabel.getText()));
                if(blue.length() == 1)
                    blue = "0".concat(blue);
                StringBuilder rgb = new StringBuilder("#").append(red).append(green).append(blue);
                pane.setBackground(new Background(new BackgroundFill(Color.valueOf(rgb.toString()), null, null)));
                pane.setOpacity(Double.parseDouble(OLabel.getText())/255);
                chart.setTitle(rgb.toString());
                double redDouble =Double.parseDouble(RLabel.getText());
                double greenDouble =Double.parseDouble(GLabel.getText());
                double blueDouble = Double.parseDouble(BLabel.getText());
                if(redDouble==0 && greenDouble==0 && blueDouble==0)
                {
                    chartData.get(0).setPieValue(0.33333);
                    chartData.get(1).setPieValue(0.33333);
                    chartData.get(2).setPieValue(0.33333);
                }
                else{
                    double redPer = redDouble/(redDouble+greenDouble+blueDouble);
                    double greenPer = greenDouble/(redDouble+greenDouble+blueDouble);
                    double bluePer = blueDouble/(redDouble+greenDouble+blueDouble);
                    chartData.get(0).setPieValue(bluePer);
                    chartData.get(1).setPieValue(greenPer);
                    chartData.get(2).setPieValue(redPer);
                }
            }
        });
        BSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                //System.out.println(new_val.doubleValue());
                BLabel.setText(new DecimalFormat("000").format((int)new_val.doubleValue()));
                String red = Integer.toHexString(Integer.parseInt(RLabel.getText()));
                if(red.length() == 1)
                    red = "0".concat(red);
                String green = Integer.toHexString(Integer.parseInt(GLabel.getText()));
                if(green.length() ==1)
                    green = "0".concat(green);
                String blue = Integer.toHexString(Integer.parseInt(BLabel.getText()));
                if(blue.length() == 1)
                    blue = "0".concat(blue);
                StringBuilder rgb = new StringBuilder("#").append(red).append(green).append(blue);
                pane.setBackground(new Background(new BackgroundFill(Color.valueOf(rgb.toString()), null, null)));
                pane.setOpacity(Double.parseDouble(OLabel.getText())/255);
                chart.setTitle(rgb.toString());
                double redDouble =Double.parseDouble(RLabel.getText());
                double greenDouble =Double.parseDouble(GLabel.getText());
                double blueDouble = Double.parseDouble(BLabel.getText());
                if(redDouble==0 && greenDouble==0 && blueDouble==0)
                {
                    chartData.get(0).setPieValue(0.33333);
                    chartData.get(1).setPieValue(0.33333);
                    chartData.get(2).setPieValue(0.33333);
                }
                else{
                    double redPer = redDouble/(redDouble+greenDouble+blueDouble);
                    double greenPer = greenDouble/(redDouble+greenDouble+blueDouble);
                    double bluePer = blueDouble/(redDouble+greenDouble+blueDouble);
                    chartData.get(0).setPieValue(bluePer);
                    chartData.get(1).setPieValue(greenPer);
                    chartData.get(2).setPieValue(redPer);
                }
            }
        });
        OSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                //System.out.println(new_val.doubleValue());
                OLabel.setText(new DecimalFormat("000").format((int)new_val.doubleValue()));
                String red = Integer.toHexString(Integer.parseInt(RLabel.getText()));
                if(red.length() == 1)
                    red = "0".concat(red);
                String green = Integer.toHexString(Integer.parseInt(GLabel.getText()));
                if(green.length() ==1)
                    green = "0".concat(green);
                String blue = Integer.toHexString(Integer.parseInt(BLabel.getText()));
                if(blue.length() == 1)
                    blue = "0".concat(blue);
                StringBuilder rgb = new StringBuilder("#").append(red).append(green).append(blue);
                pane.setBackground(new Background(new BackgroundFill(Color.valueOf(rgb.toString()), null, null)));
                pane.setOpacity(Double.parseDouble(OLabel.getText())/255);
                chart.setTitle(rgb.toString());
            }
        });
        colorPicker.setOnAction(event -> {
            String colorValue = colorPicker.getValue().toString();
            String red = colorValue.substring(2, 4);
            String green =colorValue.substring(4, 6);
            String blue = colorValue.substring(6, 8);
            String opacity = colorValue.substring(8, 10);
            RSlider.setValue(Integer.valueOf(red, 16));
            GSlider.setValue(Integer.valueOf(green, 16));
            BSlider.setValue(Integer.valueOf(blue, 16));
            OSlider.setValue(Integer.valueOf(opacity, 16));
        });
        exitMenu.setOnAction((event) -> {
            System.out.println(event.getEventType());
        });
        pane.setOnMouseClicked((event) -> {
            String s = pane.getBackground().getFills().get(0).getFill().toString();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(s);
            clipboard.setContent(content);
        });


        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(0, 0, 20, 0));
        grid.setAlignment(Pos.CENTER);
        grid.add(menuBar, 0, 0, 5, 1);
        grid.add(RSlider, 0 ,1, 1, 1);
        grid.add(RLabel, 1,1, 1,1);
        grid.add(GSlider, 0, 2, 1, 1);
        grid.add(GLabel, 1, 2, 1, 1);
        grid.add(BSlider, 0, 3, 1, 1);
        grid.add(BLabel, 1, 3, 1, 1);
        grid.add(OSlider, 0, 4, 1, 1);
        grid.add(OLabel, 1, 4, 1, 1);
        grid.add(separator, 2, 1, 1, 5);
        grid.add(paneBox, 3, 1, 1, 5);
        grid.add(vBox, 0, 5, 1, 1);
        grid.add(chart, 0, 5, 1, 1);
        grid.add(colorPicker, 0, 5, 1, 1);

        Scene scene = new Scene(grid, 865, 620);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("调色板  |  佘一凡");
        Application.setUserAgentStylesheet(getClass().getResource("t.css").toString());
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
