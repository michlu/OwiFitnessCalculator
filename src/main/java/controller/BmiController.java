package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML public TextField ageTextField, heightTextField, weightTextField;
    @FXML private Label ageOkLabel, heightOkLabel, weightOkLabel;
    @FXML private ToggleGroup toggleGender;
    @FXML private Label bmiLabel;

    private Model model = new Model();
    private ViewModel viewModel;
    private User user;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void startController(){

        onlnyNumberTextField(ageTextField);
        onlnyNumberTextField(heightTextField);
        onlnyNumberTextField(weightTextField);


        StringConverter conventer = new NumberStringConverter();
        if(!(viewModel ==null)) {
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
    }
    @FXML
    public void bmiReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        ageTextField.setText("0");
        heightTextField.setText("0");
        weightTextField.setText("0");
    }

    @FXML
    public void bmiCalculate(){
        user.getAtributes(viewModel);
        System.out.println(user.toString());
        bmiLabel.setText(String.format("%.2f", model.obliczBmi(user)));
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
