package social.Database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import social.LocalStorage.ListArray;
import social.Objects.Index;
import social.Profiles.User;

public class Database {
   
    private final String GET_POST = "SELECT * FROM POSTS";
    private final String GET_USER_POSTS = "SELECT * FROM POSTS WHERE USERNAME=?";
    private final String ADD_POST = "INSERT INTO POSTS(USERNAME, NAME, CONTENT, DATE, IMAGE, LIKES) VALUES (?,?,?,?,?,?)";
    private final String UPDATE_POST_LIKES = "UPDATE POSTS SET LIKES=LIKES+1 WHERE USERNAME=?";
    private final String GET_LIKE_COUNT = "SELECT LIKES FROM POSTS WHERE USERNAME=?";

    private final String ADD_CLUB = "UPDATE USERS SET CLUBS=? WHERE USERNAME=?";
    private final String GET_CLUBS = "SELECT CLUBS FROM USERS WHERE USERNAME=?";
    private final String GET_NUM_CLUBS = "SELECT NUM_CLUBS FROM USERS WHERE USERNAME=?";
    public static final String ADD_USER = "INSERT INTO USERS(USERNAME, PASSWORD, F_NAME, L_NAME, MAJOR, STANDING, YEAR, D_JOB, IMAGE) VALUES (?,?,?,?,?,?,?,?,?)";

    private final String FIND_USER_NAME = "SELECT * FROM USERS WHERE USERNAME=?";
    private final String VALID_LOGIN = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
    private final String COUNT_USERS = "SELECT USERNAME FROM USERS";
    private final String GET_IMAGE = "SELECT IMAGE FROM USERS WHERE USERNAME=?";

    private final String USERNAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";
    private final String FIRST_NAME = "F_NAME";
    private final String LAST_NAME = "L_NAME";

    private DataConnect dataSource = new DataConnect();
    private Connection connection = dataSource.getConnection();


    public int getNumberClubs() throws SQLException{

        PreparedStatement ps;
        ResultSet rs;

        connection = dataSource.getConnection();
        ps = connection.prepareStatement(GET_NUM_CLUBS);
        ps.setString(1, User.currentUser);
        rs = ps.executeQuery();

        int numClubs = Integer.parseInt(rs.getString(Query.NUM_CLUS));

        ps.close();
        rs.close();
        connection.close();

        return numClubs;
    }

    /**
     * 
     * @return url
     * @throws SQLException
     * 
     * Get current user profile picture
     */
    public String getProfilePicture() throws SQLException{

        PreparedStatement ps;
        ResultSet rs;
        
        connection = dataSource.getConnection();
        ps = connection.prepareStatement(GET_IMAGE);
        ps.setString(1, User.currentUser);
        rs = ps.executeQuery();

        String url = rs.getString(Query.IMAGE);

        rs.close();
        ps.close();
        connection.close();

        return url;
    }

    /**
     * 
     * @param username to be checked against db
     * @return user exists
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * When a new user creates an account, we must
     * ensure the username is unique
     */
    public boolean userExists(String username) throws ClassNotFoundException, SQLException{

        connection = dataSource.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        ps = connection.prepareStatement(FIND_USER_NAME);
        ps.setString(1, username);
        rs = ps.executeQuery();

        while(rs.next()){
            if(username.equals(rs.getString(USERNAME))){
                ps.close();
                rs.close();
                return true;
            }
        }

        ps.close();
        rs.close();
        connection.close();

        return false;
    }
    
    /**
     * 
     * @param user Object containing user data
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * Adds newly created user to the database
     */
    public void createUser(User user) throws SQLException{

        PreparedStatement ps;

        String hashedPassword = hash(user.getPassword());
        connection = dataSource.getConnection();

        ps = connection.prepareStatement(ADD_USER);
        ps.setString(1, user.getUsername());
        ps.setString(2, hashedPassword);
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setString(5, user.getMajor());
        ps.setString(6, user.getStanding());
        ps.setString(7, user.getYear());
        ps.setString(8, user.getDreamJob());
        ps.setString(9, user.getImage().getUrl());
        ps.executeUpdate();
        ps.close();

        connection.close();

    }

    /**
     * 
     * @param content user wishes to post
     * @param date of post
     * @throws SQLException
     * 
     * Adds new post to the database
     */
    public void addPost(String content, String date) throws SQLException{

        PreparedStatement ps;
        connection = dataSource.getConnection();
        ps = connection.prepareStatement(ADD_POST);

        ps.setString(1, User.currentUser);
        ps.setString(2, User.currentName);
        ps.setString(3, content);
        ps.setString(4, date);
        ps.setString(5, User.currentURL);
        ps.setInt(6, 0);
        ps.executeUpdate();
        ps.close();

        connection.close();

    }

    /**
     * 
     * @param username of post that was liked
     * @throws SQLException
     * 
     * Updates the number of likes on a given post
     */
    public void updateLikeCount(String username) throws SQLException{

        PreparedStatement ps;
        connection = dataSource.getConnection();
        ps = connection.prepareStatement(UPDATE_POST_LIKES);

        ps.setString(1, username);
        ps.executeUpdate();
        ps.close();

        connection.close();
        
    }


    public int getLikeCount(String username) throws SQLException{

        PreparedStatement ps;
        ResultSet rs;
        connection = dataSource.getConnection();
        ps = connection.prepareStatement(GET_LIKE_COUNT);
        ps.setString(1, username);

        rs = ps.executeQuery();

        int count = rs.getInt("LIKES");

        rs.close();;
        ps.close();

        return count;
    }
    /**
     * 
     * @return current users feed
     * @throws SQLException
     */
    public ListArray getUserFeed() throws SQLException{

        ListArray data = new ListArray(25, 6, "");
        PreparedStatement ps;
        ResultSet rs;
        connection = dataSource.getConnection();
        ps = connection.prepareStatement(GET_USER_POSTS);
        ps.setString(1, User.currentUser);
        
        rs = ps.executeQuery();

        int row = 0;
        while(rs.next()){
            String id = String.valueOf(rs.getInt("POSTID"));
            String username = rs.getString("USERNAME");
            String name = rs.getString("NAME");
            String content = rs.getString("CONTENT");
            String date = rs.getString("DATE");
            String url = rs.getString("IMAGE");
            
            data.add(id, row, Index.POST_ID);
            data.add(username, row, Index.POST_USERNAME);
            data.add(name, row, Index.POST_NAME);
            data.add(content, row, Index.POST_CONTENT);
            data.add(date, row, Index.POST_DATE);
            data.add(url, row, Index.POST_URL);
        }

        ps.close();
        rs.close();

        return data;
    }

    /**
     * 
     * @return
     * @throws SQLException
     */
    public ListArray getFeed() throws SQLException{

        ListArray feed = new ListArray(30, 6, "");
        PreparedStatement ps;
        ResultSet rs;

        connection = dataSource.getConnection();
        ps = connection.prepareStatement(GET_POST);
        rs = ps.executeQuery();

        int row = 0;
        while(rs.next()){
            String id = String.valueOf(rs.getInt("POSTID"));
            String username = rs.getString("USERNAME");
            String name = rs.getString("NAME");
            String content = rs.getString("CONTENT");
            String date = rs.getString("DATE");
            String url = rs.getString("IMAGE");
            
            feed.add(id, row, Index.POST_ID);
            feed.add(username, row, Index.POST_USERNAME);
            feed.add(name, row, Index.POST_NAME);
            feed.add(content, row, Index.POST_CONTENT);
            feed.add(date, row, Index.POST_DATE);
            feed.add(url, row, Index.POST_URL);
            row++;

        }

        // ps.close();
        // rs.close();
        // connection.close();

        return feed;
    }


    /**
     * 
     * @param name of club
     * @throws SQLException
     * 
     * Fetches current user from database
     * Adds the selected club to the user profile
     */
    public void addClub(String name) throws SQLException{

        PreparedStatement ps;
        ResultSet rs;
        String currentList ="";
        connection = dataSource.getConnection();
        ps = connection.prepareStatement(GET_CLUBS);
        ps.setString(1, User.currentUser);
        rs = ps.executeQuery();

        while(rs.next()){
            currentList = rs.getString("CLUBS");
            currentList += ", " + name;
        }
        ps.close();
        rs.close();

        PreparedStatement ps2;
        ps2 = connection.prepareStatement(ADD_CLUB);
        ps2.setString(1, currentList);
        ps2.setString(2, User.currentUser);
        ps2.executeUpdate();

        ps2.close();

        connection.close();

        
    }

    /**
     * 
     * @param un
     * @param pw
     * @return valid login
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * Checks entered username and password against
     * the database
     */
    private boolean loginSuccessful(String username, String password) throws ClassNotFoundException, SQLException{

        boolean loginResult = false;
        PreparedStatement ps;
        ResultSet rs;

        password = hash(password);

        connection = dataSource.getConnection();
        ps = connection.prepareStatement(VALID_LOGIN);
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();

        while(rs.next()){

            String user = rs.getString(USERNAME);
            String pass = rs.getString(PASSWORD);

            // Getting current full name in same query 
            String first = rs.getString(FIRST_NAME);
            String last = rs.getString(LAST_NAME);

            if(user.equals(username) && pass.equals(password)){
                loginResult = true;
                User.setCurrentName(first + " " + last);
                break;
            }

           
        }

        ps.close();
        rs.close();
        connection.close();

        return loginResult;
    }

    /**
     * 
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     * When user attempts to login, the method will return true or false
     * The boolean nature of the method is used to determine error handling
     * on the login screen (i.e. informing the user of incorrect credentials)
     */
    public boolean login(String username, String password) throws ClassNotFoundException, SQLException{
        return loginSuccessful(username, password);
    }

    /**
     * 
     * @param passwordToHash raw password entry
     * @return
     * 
     * Hashes password as to not store plain text
     * May also be used to decode a previously hashed password
     */
    private String hash(String passwordToHash){

        MessageDigest md;
        StringBuilder sb;

        String generatedPassword = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            md.update(bytes);
            sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    } 


    /**
     * 
     * @return current number of users
     * @throws SQLException
     */
    public int getNumberUsers() throws SQLException{

        connection = dataSource.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        int count = 0;

        ps = connection.prepareStatement(COUNT_USERS);
        rs = ps.executeQuery();

        while(rs.next()){
            count++;
        }

        ps.close();
        rs.close();
        connection.close();

        return count;
    }



}
