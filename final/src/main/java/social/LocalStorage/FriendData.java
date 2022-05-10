package social.LocalStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import social.Database.DataConnect;
import social.Database.Database;
import social.Database.Query;
import social.Profiles.User;

public class FriendData {
    
    private ListArray data;

    private DataConnect dataSource;
    private Connection connection;
    private Database db;

    private final int USERNAME_INDEX = 0;
    private final int FIRST_NAME_INDEX = 1;
    private final int LAST_NAME_INDEX = 2;
    private final int MAJOR_INDEX = 3;
    private final int STANDING_INDEX = 4;
    private final int YEAR_INDEX = 5;
    private final int JOB_INDEX = 6;
    private final int IMAGE_INDEX = 7;
    private final int CLUBS_INDEX = 8;
    
    public FriendData()  {

        data = new ListArray(20, 9, "");


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

        final String query = "SELECT * FROM USERS";

        try {

                connection = dataSource.getConnection();
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                
                int row = 0;
                while(rs.next()){

                    if(rs.getString(Query.USERNAME).equals(User.currentUser)){
                        continue;
                    }
                    String username = rs.getString(Query.USERNAME);
                    String first_name = rs.getString(Query.FIRST_NAME);
                    String last_name = rs.getString(Query.LAST_NAME);
                    String major = rs.getString(Query.MAJOR);
                    String standing = rs.getString(Query.STANDING);
                    String year = rs.getString(Query.YEAR);
                    String dream_job = rs.getString(Query.DREAM_JOB);
                    String image = rs.getString(Query.IMAGE);
                    String clubs = rs.getString("CLUBS");

                    data.add(username, row, USERNAME_INDEX);
                    data.add(first_name, row, FIRST_NAME_INDEX);
                    data.add(last_name, row, LAST_NAME_INDEX);
                    data.add(major, row, MAJOR_INDEX);
                    data.add(standing, row, STANDING_INDEX);
                    data.add(year, row, YEAR_INDEX);
                    data.add(dream_job, row, JOB_INDEX);
                    data.add(image, row, IMAGE_INDEX);
                    data.add(clubs, row, CLUBS_INDEX);

                    row++;

                }

                ps.close();
                rs.close();

        }catch(Exception e){
        
        }

    }


    public void addClub(String name) throws SQLException{
        db.addClub(name);

    
    }

    public ListArray getData(){
        return this.data;
    }
}
