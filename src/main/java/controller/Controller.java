package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.User;
import model.ViewModel;

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


        ViewModel viewModel = new ViewModel();
        User user = new User();

        tabController.setViewModel(viewModel);
        tabController.setUser(user);
        tabController.startController();


    }
}
