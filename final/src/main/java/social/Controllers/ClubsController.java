package social.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import social.LocalStorage.UserData;
import social.Objects.Club;
import social.Objects.Clubs;

public class ClubsController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane scrollAnchor;

    @FXML
    private ScrollPane scrollPane;

    private final String accountingURL = "file:src/main/resources/social/Images/Clubs/accounting_society.jpg";
    private final String artsCollectiveURL = "file:src/main/resources/social/Images/Clubs/arts_collective.jpg";
    private final String bsuURL = "file:src/main/resources/social/Images/Clubs/black_student_union.jpeg";
    private final String chemistryURL = "file:src/main/resources/social/Images/Clubs/chemistry.jpg";
    private final String cyberURL = "file:src/main/resources/social/Images/Clubs/cyber_security_club.jpg";
    private final String esportsURL = "file:src/main/resources/social/Images/Clubs/esports_association.jpg";
    private final String historyClubURL = "file:src/main/resources/social/Images/Clubs/history_club.jpg";
    private final String itURL = "file:src/main/resources/social/Images/Clubs/it_society.jpg";
    private final String koreanURL = "file:src/main/resources/social/Images/Clubs/korean_club.jpg";
    private final String latinoURL = "file:src/main/resources/social/Images/Clubs/latino_student_association.jpg";
    private final String logisticsURL = "file:src/main/resources/social/Images/Clubs/I.png";
    private final String mathClubURL = "file:src/main/resources/social/Images/Clubs/math_club.jpg";
    private final String lawURL = "file:src/main/resources/social/Images/Clubs/pre_law.jpg";

    private Clubs c = new Clubs();
    private Club club;
    private List<String> urls;
    private String[][] clubs;
    private int currentX;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clubs = c.getClubs();
        urls = new ArrayList<>();

        setURLS();

        for(int i = 0; i < urls.size(); i++){

            String name = clubs[i][0];
            String message = clubs[i][1];
            String url = urls.get(i);
            club = new Club(name, message, url, currentX);
            club.getJoin().setOnAction(event -> {
                try {
                    clicked(event);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            
            scrollAnchor.getChildren().add(club.getClub());
            currentX += 107;
        
        }

        
    }

    public void clicked(ActionEvent event) throws SQLException{

        Button join = (Button) event.getSource();
        UserData data = new UserData();
        data.addClub(join.getId());
       
    }

    private void setURLS(){
        urls.add(accountingURL);
        urls.add(artsCollectiveURL);
        urls.add(bsuURL);
        urls.add(chemistryURL);
        urls.add(cyberURL);
        urls.add(esportsURL); 
        urls.add(historyClubURL);
        urls.add(itURL); 
        urls.add(koreanURL);  
        urls.add(latinoURL); 
        urls.add(logisticsURL); 
        urls.add(mathClubURL); 
        urls.add(lawURL); 

    }

}