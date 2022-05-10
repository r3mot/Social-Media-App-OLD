package social;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import social.Profiles.User;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

  
        scene = new Scene(loadFXML("NewLogin"), 1000, 800  );
 

        String css = this.getClass().getResource("/social/Styling/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}