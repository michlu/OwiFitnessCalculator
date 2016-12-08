package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.User;
import model.ViewModel;

import java.io.IOException;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class Controller {

    ViewModel viewModel = new ViewModel();
    User user = new User();

    @FXML private VBox vboxMenu;
    @FXML private VBox vboxBMI;
    @FXML private BorderPane menuBorderPane;
    @FXML private VBox bmiMain, aibwMain;


    @FXML
    public void initialize(){

        System.out.println("Controller-Vierwmodel = " + viewModel.toString());
        System.out.println("Controller-User = " + user.hashCode());

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/bmi_gui.fxml"));
        try {
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
            aibwMain = loader.load();
            AIBWController aibwController = loader.getController();
            // przekazanie do controllera ViewModel i User aby wszystkie kontrolery dzialaly na tych samych danych
            aibwController.setViewModel(viewModel);
            aibwController.setUser(user);
            aibwController.start();
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
}
