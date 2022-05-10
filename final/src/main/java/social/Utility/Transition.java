package social.Utility;



import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Transition {

    private final static int LEFT = 0;
    private final static int RIGHT = 525;
    
    public static void slideRight(Pane pane){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(pane);
        slide.setToX(RIGHT);
        slide.play();
        slide.setOnFinished((e->{
        }));
    }

    public static void slideLeft(Pane pane){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(pane);
        slide.setToX(LEFT);
        slide.play();
        slide.setOnFinished((e->{
        }));
    }

    public static void buttonState(Button on, Button off){
        on.setVisible(true);
        off.setVisible(false);
    }

    public static void showLogin(Button transition, Button login, Hyperlink forgot, TextField user, TextField pass){
        
    }
}
