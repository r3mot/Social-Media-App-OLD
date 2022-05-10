package social.Controllers;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import social.Database.Database;
import social.Profiles.User;

public class CreateUserController {

    @FXML private TextField dream_job;
    @FXML private TextField first_name;
    @FXML private TextField last_name;
    @FXML private TextField major;
    @FXML private TextField password;
    @FXML private TextField standing;
    @FXML private TextField username;
    @FXML private TextField year;
    private Image image;
    private User user;

    private Database db;

    @FXML void process_account(ActionEvent event) throws ClassNotFoundException, IOException, SQLException {

        db = new Database();
        if(validInput()){

            this.user = new User(first_name.getText(), 
                                last_name.getText(), 
                                major.getText(), 
                                standing.getText(), 
                                year.getText(), 
                                dream_job.getText(), 
                                image,
                                username.getText(), 
                                password.getText() 
                                );

            // createUser();
            // writeToFile();

            db.createUser(this.user);


        }
    }

    @FXML void upload_user_image(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            this.image = new Image(file.toURI().toString()); 
        }
    }

    // public void createUser() throws ClassNotFoundException{

    //     Class.forName(Path.CLASS);
    //     Connection connection;
    //     PreparedStatement statement;

    //     try{

    //         String hashedPass = hashedPassword(user.getPassword());
    //         connection = DriverManager.getConnection(Path.DB);
        
    //         statement = connection.prepareStatement(Query.ADD_USER);
    //         statement.setString(1, user.getUsername());
    //         statement.setString(2, hashedPass);
    //         statement.setString(3, user.getFirstName());
    //         statement.setString(4, user.getLastName());
    //         statement.setString(5, user.getMajor());
    //         statement.setString(6, user.getStanding());
    //         statement.setString(7, user.getYear());
    //         statement.setString(8, user.getDreamJob());
    //         statement.setString(9, user.getImage().getUrl());
    //         statement.executeUpdate();
            
    //         statement.close();
    //         connection.close();

    //     }catch (SQLException e){
    //         System.out.println(e.getMessage());
    //     }
    // }

    // private String hashedPassword(String passwordToHash){

    //     MessageDigest md;
    //     StringBuilder sb;

    //     String generatedPassword = null;
    //     try {
    //         md = MessageDigest.getInstance("SHA-512");
    //         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
    //         md.update(bytes);
    //         sb = new StringBuilder();
    //         for(int i=0; i< bytes.length ;i++){
    //             sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    //         }
    //         generatedPassword = sb.toString();
    //     } catch (NoSuchAlgorithmException e) {
    //         e.printStackTrace();
    //     }
    //     return generatedPassword;
    //     } 
    
    private boolean validInput() throws IOException{
        
        return !(first_name.getText().isEmpty() || last_name.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty());
    }

    private void writeToFile() throws IOException{
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("usernames.txt"), "utf-8"))) {
                writer.write(username.getText() + ",");
            }
        }
}