package social.Controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import social.Database.Database;
import social.LocalStorage.ListArray;
import social.LocalStorage.UserData;
import social.Objects.Feed;
import social.Objects.Index;
import social.Profiles.User;

import javafx.event.EventHandler;

public class FeedController implements Initializable {

    // private final String ADD_POST = "INSERT INTO POSTS(USERNAME, CONTENT, DATE) VALUES (?,?,?)";

    private final String corey = "file:/C:/Users/micha/OneDrive/Pictures/ranom/corey.jpg";

    @FXML private AnchorPane anchor;
    @FXML private AnchorPane feedPane;
    @FXML private Pane popup;
    @FXML private Button newPost;

    private Database db;
    private UserData userData;
    private int currentY;
    private String content;
    private Feed feed;
    private boolean maxMet;

    private ListArray post_data;
    private int numLikes;

    @FXML
    void createNewPost(ActionEvent event) throws IOException, SQLException {

 
        displayPopup();

        checkForEmoji();

        UserData.addPost(content, getDate()); 
    

        post_data.add(User.currentName,0, Index.POST_NAME);
        post_data.add(content, 0, Index.POST_CONTENT);
        post_data.add(getDate(), 0, Index.POST_DATE);
        post_data.add(corey, 0, Index.POST_URL);

        // System.out.println(User.currentName + " " + "is adding post with content: " + content);

        initialize(null, null);

    }

    @FXML
    void searchFeed(KeyEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        db = new Database();
        try {
            post_data = db.getFeed();
        } catch (SQLException e) {}

        userData = new UserData();

        feedPane.getChildren().clear();
        showFeed();
    }


    private void showFeed(){

        currentY = 0;

        String name;
        String content;
        String date;
        String url;
        String username;

        int count = 0;
        for(int i = post_data.size()-1; i >= 0; i--){

            if(post_data.nextNull(i)){
                count++;
                continue;
            }



            username = post_data.get(i, Index.POST_USERNAME);
            name = post_data.get(i, Index.POST_NAME);
            content = post_data.get(i, Index.POST_CONTENT);
            date = post_data.get(i, Index.POST_DATE);
            url = post_data.get(i, Index.POST_URL);

            feed = new Feed(name, content, url, date, currentY);


            feedPane.getChildren().addAll(feed);
            currentY += 252;
        }
        
    }


    private void updateLikes(ActionEvent event) throws SQLException{

        // Button likeClicked = (Button) feed.getLikeID();
        // String username = likeClicked.getId();
        // db.updateLikeCount(username);

        // int numberLikes = db.getLikeCount(username);

        // feed.updateLikes(numberLikes);

        // initialize(null, null);

    }

    private String getDate(){
        String format = "MM-dd-yyy";
        SimpleDateFormat simpledate = new SimpleDateFormat(format);
        return simpledate.format(new Date());
    }

    private void displayPopup(){

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Create A Post");
        alert.setHeaderText("Share Your Thoughts!");

        Label maxCharsMet = new Label();
        maxCharsMet.setText("Max Characters is 120!");
        maxCharsMet.setTextFill(Color.RED);
        maxCharsMet.setVisible(false);

        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // add max character event handler
        textArea.addEventFilter(KeyEvent.KEY_TYPED, maxChars(120));

        if(maxMet){
            maxCharsMet.setVisible(true);
        }
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.getDialogPane().setExpanded(true);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            content = textArea.getText();
            return;
        }

    }

    private void checkForEmoji(){
        int[] surrogates = {0xD83D, 0xDC7D};
        String alienEmojiString = new String(surrogates, 0, surrogates.length);

        String emo = "/alien";
        String replaced="";
        if(content.contains(emo)){
             replaced = content.replace(emo, alienEmojiString);
             content = replaced;
        }
        
    }

    private EventHandler<KeyEvent> maxChars(final Integer i){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent k){
                TextArea tx = (TextArea) k.getSource();
                if(tx.getText().length() >= i){
                    maxMet = true;
                    k.consume();
                }
            }
        };
    }
}