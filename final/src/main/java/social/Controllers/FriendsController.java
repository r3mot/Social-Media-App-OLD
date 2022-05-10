package social.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import social.LocalStorage.FriendData;
import social.LocalStorage.ListArray;
import social.Objects.Friend;
import social.Objects.Index;

public class FriendsController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane scrollAnchor;

    @FXML
    private ScrollPane scrollPane;

    private FriendData friendData;
    private ListArray friend_profile_data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        friendData = new FriendData();
        friend_profile_data = friendData.getData();

        showFriends();
        
    }

    private void showFriends(){
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

            scrollAnchor.getChildren().addAll(friend);
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
        // Circle friend_icon = (Circle) event.getSource();
        // displayFriendProfile(friend_icon.getId());
    }

}
