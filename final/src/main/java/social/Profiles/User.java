package social.Profiles;

import java.sql.SQLException;

import javafx.scene.image.Image;
import social.Database.Database;

public class User {
    
    private String first_name;
    private String last_name;
    private String major;
    private String standing;
    private String year;
    private String dream_job;
    private String username;
    private String password;
    private Image image;

    public static String currentUser;
    public static String currentName;
    public static String currentURL;

    public User(String first_name, 
                    String last_name, 
                    String major, 
                    String standing, 
                    String year, 
                    String dream_job,
                    Image image,
                    String username,
                    String password)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.major = major;
        this.standing = standing;
        this.year = year;
        this.dream_job = dream_job;
        this.image = image;
        this.username = username;
        this.password = password;

        currentUser = username;
        
    }

    public String getFirstName(){
        return this.first_name;
    }

    public String getLastName(){
        return this.last_name;
    }

    public String getMajor(){
        return this.major;
    }

    public String getStanding(){
        return this.standing;
    }

    public String getYear(){
        return this.year;
    }

    public String getDreamJob(){
        return this.dream_job;
    }

    public Image getImage(){
        return this.image;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public static void setCurrentUser(String username){
        currentUser = username;
    }

    public static void setCurrentName(String name){
        currentName = name;
    }

    public static String getCurrentIcon() throws SQLException{
        Database db = new Database();
        return db.getProfilePicture();
    }

    public static void setCurrentIcon(String url){
        currentURL = url;
    }
}
