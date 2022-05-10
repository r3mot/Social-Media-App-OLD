package social.Database;

public class Query {
    
    public static final String POSTS = "CREATE TABLE POSTS" +
    "(POSTID INTEGER NOT NULL," +
    "USERNAME VARCHAR NOT NULL," +
    "NAME VARCHAR NOT NULL," + 
    "CONTENT TEXT NOT NULL," +
    "DATE TEXT NOT NULL," +
    "IMAGE VARCHAR NOT NULL," + 
    "LIKES INTEVER)";
    
    public static final String TABLE = "CREATE TABLE USERS" +
    "(USERNAME VARCHAR PRIMARY KEY NOT NULL," +
    "PASSWORD VARCHAR NOT NULL," +
    "F_NAME VARCHAR NOT NULL," +
    "L_NAME VARCHAR NOT NULL," +
    "MAJOR VARCHAR," +
    "STANDING VARCHAR," + 
    "YEAR VARCHAR," +
    "D_JOB VARCHAR," + 
    "IMAGE VARCHAR)";


    public static final String CLUBS = "CREATE TABLE CLUBS" +
    "(USERNAME VARCHIA PRIMARY KEY NOT NULL," +
    "CLUB COMMA_SEP)";



    public static final String FIND_USER = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String DATA = "SELECT * from USERS where USERNAME = ? ";

    public static final String PROFILE = "SELECT * FROM USERS WHERE USERNAME=?";

    public static final String ADD_USER = "INSERT INTO USERS(USERNAME, PASSWORD, F_NAME, L_NAME, MAJOR, STANDING, YEAR, D_JOB, IMAGE) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String CLUB = "SELECT * FROM USERS WHERE USERNAME=?";
    public static final String ADD_CLUB = "UPDATE USERS SET CLUBS=? WHERE CLUBS=?";

    public static final String GET_USERS = "SELECT * FROM USERS";

    public static final String GET_DATA = "SELECT F_NAME, L_NAME, STANDING, YEAR, D_JOB, IMAGE FROM USERS";

    public static final String FIRST_NAME = "F_NAME";
    public static final String LAST_NAME = "L_NAME";
    public static final String MAJOR = "MAJOR";
    public static final String DREAM_JOB = "D_JOB";
    public static final String YEAR = "YEAR";
    public static final String IMAGE = "IMAGE";
    public static final String STANDING = "STANDING";
    public static final String NUM_CLUS = "NUM_CLUBS";


}   
