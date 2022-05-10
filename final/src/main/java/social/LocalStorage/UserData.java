package social.LocalStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import social.Database.DataConnect;
import social.Database.Database;
import social.Database.Query;
import social.Objects.Index;
import social.Profiles.User;

public class UserData {

    private static ArrayList<String> data;

    private DataConnect dataSource;
    private Connection connection;
    private static Database db;

    public UserData()  {

        data = new ArrayList<>();
        dataSource = new DataConnect();
        db = new Database();

        fetchProfile();

    }

    /**
     * 
     * @throws SQLException
     * 
     * Gather user profile from database
     * to store in Local Storage
     */
    private void fetchProfile() {

        ResultSet rs;
        PreparedStatement ps;

        final String query = "SELECT * FROM USERS WHERE USERNAME=?";

        try {

                connection = dataSource.getConnection();
                ps = connection.prepareStatement(query);
                ps.setString(1, User.currentUser);
                rs = ps.executeQuery();
                
                while(rs.next()){

                    String username = rs.getString(Query.USERNAME);
                    String first_name = rs.getString(Query.FIRST_NAME);
                    String last_name = rs.getString(Query.LAST_NAME);
                    String major = rs.getString(Query.MAJOR);
                    String standing = rs.getString(Query.STANDING);
                    String year = rs.getString(Query.YEAR);
                    String dream_job = rs.getString(Query.DREAM_JOB);
                    String image = rs.getString(Query.IMAGE);
                    String clubs = rs.getString("CLUBS");

                    data.add(username);
                    data.add(first_name);
                    data.add(last_name);
                    data.add(major);
                    data.add(standing);
                    data.add(year);
                    data.add(dream_job);
                    data.add(image);
                    data.add(clubs);
                }

                ps.close();
                rs.close();

        }catch(Exception e){
        
        }

    }


    public void addClub(String name) throws SQLException{
        db.addClub(name);

        String updated = data.get(Index.CLUBS) + ", " + name;
        data.set(Index.CLUBS, updated);
    }

    public static void addPost(String content, String date) throws SQLException{

        db.addPost(content, date);

        data.add(Index.POSTS, content + "," + date);
    }

    public static ArrayList<String> getData(){
        return data;
    }

  
}
