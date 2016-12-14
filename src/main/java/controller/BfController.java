package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Model;
import model.User;
import model.ViewModel;

import java.util.ResourceBundle;


/**
 * @author Michlu
 * @sience 2016-12-12
 */
public class BfController {

    @FXML private ToggleButton btnMen, btnWoman;
    @FXML private TextField ageTextField;
    @FXML private Label ageOkLabel;
    @FXML private ToggleGroup toggleGender;

    @FXML private Button btnCalculateBf;
    @FXML private Label lblMeasurement1, lblMeasurement2, lblMeasurement3, measurement1OkLabel, measurement2OkLabel, measurement3OkLabel, resultBf;
    @FXML private TextField measurement1TextField, measurement2TextField, measurement3TextField;

    ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

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

        // Chest / Triceps
        measurement1TextField.disableProperty().bind(viewModel.disableChestPropertyProperty());
        measurement1TextField.disableProperty().bind(viewModel.disableTricepsPropertyProperty());

        measurement1TextField.textProperty().bindBidirectional(viewModel.chestPropertyProperty(), conventer);//Man
        measurement1TextField.textProperty().bindBidirectional(viewModel.tricepsPropertyProperty(), conventer);//Woman

        measurement1OkLabel.visibleProperty().bind(viewModel.okChestPropertyProperty());
        measurement1OkLabel.visibleProperty().bind(viewModel.okTricepsPropertyProperty());

        // Navel / Hip
        measurement2TextField.disableProperty().bind(viewModel.disableNavelPropertyProperty());
        measurement2TextField.disableProperty().bind(viewModel.disableHipPropertyProperty());

        measurement2TextField.textProperty().bindBidirectional(viewModel.navelPropertyProperty(), conventer);//Man
        measurement2TextField.textProperty().bindBidirectional(viewModel.hipPropertyProperty(), conventer);//Woman

        measurement2OkLabel.visibleProperty().bind(viewModel.okNavelPropertyProperty());
        measurement2OkLabel.visibleProperty().bind(viewModel.okHipPropertyProperty());

        // Thigh
        measurement3TextField.disableProperty().bind(viewModel.disableThighPropertyProperty());
        measurement3TextField.textProperty().bindBidirectional(viewModel.thighPropertyProperty(), conventer);
        measurement3OkLabel.visibleProperty().bind(viewModel.okThighPropertyProperty());


        // Button Calculate zbindowany z disable calculate

        btnCalculateBf.disableProperty().bind(viewModel.disableCalculateBfPropertyProperty());

        // przy zmianie ustawienia płci, resetuj pomiary i zmieniaj label
        toggleGender.selectedToggleProperty().addListener(e->{
            if(btnMen.isSelected()){
                measurement1TextField.setText("");
                measurement2TextField.setText("");
                measurement3TextField.setText("");
                lblMeasurement1.setText(bundle.getString("label.man.measurement1"));
                lblMeasurement2.setText(bundle.getString("label.man.measurement2"));
            }
            if(btnWoman.isSelected()){
                measurement1TextField.setText("");
                measurement2TextField.setText("");
                measurement3TextField.setText("");
                lblMeasurement1.setText(bundle.getString("label.woman.measurement1"));
                lblMeasurement2.setText(bundle.getString("label.woman.measurement2"));
            }
        });
    }

    @FXML
    public void initialize(){
        // TextFieldy przyjmujace tylko liczby
        onlnyNumberTextField(ageTextField);
        onlnyNumberTextField(measurement1TextField);
        onlnyNumberTextField(measurement2TextField);
        onlnyNumberTextField(measurement3TextField);

    }
    @FXML
    public void btnReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        ageTextField.setText("0");
        measurement1TextField.setText("0");
        measurement2TextField.setText("0");
        measurement3TextField.setText("0");
        resultBf.setText("____ _");
    }

    @FXML
    public void bmiCalculate(){
        user.getAtributes(viewModel);
        resultBf.setText(String.format("%.2f", model.obliczBF(user)) + "%");
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
