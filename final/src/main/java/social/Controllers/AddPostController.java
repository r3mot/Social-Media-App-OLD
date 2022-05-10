package social.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AddPostController {

    @FXML
    private TextArea content;

    @FXML
    private Button post;

    private boolean fired = false;

    @FXML
    void postToFeed(ActionEvent event) {
        fired = true;
    }

    public void hide(){
        post.getScene().getWindow().hide();
    }

    public boolean wasFired(){
        return this.fired;
    }

}
