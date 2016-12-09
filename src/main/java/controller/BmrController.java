package controller;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Model;
import model.User;
import model.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Michlu
 * @sience 2016-12-08
 */
public class BmrController {

    @FXML private ToggleButton btnMen, btnWoman;
    @FXML private Button btnCalculate;
    @FXML private TextField ageTextField, heightTextField, weightTextField;
    @FXML private Label ageOkLabel, heightOkLabel, weightOkLabel;
    @FXML private ToggleGroup toggleGender;

    private Model model = new Model();
    private ViewModel viewModel;
    private User user;

    ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
    @FXML private ComboBox<String> comboBox;
    @FXML private Label labelResultHarris, labelResultMifflin, labelResultTMR1, labelResultTMR2;


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

        // ComboBox - aktywnosc fizyczna
        List<String> activityArrayList = new ArrayList<>();
        activityArrayList.add(bundle.getString("bmr.activity.level0"));
        activityArrayList.add(bundle.getString("bmr.activity.level1"));
        activityArrayList.add(bundle.getString("bmr.activity.level2"));
        activityArrayList.add(bundle.getString("bmr.activity.level3"));
        activityArrayList.add(bundle.getString("bmr.activity.level4"));
        activityArrayList.add(bundle.getString("bmr.activity.level5"));
        comboBox.getItems().addAll(activityArrayList);
        comboBox.getSelectionModel().select(1);

        viewModel.setActivityIndexProperty(comboBox.getSelectionModel().getSelectedIndex());
        comboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->
                viewModel.setActivityIndexProperty(newValue.intValue()));
        }


    @FXML
    public void initialize(){


    }

    @FXML
    public void bmrCalculate(){
        user.getAtributes(viewModel);
        labelResultMifflin.setText(String.format("%.0f", model.obliczPpmMifflin(user)) + " kcal");
        labelResultHarris.setText(String.format("%.0f", model.obliczPpmHarrisBenedict(user)) + " kcal");
        labelResultTMR1.setText(String.format("%.0f", model.obliczPpmMifflin(user)*user.getActivity()) + " kcal");
        labelResultTMR2.setText(String.format("%.0f", model.obliczPpmHarrisBenedict(user)*user.getActivity()) + " kcal");
    }

    @FXML
    public void btnReset(){
        btnMen.setSelected(false);
        btnWoman.setSelected(false);
        ageTextField.setText("0");
        heightTextField.setText("0");
        weightTextField.setText("0");
        labelResultHarris.setText("");
        labelResultMifflin.setText("");
        labelResultTMR1.setText("TMR");
        labelResultTMR2.setText("TMR");
    }
}
