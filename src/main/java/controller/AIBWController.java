package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Model;
import model.User;
import model.ViewModel;

/**
 * @author Michlu
 * @sience 2016-12-03
 */
public class AIBWController {
    @FXML
    private ToggleButton btnMen, btnWoman;
    @FXML private Button btnCalculate;
    @FXML private TextField ageTextField, heightTextField;
    @FXML private Label ageOkLabel, heightOkLabel;
    @FXML private ToggleGroup toggleGender;
    @FXML private Label labelWN, labelWB, labelWBB, labelWP, labelWL, labelATUNZ, labelS;

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

        // Button Calculate zbndowany z disable calculate

        btnCalculate.disableProperty().bind(viewModel.disableWeightPropertyProperty());
    }

    @FXML
    public void initialize(){
        // TextFieldy przyjmujace tylko liczby
        onlnyNumberTextField(ageTextField);
        onlnyNumberTextField(heightTextField);
    }

    @FXML
    public void aibwCalculate(){
        user.getAtributes(viewModel);
        System.out.println(user);
        labelWN.setText(String.format("%.2f", model.wagaNalezna(user)) + " kg");
        labelWB.setText(String.format("%.2f", model.obliczWzorBroca(user)) + " kg");
        labelWBB.setText(String.format("%.2f", model.obliczWzorBrocaBrugsha(user)) + " kg");
        labelWP.setText(String.format("%.2f", model.obliczWzorPottona(user)) + " kg");
        labelWL.setText(String.format("%.2f", model.obliczWzorLorenza(user)) + " kg");
        labelATUNZ.setText(String.format("%.2f", model.obliczWzorATUnZ(user)) + " kg");
        labelS.setText(String.format("%.2f", (model.wagaNalezna(user)+model.obliczWzorBroca(user)+model.obliczWzorBrocaBrugsha(user)+model.obliczWzorPottona(user)+model.obliczWzorLorenza(user)+model.obliczWzorATUnZ(user))/6) + " kg");

    }

    @FXML
    public void btnReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        ageTextField.setText("0");
        heightTextField.setText("0");
        labelWN.setText("");
        labelWB.setText("");
        labelWBB.setText("");
        labelWP.setText("");
        labelWL.setText("");
        labelATUNZ.setText("");
        labelS.setText("");
    }

    public void onlnyNumberTextField(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                textField.setText(oldValue);
            }
        });
    }
}
