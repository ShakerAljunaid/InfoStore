package Classes;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class DB_connection {
    
    private Connection DB_connction;
    
    /**
     * 
     * @return
     */
    public Connection connect(){
    try
    {
       Class.forName("com.mysql.Jdbc.Driver");
          System.out.println("Connection succeeded");
    
    }
    catch(ClassNotFoundException cnf){ System.out.println("Connection faild"+cnf);}
    String url="jdbc:mysql://localhost:3306/info_store";
    try
    {
       DB_connction =(Connection) DriverManager.getConnection(url,"root","");
        System.out.println(" Database  connected");
    
    
    }
    catch (SQLException sqlexp){System.out.println(" No database found" +sqlexp);}
    return   DB_connction;
    
        }
   
    
}
