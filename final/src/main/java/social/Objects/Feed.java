package social.Objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import social.Profiles.User;

public class Feed extends Pane {

    private Label timestamp;
    private Label name;
    private Circle icon;
    private TextArea text;
    private Button like;
    private Label unchanged_like;
    private Label like_count;

    private String fullname;
    private String message;
    private String url;
    private String date;
    
    public Feed(String fullname, String message, String url, String date, int currentY){
        this.fullname = fullname;
        this.url = url;
        this.message = message;
        this.date = date;

        text = new TextArea();
        timestamp = new Label();
        name = new Label();
        icon = new Circle();

        prefWidth(665);
        prefHeight(250);
        setLayoutY(currentY);

        setIcon();
        setName();
        setTime();
        setText();
        setImage();
        // setButton();
        // setLikes();

        getChildren().addAll(timestamp, name, icon, text);
    }

    public Pane getPane(){
        return this;
    }

    private void setImage(){

    }

    // private void setLikes(){
    //     unchanged_like = new Label();
    //     unchanged_like.setPrefWidth(32);
    //     unchanged_like.setPrefHeight(17);
    //     unchanged_like.setLayoutX(83);
    //     unchanged_like.setLayoutY(61);
    //     unchanged_like.setText("Likes: ");

    //     like_count = new Label();
    //     like_count.setPrefWidth(28);
    //     like_count.setPrefHeight(17);
    //     like_count.setLayoutX(118);
    //     like_count.setLayoutY(61);
    //     like_count.setText("0");
        
    // }
    
    // private void setButton(){
    //     like = new Button();
    //     like.setLayoutX(592);
    //     like.setLayoutY(47);
    //     like.setPrefWidth(50);
    //     like.setPrefHeight(20);
    //     like.setText("LIKE");
    //     like.setId(User.currentUser);

    // }

    private void setText(){
        text.setPrefWidth(622);
        text.setPrefHeight(74);
        text.setLayoutX(23);
        text.setLayoutY(85);
        text.setStyle("-fx-font-size: 20; -fx-font-family: OpenSansEmoji;-fx-font-size: 20;");
        text.setText(message);
        text.setDisable(true);
        text.setWrapText(true);
    }

    private void setTime(){
        timestamp.setPrefWidth(160);
        timestamp.setPrefHeight(17);
        timestamp.setStyle("-fx-font-size: 12");
        timestamp.setLayoutX(83);
        timestamp.setLayoutY(40);
        timestamp.setText(this.date);
    }

    private void setName(){
        name.setPrefWidth(126);
        name.setPrefHeight(17);
        name.setStyle("-fx-font-size: 15");
        name.setLayoutX(83);
        name.setLayoutY(19);
        name.setText(this.fullname);
    }

    private void setIcon(){
        icon.setRadius(27);
        icon.setLayoutX(45);
        icon.setLayoutY(47);
        icon.setFill(new ImagePattern(new Image(this.url)));
    }
    
    // public void updateLikes(int number){
    //     like_count.setText(String.valueOf(number));
    // }

    // public Button getLikeID(){
    //     return this.like;
    // }
}
