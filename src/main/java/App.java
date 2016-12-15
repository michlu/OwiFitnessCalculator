import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.Locale;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
//        Locale.setDefault(new Locale("pl"));

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/gui.fxml"));
        Pane pane = loader.load();

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/css/themeBlue.css").toExternalForm());
        Stage stage = new Stage();
        stage.setMaxWidth(305);
        stage.setScene(scene);
        stage.setTitle("OwiFitnessCalculator");
        // Ikona po przerobkach: http://www.freeiconspng.com/free-images/fitness-icon-png-280
        stage.getIcons().add(new Image("/graphics/icon_bmi.png"));
        stage.setResizable(false);
        stage.show();
    }
}
