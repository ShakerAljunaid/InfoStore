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
public class Useful_websitesTab_operations {
    
    
    
     //Instance of the db connection class
        Connection dbconn = new DB_connection().connect();
    
    //Method to show the data existing in the subject table
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet show_useful_websites() throws SQLException
               
       {
           //Call the stored procedure designated for showing subjets
           CallableStatement cstm=dbconn.prepareCall("{call show_useful_websites}");
           //Execute the stored procedure and put the result into data set 
           ResultSet RS=cstm.executeQuery();
           //Return the data set 
           return RS;
       }
       //End of showing subject method
       
       
       
     //Method to insert data into subject table 
       
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet add_new() throws SQLException
     {
         //Call the stored procedure designated for adding new subjets
         CallableStatement cstm=dbconn.prepareCall("{call add_new_useful_websites}");
         
           //Execute the stored procedure and put the result into data set 
           ResultSet RS=cstm.executeQuery();
            //Return the data set 
           return RS;
     
      }
       //End of adding new subject method
       
       
     
        //Method that is used to execute the update operation
     /**
      * 
      * @param updatedRow
      * @param textboxes
      * @return
      * @throws SQLException
      */
     public  ResultSet update_useful_websites(int updatedRow, String[] textboxes) throws SQLException
        {
             
            // Call the stored procedure designated for updating subject data
         
          CallableStatement cstm=dbconn.prepareCall("{call update_useful_websites(?,?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
            cstm.setInt(1, updatedRow);
           cstm.setString(2,textboxes[0]);
           cstm.setString(3,textboxes[1]);
         
       
         
         
                 
          //Execute the stored procedure and put the result into data set 
              
           ResultSet RS=  cstm.executeQuery();
           //Return the data set 
          return  RS;
          
        }
        //End of update subject method  
        
        
              
        
        
      
      //Method to delete data from the subject table
        /**
         * 
         * @param deletedRow
         * @return
         * @throws SQLException
         */
        public  ResultSet delete_useful_websites(int deletedRow) throws SQLException
        {
             
            // Call the stored procedure designated for deleting subject
         
          CallableStatement cstm=dbconn.prepareCall("{call delete_useful_websites(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setInt(1,deletedRow);
          
          //Execute the deletion operation and put into a data set
               
           ResultSet RS=  cstm.executeQuery();
          //Return the data set 
          return  RS;
          
        }
        //End of delete Row method
        
        //method to search of data in the subject 
        /**
         * 
         * @param criterion
         * @return
         * @throws SQLException
         */
        public ResultSet search_useful_websites(String criterion) throws SQLException
       {
         // Call the stored procedure designated for search subject
         
          CallableStatement cstm=dbconn.prepareCall("{call Search_useful_websites(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
                            
          //Execute the search operation  and put it into  a data set
         ResultSet rs= cstm.executeQuery();
         
          //Return the data set 
         return rs;
       
       
       }//End search subject method
}
