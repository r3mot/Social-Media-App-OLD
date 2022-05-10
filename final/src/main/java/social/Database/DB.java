package social.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    
    public void createInitialTable() throws ClassNotFoundException{

        Connection connection;

        try {

            Class.forName(Path.CLASS);

            connection = DriverManager.getConnection(Path.DB);          
            Statement statement = connection.createStatement();

            statement.executeUpdate(Query.TABLE);
            statement.close();
            connection.close();

        }catch(SQLException e){
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        //System.out.println("Users table created");
    }

    public void createPostsTable() throws ClassNotFoundException{

        Connection connection;

        try {

            Class.forName(Path.CLASS);
            connection = DriverManager.getConnection(Path.DB);
            //System.out.println("opened database successfully");
            
            Statement statement = connection.createStatement();

            statement.executeUpdate(Query.POSTS);
            statement.close();
            connection.close();

        }catch(SQLException e){
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        //System.out.println("Posts table created");
    }

    public void findColumn(){



    }


    public void post(String query, ResultSet result, String value) throws SQLException {

        Connection connection;
        PreparedStatement pstm;

        try{

            Class.forName(Path.CLASS);
            connection = DriverManager.getConnection(Path.DB);
            pstm = connection.prepareStatement(query);
            while(result.next()){
                pstm.setString(1, value);
                pstm.addBatch();
            }

            pstm.executeBatch();
            pstm.close();
            connection.close();

        }catch(ClassNotFoundException e){
            System.out.println("Post failed . . .");
        }
    }
}
