package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Model;
import model.User;
import model.ViewModel;



/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class BmiController {

    @FXML private ToggleButton btnMen, btnWoman;
    @FXML private Button btnCalculate;
    @FXML private TextField ageTextField, heightTextField, weightTextField;
    @FXML private Label ageOkLabel, heightOkLabel, weightOkLabel;
    @FXML private ToggleGroup toggleGender;
    @FXML private Label bmiLabel, yourBMI, labelMax, labelMin, labelResultBmi;
    @FXML private Rectangle bmiLabelRectangle;

    private Model model = new Model();
    private ViewModel viewModel;
    private User user;


    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void start(){

        StringConverter conventer = new NumberStringConverter();
        // ToggleGroup gender zbindowany do
        btnMen.selectedProperty().bindBidirectional(viewModel.genderManPropertyProperty());
        btnWoman.selectedProperty().bindBidirectional(viewModel.genderWomanPropertyProperty());


        // Wiek textfield zbindowany z property age
        ageTextField.textProperty().bindBidirectional(viewModel.agePropertyProperty(), conventer);

        ageTextField.disableProperty().bind(viewModel.okGenderPropertyProperty());
        ageOkLabel.visibleProperty().bind(viewModel.okAgePropertyProperty());


        // Wzrost textfield zbindowany z property height

        heightTextField.textProperty().bindBidirectional(viewModel.heightPropertyProperty(), conventer);
        heightTextField.disableProperty().bind(viewModel.disableHeightPropertyProperty());
        heightOkLabel.visibleProperty().bind(viewModel.okHeigihtPropertyProperty());


        // Waga TextField zbindowany z weight property

        weightTextField.textProperty().bindBidirectional(viewModel.weightPropertyProperty(), conventer);
        weightTextField.disableProperty().bind(viewModel.disableWeightPropertyProperty());
        weightOkLabel.visibleProperty().bind(viewModel.okWeightPropertyProperty());

        // Button Calculate zbndowany z disable calculate

        btnCalculate.disableProperty().bind(viewModel.disableCalculatePropertyProperty());


    }

    @FXML
    public void initialize(){

        // TextFieldy przyjmujace tylko liczby
        onlnyNumberTextField(ageTextField);
        onlnyNumberTextField(heightTextField);
        onlnyNumberTextField(weightTextField);

    }
    @FXML
    public void btnReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        ageTextField.setText("0");
        heightTextField.setText("0");
        weightTextField.setText("0");
        bmiLabelRectangle.setVisible(false);
        yourBMI.setVisible(false);
        bmiLabel.setText("BMI");
        labelMin.setVisible(false);
        labelMax.setVisible(false);
        labelResultBmi.setText("");
        viewModel.bmiPropertyProperty().set(0.0);

    }
    public void drawRectnagleBMI(Double BMI){
        int colorRed = 0;
        int colorGreen = 255;
        int colorBlue = 255;
        if (BMI <= 18.5) {
            colorRed = 0;
            colorGreen = 255;
            colorBlue = 255;
        } else if (BMI <= 24.9) {
            colorRed = 0;
            colorGreen = 255;
            colorBlue = 33;
        } else if (BMI <= 29.9) {
            colorRed = 255;
            colorGreen = 116;
            colorBlue = 0;
        } else if (BMI <= 34.9) {
            colorRed = 255;
            colorGreen = 106;
            colorBlue = 0;
        } else if (BMI <= 39.9) {
            colorRed = 255;
            colorGreen = 106;
            colorBlue = 0;
        } else {
            colorRed = 255;
            colorGreen = 106;
            colorBlue = 0;
        }

        bmiLabelRectangle.setFill(Color.rgb(colorRed, colorGreen, colorBlue));
        bmiLabelRectangle.setVisible(true);
        yourBMI.setVisible(true);
    }


    @FXML
    public void bmiCalculate(){
        user.getAtributes(viewModel);
        System.out.println(user.toString());
        drawRectnagleBMI(model.obliczBmi(user));
        bmiLabel.setText(String.format("%.2f", model.obliczBmi(user)));

        labelMin.setText(String.format("%.1f", model.wagaMinimalna(user)) + "kg");
        labelMin.setVisible(true);
        labelMax.setText(String.format("%.1f", model.wagaMaksymalna(user)) + "kg");
        labelMax.setVisible(true);

        setTextFlowBmi();

        viewModel.bmiPropertyProperty().set(model.obliczBmi(user));
    }

    public void setTextFlowBmi(){
        labelResultBmi.setText(model.wynikTxtBmi(user));
    }

    /**
     * Zamienia standardowy TextField na przyjmujacy tylko liczby
     * @param textField przyjmuje referencje pola tekstowego
     */
    public void onlnyNumberTextField(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                textField.setText(oldValue);
            }
        });
    }
}
