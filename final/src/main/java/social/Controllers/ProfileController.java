package social.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.Database.Database;
import social.LocalStorage.FriendData;
import social.LocalStorage.ListArray;
import social.LocalStorage.UserData;
import social.Objects.Feed;
import social.Objects.Friend;
import social.Objects.Index;
import social.Profiles.User;

public class ProfileController implements Initializable {

    @FXML private AnchorPane profileAnchor;
    @FXML private AnchorPane friendAnchor;
    @FXML private AnchorPane scrollAnchor;
    @FXML private AnchorPane postAnchor;
    @FXML private ScrollPane scrollPane;
    @FXML private Pane friend1;
    @FXML private Pane friend2;
    @FXML private Pane friend3;
    @FXML private Pane friend4;
    @FXML private Pane friendsPane;
    @FXML private Circle profilePicture;
    @FXML private Circle friend_image_one;
    @FXML private Circle friend_image_two;
    @FXML private Circle friend_image_three;
    @FXML private Circle friend_image_four;
    @FXML private Label friendName1;
    @FXML private Label friendName2;
    @FXML private Label friendName3;
    @FXML private Label friendName4;
    @FXML private Label job;
    @FXML private Label major;
    @FXML private Label first_name;
    @FXML private Label last_name;
    @FXML private Label standing;
    @FXML private Label year;
    @FXML private Label clubLabel;

    private Database db;
    private UserData userData;
    private FriendData friendData;
    private ArrayList<String> profile_data;
    private ListArray friend_profile_data;
    private ListArray feedData;

    private int currentY;


    /**
     * Start of Profile Controller
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        db = new Database();
       userData = new UserData();
       friendData = new FriendData();
       
        try {
            feedData = db.getUserFeed();
        } catch (SQLException e) {}


        profile_data = UserData.getData();
        friend_profile_data = friendData.getData();

    
        addFeed(User.currentUser, User.currentURL);
        showMyFriends();
        displayUserProfile();
    }

    /**
     * Shows all of the current users posts
     */
    private void addFeed(String username, String url){

        // String url = "file:src/main/resources/social/Images/Headhots/1495538_10202726368386681_55861568_n.jpg";

        scrollAnchor.getChildren().clear();



        Feed feed;
        currentY = 0;

        for(int i = feedData.size()-1; i >= 0; i--){
            if(feedData.nextNull(i)){
                continue;
            }

            String name = feedData.get(i, Index.POST_NAME);
            String message = feedData.get(i, Index.POST_CONTENT);
            String user_url = feedData.get(i, Index.POST_URL);
            String date = feedData.get(i, Index.POST_DATE);

            feed = new Feed(name, message, user_url, date, currentY);
            scrollAnchor.getChildren().addAll(feed);

            currentY += 165;
        }

    }

    /**
     * 
     * @param event
     * @throws FileNotFoundException
     * 
     * Updates screen to reflect user going to friends profile
     * Updates screen with "friend" data
     */
    @FXML
    public void goToProfile(MouseEvent event) throws FileNotFoundException  {

        Circle circle = (Circle) event.getSource();
        circle.setOnMouseClicked(e ->{
            displayFriendProfile(circle.getId());
        });
        
    }


    /**
     * Collects friend data from local storage
     * Displays friends list on user profile
     * Adds "Friend" object with given params
     */
    private void showMyFriends() {


        int[] xLayout = {95,95,414, 414};
        int[] yLayout = {94,297,94,297};

        Friend friend;
        int count = 0;
        for(int i = 0; i < 4; i++){

            String username = friend_profile_data.get(i, Index.USERNAME);
            String name = friend_profile_data.get(i, Index.FIRST_NAME);
            String url = friend_profile_data.get(i, Index.IMAGE);
            int x = xLayout[count];
            int y = yLayout[count];

            friend = new Friend(name, username, url, x, y);
            friend.getClicked().setOnMouseClicked(e ->{
                clicked(e);
            });

            friendAnchor.getChildren().addAll(friend);
            count++;
        }


    }

    /**
     * 
     * @param event clicked friend profile picture
     * 
     * Clear fields and display the friends profile
     */
    private void clicked(MouseEvent event){
        Circle friend_icon = (Circle) event.getSource();
        displayFriendProfile(friend_icon.getId());
    }

    /**
     * 
     * @param username of friend clicked
     * 
     * When a user clicks a friends profile picture,
     * load that friends profile data from local storage
     * Update the page to reflect the changes
     */
    private void displayFriendProfile(String username){

        first_name.setText("");
        currentY = 0;

        for(int i = 0; i < friend_profile_data.size(); i++){
            for(int j = 0; j < 9; j++){
                if(friend_profile_data.get(i, 0).equals(username)){
                    addFirstName(friend_profile_data.get(i, Index.FIRST_NAME));
                    addLastName(friend_profile_data.get(i, Index.LAST_NAME));
                    addMajor(friend_profile_data.get(i, Index.MAJOR));
                    addStanding(friend_profile_data.get(i, Index.STANDING));
                    addYear(friend_profile_data.get(i, Index.YEAR));
                    addDreamJob(friend_profile_data.get(i, Index.JOB));
                    addIcon(friend_profile_data.get(i, Index.IMAGE));
                    // addClubs(friend_profile_data.get(i, Index.CLUBS));
                    addFeed(username, friend_profile_data.get(i, Index.IMAGE));
                    break;
                }
            }
        }

    }

    /**
     * Collects the current users data from local storage
     * Displays "About" data on the users profile
     */
    private void displayUserProfile(){

        addFirstName(profile_data.get(Index.FIRST_NAME));
        addLastName(profile_data.get(Index.LAST_NAME));
        addMajor(profile_data.get(Index.MAJOR));
        addStanding(profile_data.get(Index.STANDING));
        addYear(profile_data.get(Index.YEAR));
        addDreamJob(profile_data.get(Index.JOB));
        addIcon(profile_data.get(Index.IMAGE));
        // addClubs(profile_data.get(Index.CLUBS));

    }

    /**
     * 
     * @param all_clubs the users is a part of
     * 
     * Collects all the clubs a user is a part of 
     * from local storage
     * 
     * Sort the clubs and display on user profile
     */
    private void addClubs(String all_clubs){
        all_clubs.substring(6);
        String[] clbs = all_clubs.split(",");
        List<String> c = Arrays.asList(clbs);
        Collections.sort(c);
        String completed = "";
        for(String w : c){
            completed += w + ",";
        }

        completed.substring(0, completed.length()-6);
        clubLabel.setText(completed);
        clubLabel.setId("");
    }

    /**
     * 
     * @param URL of profile picture
     * 
     * Sets the current users profile picture
     */
    private void addIcon(String URL){
        Image image = new Image(URL);
        profilePicture.setFill(new ImagePattern(image));
        User.setCurrentIcon(URL);
    }

    /**
     * 
     * @param first name
     * 
     * Collects first name of user for the display name
     */
    private void addFirstName(String first){
        first_name.setText(first);
        first_name.setStyle("-fx-font-weight:bold");
    }

    /**
     * 
     * @param last name
     * 
     * Collects last name of user for the display name
     */
    private void addLastName(String last){
        last_name.setText(last);
        last_name.setStyle("-fx-font-weight:bold");
    }

    /**
     * 
     * @param major
     * 
     * Sets the current user "major" on profile
     */
    private void addMajor(String major){
        this.major.setText(major);
    }
    
    /**
     * 
     * @param standing
     * 
     * Sets the current user "standing" on profile
     */
    private void addStanding(String standing){
        this.standing.setText(standing);
    }

    /**
     * 
     * @param year
     * 
     * Sets the current user "year" on profile
     */
    private void addYear(String year){
        this.year.setText(year);
    }

    /**
     * 
     * @param job
     * 
     * Sets the current user "dream job" on profile
     */
    private void addDreamJob(String job){
        this.job.setText(job);
    }


}
