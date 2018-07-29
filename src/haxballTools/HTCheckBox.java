package haxballTools;

import javafx.scene.control.CheckBox;

class HTCheckBox extends CheckBox {

    HTCheckBox(HTExecutor executor, String text, HTScript script) {
        super(text);
        setMinSize(100, 61);
        setSelected(false);
        setStyle("-fx-font: 20px Tahoma;");
        selectedProperty().addListener((o, l, isCheck) -> {
            if (isCheck) executor.addScript(script);
            else executor.removeScript(script);
        });
    }
}
