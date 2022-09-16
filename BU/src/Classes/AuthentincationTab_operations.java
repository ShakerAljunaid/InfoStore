/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class AuthentincationTab_operations {
    
     //Instance of the db connection class
        Connection dbconn = new DB_connection().connect();
    
    //Method to show the data existing in the users table
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet show_users() throws SQLException
               
       {
           //Call the stored procedure designated for showing subjets
           CallableStatement cstm=dbconn.prepareCall("{call show_users}");
           //Execute the stored procedure and put the result into data set 
           ResultSet RS=cstm.executeQuery();
           //Return the data set 
           return RS;
       }
       //End of showing users method
       
       
       
     //Method to insert data into users table 
       
       /**
        * 
        * @param usrName
        * @param usrPassword
        * @return
        * @throws SQLException
        */
       public ResultSet add_new(String usrName,String usrPassword) throws SQLException
     {
         //Call the stored procedure designated for adding new subjets
         CallableStatement cstm=dbconn.prepareCall("{call add_new_user(?,?)}");
         
           cstm.setString(1,usrName);
           cstm.setString(2,usrPassword);
         
           //Execute the stored procedure and put the result into data set 
           ResultSet RS=cstm.executeQuery();
            //Return the data set 
           return RS;
     
      }
       //End of adding new users method
       
       
     
        //Method that is used to execute the update operation
     /**
      * 
      * @param updatedRow
      * @param textboxes
      * @return
      * @throws SQLException
      */
     public  ResultSet update_users(int updatedRow, String[] textboxes) throws SQLException
        {
             
            // Call the stored procedure designated for updating users data
         
          CallableStatement cstm=dbconn.prepareCall("{call update_users(?,?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
           cstm.setInt(1, updatedRow);
           cstm.setString(2,textboxes[0]);
           cstm.setString(3,textboxes[1]);
         
       
         
         
                 
          //Execute the stored procedure and put the result into data set 
              
           ResultSet RS=  cstm.executeQuery();
           //Return the data set 
          return  RS;
          
        }
        //End of update users method  
        
        
              
        
        
      
      //Method to delete data from the users table
        /**
         * 
         * @param deletedRow
         * @return
         * @throws SQLException
         */
        public  ResultSet delete_users(int deletedRow) throws SQLException
        {
             
            // Call the stored procedure designated for deleting users
         
          CallableStatement cstm=dbconn.prepareCall("{call delete_user(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setInt(1,deletedRow);
          
          //Execute the deletion operation and put into a data set
               
           ResultSet RS=  cstm.executeQuery();
          //Return the data set 
          return  RS;
          
        }
        //End of delete Row method
        
        //method to search of data in the users 
        /**
         * 
         * @param criterion
         * @return
         * @throws SQLException
         */
        public ResultSet search_users(String criterion) throws SQLException
       {
         // Call the stored procedure designated for search users
         
          CallableStatement cstm=dbconn.prepareCall("{call Search_users(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
                            
          //Execute the search operation  and put it into  a data set
         ResultSet rs= cstm.executeQuery();
         
          //Return the data set 
         return rs;
       
       
       }//End search users method
       
       //Method to verify the user name and password
        /**
         * 
         * @param usrName
         * @param usrPassword
         * @return
         * @throws SQLException
         */
        public boolean verify_user(String usrName,String usrPassword) throws SQLException
       {
       
       // Call the stored procedure designated for search users
         
          CallableStatement cstm=dbconn.prepareCall("{call verify_users(?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
             cstm.setString(1, usrName);
             cstm.setString(2, usrPassword);                
           //Execute the search operation  and put it into  a data set
           ResultSet rs= cstm.executeQuery();
           if(rs.first())
               return true;
           else
            return false;
       
       
       
       } 
       
       //Method that check if the user name is replicated 
       /**
        * 
        * @param usrName
        * @return
        * @throws SQLException
        */
       public boolean check_replication(String usrName) throws SQLException 
       {
         // Call the stored procedure designated for search users
         
          CallableStatement cstm=dbconn.prepareCall("{call check_replicated_user(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
             cstm.setString(1, usrName);
                         
           //Execute the search operation  and put it into  a data set
           ResultSet rs= cstm.executeQuery();
           if(rs.first())
               return false;
           else
            return true;
       
       
       }
       //End replication check method
    
    
    
}
