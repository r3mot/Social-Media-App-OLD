package social.Objects;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import social.Profiles.User;

public class Post extends Pane {
    
    private Label name;
    private Label header;
    private Circle icon;
    private TextArea content;
    private Button post;
    private String full_post;

    public Post(){

        setCreatePost();
        setName();
        // setIcon();
        setContent();
        setButton();

        setPrefWidth(450);
        setPrefHeight(330);


    }

    private void setCreatePost(){
        header = new Label();
        header.setPrefWidth(160);
        header.setPrefHeight(40);
        header.setLayoutX(142);
        header.setLayoutY(14);
        header.setStyle("-fx-font-size: 20");
        header.setText("Create A Post");
    }

    private void setName(){
        name = new Label();
        name.setPrefWidth(100);
        name.setPrefHeight(17);
        name.setLayoutX(126);
        name.setLayoutY(101);
        name.setText(User.currentName);
    }

    private void setContent(){
        content = new TextArea();
        content.setPrefWidth(340);
        content.setPrefHeight(85);
        content.setLayoutX(50);
        content.setLayoutY(150);
    }

    private void setButton(){
        post = new Button();
        post.setPrefWidth(340);
        post.setPrefHeight(40);
        post.setLayoutX(55);
        post.setLayoutY(340);
        post.setText("POST");
        post.setOnAction(e -> {
            full_post = content.getText();
            content.setDisable(true);
        });
    }

    public Pane getPost(){
        return this;
    }
    
    public String getContent(){
        return full_post;
    }
}
