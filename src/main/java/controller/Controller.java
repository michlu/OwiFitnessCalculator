package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.User;
import model.ViewModel;

import java.io.IOException;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class Controller {

    @FXML private VBox vboxMenu;
    @FXML private VBox vboxBMI;
    @FXML private BmiController tabController;


    @FXML
    public void initialize(){


//        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/bmi_gui.fxml"));
//        VBox vboxBMI = loader.load();
//
//        BmiController bmiController = new BmiController();
//        loader.setController(bmiController);

        ViewModel viewModel = new ViewModel();
        User user = new User();

        tabController.setViewModel(viewModel);
        tabController.setUser(user);
        tabController.startController();


    }
}
