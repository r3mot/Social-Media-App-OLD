package social.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.LocalStorage.FriendData;
import social.LocalStorage.ListArray;
import social.Objects.Index;

public class FriendProfileController {

    @FXML private Tab aboutTab;
    @FXML private Label clubs;
    @FXML private Label job;
    @FXML private Label major;
    @FXML private Label name;
    @FXML private AnchorPane postAnchor;
    @FXML private Tab postTab;
    @FXML private AnchorPane profileAnchor;
    @FXML private Circle profilePicture;
    @FXML private AnchorPane scrollAnchor;
    @FXML private Label standing;
    @FXML private Label year;

    private FriendData friends;
    private String username;
    private ListArray data;
    private String full_name;

    public FriendProfileController(String username){
        this.username = username;
        this.data = friends.getData();

        showProfile();
    }

    public void showThisFriend(){
        
    }
    private void showProfile(){

        for(int i = 0; i < data.size(); i++){
            for(int j = 0; j < 9; j++){
                if(data.get(i, 0).equals(this.username)){
                    addFirstName(data.get(i, Index.FIRST_NAME));
                    addLastName(data.get(i, Index.LAST_NAME));
                    addMajor(data.get(i, Index.MAJOR));
                    addStanding(data.get(i, Index.STANDING));
                    addYear(data.get(i, Index.YEAR));
                    addDreamJob(data.get(i, Index.JOB));
                    addIcon(data.get(i, Index.IMAGE));
                    addClubs(data.get(i, Index.CLUBS));
                    break;
                }
            }
        }
    }

    private void addFirstName(String first){
        full_name += first;
    }

    private void addLastName(String last){
        full_name += " " + last;
        name.setText(full_name);
    }

    private void addMajor(String major){
        this.major.setText(major);
    }

    private void addStanding(String standing){
        this.standing.setText(standing);
    }

    private void addYear(String year){
        this.year.setText(year);
    }

    private void addDreamJob(String job){
        this.job.setText(job);
    }

    private void addIcon(String url){
        profilePicture.setFill(new ImagePattern(new Image(url)));
    }

    private void addClubs(String clubs){
        this.clubs.setText(clubs);
    }
}