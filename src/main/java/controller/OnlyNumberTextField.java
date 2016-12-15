package controller;

import javafx.scene.control.TextField;

/**
 * @author Michlu
 * @sience 2016-12-15
 */
public class OnlyNumberTextField implements ControllerHelper {
    /**
     * Zamienia standardowy TextField na przyjmujacy tylko liczby
     * @param textField przyjmuje referencje pola tekstowego
     */
    public static void onlyNumberTextField(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                textField.setText(oldValue);
            }
        });
        // Kasuj zero na poaczatku
        textField.setOnKeyTyped(event -> {
            if(textField.getText().length()==1 && textField.getText().charAt(0)=='0'){
                textField.setText(textField.getText().substring(0, 0));
            }
        });
    }
}
