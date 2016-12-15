package controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Model;
import model.User;
import model.ViewModel;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class BmiController implements ControllerHelper {

    @FXML private ToggleButton btnMen, btnWoman;
    @FXML private Button btnCalculate;
    @FXML private TextField ageTextField, heightTextField, weightTextField;
    @FXML private Label ageOkLabel, heightOkLabel, weightOkLabel;
    @FXML private ToggleGroup toggleGender;
    @FXML private Label bmiLabel, yourBMI, labelMax, labelMin, labelResultBmi;
    @FXML private Polygon arrow;

    private Model model = new Model();
    private ViewModel viewModel;
    private User user;

    TranslateTransition transition;

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

        // Animacja
        transition = new TranslateTransition();
        transition.setNode(arrow);
        startAnimationArrow();
    }

    @FXML
    public void initialize(){
        // TextFieldy przyjmujace tylko liczby
        OnlyNumberTextField.onlyNumberTextField(heightTextField);
        OnlyNumberTextField.onlyNumberTextField(weightTextField);
        OnlyNumberTextField.onlyNumberTextField(ageTextField);
    }

    @FXML
    public void btnReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        ageTextField.setText("0");
        heightTextField.setText("0");
        weightTextField.setText("0");
        yourBMI.setVisible(false);
        bmiLabel.setText("BMI");
        labelMin.setVisible(false);
        labelMax.setVisible(false);
        labelResultBmi.setText("");
        viewModel.bmiPropertyProperty().set(0.0);
        startAnimationArrow();

    }

    public void stopAnimationArrow(Double BMI){
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.stop();
        transition.setDuration(Duration.seconds(1));

        int stopPunkt = 0;
        if (BMI <= 18.5) {
            stopPunkt = (int) ((BMI)*3.24);
        } else if (BMI >18.5 && BMI <= 24.9) {
            stopPunkt = (int) ((BMI-18.5)*9.35)+60;
        } else if (BMI >24.9 && BMI <= 29.9) {
            stopPunkt = (int) ((BMI-24.9)*12)+120;
        } else if (BMI >29.9 && BMI <= 34.9) {
            stopPunkt = (int) ((BMI-29.9)*12)+180;
        } else if (BMI >34.9 && BMI <= 39.9) {
            stopPunkt = (int) ((BMI-34.9)*12)+240;
        } else {
            stopPunkt = 300;
        }
        transition.setToX(stopPunkt);
        transition.play();
    }

    public  void startAnimationArrow(){
        arrow.setTranslateX(0);
        transition.setDuration(Duration.seconds(5));
        transition.setToX(300);
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        arrow.setFill(Color.TRANSPARENT);
        transition.play();
    }

    public void drawRectnagleBMI(Double BMI){
        int colorRed = 0;
        int colorGreen = 255;
        int colorBlue = 255;
        if (BMI <= 18.5) {
            colorRed = 0;
            colorGreen = 255;
            colorBlue = 255;
        } else if (BMI >18.5 && BMI <= 24.9) {
            colorRed = 0;
            colorGreen = 255;
            colorBlue = 33;
        } else if (BMI >24.9 && BMI <= 29.9) {
            colorRed = 255;
            colorGreen = 216;
            colorBlue = 0;
        } else if (BMI >29.9 && BMI <= 34.9) {
            colorRed = 255;
            colorGreen = 106;
            colorBlue = 0;
        } else if (BMI >34.9 && BMI <= 39.9) {
            colorRed = 255;
            colorGreen = 0;
            colorBlue = 0;
        } else if(BMI > 40) {
            colorRed = 255;
            colorGreen = 0;
            colorBlue = 0;
        }

        LinearGradient gradient = new LinearGradient(
                0,// start X
                0, //startY
                0, //endX
                1, //endY
                true, //proportional
                CycleMethod.NO_CYCLE, //cycleMethod
                new Stop(0f, Color.rgb(colorRed, colorGreen, colorBlue)), //stops
                new Stop(1f, Color.TRANSPARENT) //stops
        );

        arrow.setFill(gradient);
        yourBMI.setVisible(true);
    }

    @FXML
    public void bmiCalculate(){
        user.getAtributes(viewModel);
        drawRectnagleBMI(model.obliczBmi(user));
        bmiLabel.setText(String.format("%.2f", model.obliczBmi(user)));

        labelMin.setText(String.format("%.1f", model.wagaMinimalna(user)) + "kg");
        labelMin.setVisible(true);
        labelMax.setText(String.format("%.1f", model.wagaMaksymalna(user)) + "kg");
        labelMax.setVisible(true);

        labelResultBmi.setText(model.wynikTxtBmi(user));
        stopAnimationArrow(model.obliczBmi(user));
        viewModel.bmiPropertyProperty().set(model.obliczBmi(user));
    }
}
