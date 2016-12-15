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
 * @sience 2016-12-14
 */
public class TobController implements ControllerHelper {

    @FXML private ToggleButton btnMen, btnWoman;
    @FXML private Button btnCalculate;
    @FXML private TextField heightTextField, weightTextField;
    @FXML private Label heightOkLabel, weightOkLabel;
    @FXML private ToggleGroup toggleGender;

    @FXML private Label labelWq, labelWr, labelSh, labelWqTxt, labelWrTxt1, labelWrTxt2, labelShTxt;

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


        // Wzrost textfield zbindowany z property height

        heightTextField.textProperty().bindBidirectional(viewModel.heightPropertyProperty(), conventer);
        heightTextField.disableProperty().bind(viewModel.okGenderPropertyProperty());
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
        OnlyNumberTextField.onlyNumberTextField(heightTextField);
        OnlyNumberTextField.onlyNumberTextField(weightTextField);
    }

    @FXML
    public void btnReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        heightTextField.setText("0");
        weightTextField.setText("0");

        labelWq.setText("___");
        labelWqTxt.setText("___ ___");
        labelWrTxt1.setText("___ ___");
        labelWrTxt2.setText("___");
        labelShTxt.setText("___ ___ ___");
    }

    @FXML
    public void Calculate(){
        user.getAtributes(viewModel);

        labelWq.setText(String.format("%.0f", model.obliczWQueteleta(user)));
        labelWqTxt.setText(model.wynikTxtWQueteleta(user));

        labelWr.setText(String.format("%.1f", model.obliczWRohrera(user)));
        labelWrTxt1.setText(model.wynikTxtWgKowalewskiej(user));
        labelWrTxt2.setText(model.wynikTxtWgWankegoKolasy(user));

        labelSh.setText(String.format("%.1f", model.obliczWSheldona(user)));
        labelShTxt.setText(model.wynikTxtWgSheldona(user));
    }
}
