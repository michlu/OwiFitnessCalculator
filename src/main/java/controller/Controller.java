package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.User;
import model.ViewModel;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class Controller {

    ViewModel viewModel = new ViewModel();
    User user = new User();

    @FXML private BorderPane menuBorderPane;
    @FXML private VBox bmiMain, aibwMain, bmrMain, bfMain, whrMain, tobMain;


    @FXML
    public void initialize(){

        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/bmi_gui.fxml"));
        try {
            loader.setResources(bundle);
            bmiMain = loader.load();
            BmiController bmiController = loader.getController();
            // przekazanie do controllera ViewModel i User aby wszystkie kontrolery dzialaly na tych samych danych
            bmiController.setViewModel(viewModel);
            bmiController.setUser(user);
            bmiController.start();
            menuBorderPane.setCenter(bmiMain);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(this.getClass().getResource("/fxml/aibw_gui.fxml"));
        try {
            loader.setResources(bundle);
            aibwMain = loader.load();
            AIBWController aibwController = loader.getController();
            // przekazanie do controllera ViewModel i User aby wszystkie kontrolery dzialaly na tych samych danych
            aibwController.setViewModel(viewModel);
            aibwController.setUser(user);
            aibwController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(this.getClass().getResource("/fxml/bmr_gui.fxml"));
        try {
            loader.setResources(bundle);
            bmrMain = loader.load();
            BmrController bmrController = loader.getController();
            // przekazanie do controllera ViewModel i User aby wszystkie kontrolery dzialaly na tych samych danych
            bmrController.setViewModel(viewModel);
            bmrController.setUser(user);
            bmrController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(this.getClass().getResource("/fxml/bf_gui.fxml"));
        try {
            loader.setResources(bundle);
            bfMain = loader.load();
            BfController bfController = loader.getController();
            // przekazanie do controllera ViewModel i User aby wszystkie kontrolery dzialaly na tych samych danych
            bfController.setViewModel(viewModel);
            bfController.setUser(user);
            bfController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(this.getClass().getResource("/fxml/whr_gui.fxml"));
        try {
            loader.setResources(bundle);
            whrMain = loader.load();
            WhrController whrController = loader.getController();
            // przekazanie do controllera ViewModel i User aby wszystkie kontrolery dzialaly na tych samych danych
            whrController.setViewModel(viewModel);
            whrController.setUser(user);
            whrController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(this.getClass().getResource("/fxml/tob_gui.fxml"));
        try {
            loader.setResources(bundle);
            tobMain = loader.load();
            TobController tobController = loader.getController();
            // przekazanie do controllera ViewModel i User aby wszystkie kontrolery dzialaly na tych samych danych
            tobController.setViewModel(viewModel);
            tobController.setUser(user);
            tobController.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openBMI(){
        menuBorderPane.setCenter(bmiMain);
    }

    @FXML
    public void openAIBW(){
        menuBorderPane.setCenter(aibwMain);
    }

    @FXML
    public void openBMR(){
        menuBorderPane.setCenter(bmrMain);
    }

    @FXML
    public void openBF(){
        menuBorderPane.setCenter(bfMain);
    }

    @FXML
    public void openWHR(){
        menuBorderPane.setCenter(whrMain);
    }

    @FXML
    public void openTob(){
        menuBorderPane.setCenter(tobMain);
    }
}
