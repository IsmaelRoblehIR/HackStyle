package haxballTools;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Map;

public class HTGui extends BorderPane {

    private static TextField textField;
    private static ScrollBar scrollBar;
    private Label label;

    public HTGui(HTExecutor executor, Map<String, HTScript> scripts) {

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        scripts.forEach((string, script) -> {
            HTCheckBox checkBox = new HTCheckBox(executor, string, script);
            hBox.getChildren().add(checkBox);
        });

        textField = new TextField("");
        textField.setMaxWidth(200);
        textField.setStyle("-fx-font: 20px Tahoma;");

        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.HORIZONTAL);
        scrollBar.valueProperty().addListener(e -> executor.setDuration((int) (1000 / scrollBar.getValue())));
        scrollBar.setPrefHeight(61);
        scrollBar.setValue(60);
        scrollBar.setMax(100);
        scrollBar.setMin(5);

        label = new Label("Executions per second: " + scrollBar.getValue());
        label.setTextFill(Color.WHITE);
        scrollBar.valueProperty().addListener(e -> label.setText("Executions per second: " + (int)getScrollBarValue()));

        vBox.getChildren().addAll(hBox, textField, scrollBar, label);

        setCenter(vBox);
        setStyle("-fx-background-color: black");
    }

    public static double getScrollBarValue() {
        return scrollBar != null ? scrollBar.getValue() : 0;
    }

    public static String getTextFieldText() {
        return textField != null ? textField.getText() : "";
    }
}
