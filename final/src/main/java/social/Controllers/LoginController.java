package social.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import social.App;
import social.Database.Database;
import social.Profiles.User;

public class LoginController extends Database implements Initializable {

    @FXML private ImageView addUserImage;
    @FXML private Pane addUserPane;
    @FXML private Label createLabel;
    @FXML private Label errorLabel;
    @FXML private Hyperlink forgotPassword;
    @FXML private ImageView loginImage;
    @FXML private Button loginReady;
    @FXML private PasswordField password;
    @FXML private Button signIn;
    @FXML private Button signUp;
    @FXML private AnchorPane transitionPane;
    @FXML private TextField username;
    @FXML private Label welcomeLabel;

    private Pane newUserPane;

    private final int LEFT_X = 0;
    private final int RIGHT_X = 500;
    

    @FXML void loginReadyClicked(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        process(login(username.getText(), password.getText()));
    }

    @FXML void signInClicked(ActionEvent event) {
        slideLeft();
    }

    @FXML void signUpClicked(ActionEvent event) {
        slideRight();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // DB database = new DB();
        // try {
        //     database.createInitialTable();
        //     database.createPostsTable();
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        // }
   
        try {
            // Add secondary fxml file on launch
            newUserPane = FXMLLoader.load(App.class.getResource("NewUser.fxml"));
            addUserPane.getChildren().add(newUserPane);

        }catch (IOException e){
            e.printStackTrace();
        }

        addUserPane.setVisible(false);
        addUserImage.setVisible(false);
        signIn.setVisible(false);
        signUp.setVisible(true);
        createLabel.setVisible(false);
        
    }

    private void switchScene() throws IOException{
        User.setCurrentUser(username.getText());
        App.setRoot("Home");
    }

    private void showError(){
        errorLabel.setText("Incorrect Username or Password");
    }

    private void process(boolean success) throws IOException{
        if(success){
            User.setCurrentUser(username.getText());
            switchScene();
        }else{
            showError();
        }
    }

    private void slideRight(){

        loginVisible(false);
        readyRight();

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(transitionPane);
        slide.setToX(RIGHT_X);
        slide.play();
        slide.setOnFinished((e->{
          addUserPane.setVisible(true);
        }));
    }

    private void slideLeft(){

        addUserPane.setVisible(false);
    

        addUserPane.setVisible(false);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(transitionPane);
        slide.setToX(LEFT_X);
        slide.play();
        slide.setOnFinished((e->{
           loginVisible(true);
        }));
    }

 
    private void loginVisible(boolean state){
        username.setVisible(state);
        password.setVisible(state);
        forgotPassword.setVisible(state);
        loginReady.setVisible(state);
    }

    private void readyRight(){
        loginImage.setVisible(false);
        addUserImage.setVisible(true);
        signIn.setVisible(true);
        signUp.setVisible(false);
        createLabel.setVisible(true);
    }

}