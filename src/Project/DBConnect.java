package Project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    
    
    public static Connection getConnection(){
        
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tolpawiring", "root", "");
            //System.out.println("Connected");
        }catch(Exception A){
            //System.out.println("Connection Failed");
            A.printStackTrace();
        }
        return conn;
    }
    
}

