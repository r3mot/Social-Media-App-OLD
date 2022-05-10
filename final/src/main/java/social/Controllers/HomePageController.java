package social.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.App;
import social.Database.DB;
import social.Database.Database;
import social.Profiles.User;


public class HomePageController extends Database implements Initializable {

    @FXML private Button clubsButton;
    @FXML private Button eventsButton;
    @FXML private Button feedButton;
    @FXML private Button friendsButton;
    @FXML private Button profileButton;
    @FXML private Button logOut;
    @FXML private Label nameLabel;
    @FXML private Pane leftPane;
    @FXML private Pane rightPane;
    @FXML private Pane blurPane;
    @FXML private TabPane tabPane;
    @FXML private AnchorPane anchor;
    @FXML private Pane contentPane;
    @FXML private Label upperName;

    private Database db;
    private Pane profilePane;
    private Pane feedPane;
    private Pane clubPane;
    private Pane friendPane;


    /**
     * 
     * @param event
     * @throws IOException
     * 
     * User has logged out
     * Return to login screen 
     */
    @FXML void logOutClicked(ActionEvent event) throws IOException{
        App.setRoot("NewLogin");
    }

    /**
     * 
     * @param event
     * @throws Exception
     * 
     * Switch between various pages on Home Page
     * i.e. Profile, Feed, Clubs, etc.
     */
    @FXML void goTo(ActionEvent event) throws Exception {

        Button button = (Button) event.getSource();
        String source = button.getId();

        switch(source){

            case "profile" : showProfile();
            break;
            
            case "feed" : showFeed();
            break;

            case "clubs" : showClubs();
            break;

            case "friends" : showFriends();

        }

    }

    /**
     * Changes the visibility of the Clubs fxml file
     * User selected "Clubs" from side panel
     */
    private void showClubs(){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(clubPane);

    }

    /**
     * Changes the visibility of the Feed fxml file
     * User selected "Feed" from side panel
     */
    private void showFeed(){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(feedPane);
    }

    /**
     * Changes the visibility of the Profile fxml file
     * User selected "Profile" from side panel
     */
    private void showProfile(){
        if(contentPane.getChildren() != null){
            contentPane.getChildren().clear();
        }
        contentPane.getChildren().add(profilePane);
    }

      /**
     * Changes the visibility of the Friends fxml file
     * User selected "Friends" from side panel
     */
    private void showFriends(){
        if(contentPane.getChildren() != null){
            contentPane.getChildren().clear();
        }
        contentPane.getChildren().add(friendPane);
    }
    
    /**
     * Start of Home Page Controller
     */
    @Override public void initialize(URL arg0, ResourceBundle arg1) {

        db = new Database();
        // DB datdb = new DB();
       
        // try {
        //     datdb.createPostsTable();
        // } catch (ClassNotFoundException e1) {
        //     // TODO Auto-generated catch block
        //     e1.printStackTrace();
        // }

        

        /* Load files to prepare scene */
        try {
   
            profilePane = FXMLLoader.load(App.class.getResource("TempProfile.fxml"));
            feedPane = FXMLLoader.load(App.class.getResource("Feed.fxml"));
            clubPane = FXMLLoader.load(App.class.getResource("Clubs.fxml"));
            friendPane = FXMLLoader.load(App.class.getResource("Friends.fxml"));
        
        } catch (IOException e) {
            System.out.println("Error Caused by ( " + e.getCause().getLocalizedMessage() + ")");
        }    
        
        showProfile();

    }
}