import controller.BmiController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import model.ViewModel;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class App extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("en"));

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/gui.fxml"));
        Pane pane = loader.load();


        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/themeBlue.css").toExternalForm());
        Stage stage = new Stage();
        stage.setMaxWidth(305);
        stage.setScene(scene);
        stage.setTitle("OwiFitnessCalculator");
        stage.setResizable(false);
        stage.show();

    }
}
