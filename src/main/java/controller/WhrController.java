package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.StringConverter;
import javafx.util.converter.CurrencyStringConverter;
import javafx.util.converter.NumberStringConverter;
import javafx.util.converter.PercentageStringConverter;
import model.Model;
import model.User;
import model.ViewModel;

import java.util.ResourceBundle;


/**
 * @author Michlu
 * @sience 2016-12-13
 */
public class WhrController {

    @FXML private ToggleButton btnMen, btnWoman;
    @FXML private TextField ageTextField;
    @FXML private Label ageOkLabel;
    @FXML private ToggleGroup toggleGender;

    @FXML private Button btnCalculateWhr;
    @FXML private Label waistOkLabel, hipOkLabel, resultWhr, labelWhrBmi;
    @FXML private TextField waistTextField, hipTextField;
    @FXML private TextFlow textFlow;

    private Text text = new Text();

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
        NumberStringConverter converterN = new PercentageStringConverter();

        // ToggleGroup gender zbindowany do
        btnMen.selectedProperty().bindBidirectional(viewModel.genderManPropertyProperty());
        btnWoman.selectedProperty().bindBidirectional(viewModel.genderWomanPropertyProperty());


        // Wiek textfield zbindowany z property age

        ageTextField.textProperty().bindBidirectional(viewModel.agePropertyProperty(), conventer);
        ageTextField.disableProperty().bind(viewModel.okGenderPropertyProperty());
        ageOkLabel.visibleProperty().bind(viewModel.okAgePropertyProperty());

        // waist
        waistTextField.disableProperty().bind(viewModel.disableWaistCircumferencePropertyProperty());
        waistTextField.textProperty().bindBidirectional(viewModel.waistCircumferencePropertyProperty(), conventer);//Man
        waistOkLabel.visibleProperty().bind(viewModel.okWaistCircumferencePropertyProperty());


        // hip
        hipTextField.disableProperty().bind(viewModel.disableHipCircumferencePropertyProperty());
        hipTextField.textProperty().bindBidirectional(viewModel.hipCircumferencePropertyProperty(), conventer);
        hipOkLabel.visibleProperty().bind(viewModel.okHipCircumferencePropertyProperty());

        // Button Calculate zbindowany z disable calculate

        btnCalculateWhr.disableProperty().bind(viewModel.disableCalculateWhrPropertyProperty());

        labelWhrBmi.disableProperty().bind(viewModel.disableBmiPropertyProperty());
        labelWhrBmi.textProperty().bind(viewModel.bmiPropertyProperty().asString("%.1f" + " BMI"));
    }

    @FXML
    public void initialize(){
        // TextFieldy przyjmujace tylko liczby
        onlnyNumberTextField(ageTextField);
        onlnyNumberTextField(hipTextField);
        onlnyNumberTextField(waistTextField);

        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setPadding(new Insets(0,10,0,10));
    }
    @FXML
    public void btnReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        ageTextField.setText("0");
        hipTextField.setText("");
        waistTextField.setText("");
        if(!textFlow.getChildren().isEmpty())
            textFlow.getChildren().remove(text);
        resultWhr.setText("___");
    }

    @FXML
    public void Calculate(){
        user.getAtributes(viewModel);
        resultWhr.setText(String.format("%.2f", model.obliczWHR(user)));
        if(!textFlow.getChildren().isEmpty())
            textFlow.getChildren().remove(text);

        text.setText(model.wynikTxtWhr(user));
        textFlow.getChildren().add(text);
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
