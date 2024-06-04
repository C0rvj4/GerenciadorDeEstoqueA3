package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author felip
 */
public class ConexaoMVC {

 
    public Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");}
         catch (ClassNotFoundException exception) {
            exception.printStackTrace();}
         
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc?useSSL=false", "root", "F!el1pe2003.@0");
        }catch(SQLException exception){
            exception.printStackTrace();}
        
        return conn;

    }

}