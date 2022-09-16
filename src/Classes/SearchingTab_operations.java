/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class SearchingTab_operations {
    
     //Instance of the db connection class
        Connection dbconn = new DB_connection().connect();
    
    //Method to show the data existing in the subject table
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet show_Searching() throws SQLException
               
       {
           //Call the stored procedure designated for showing subjets
           CallableStatement cstm=dbconn.prepareCall("{call show_searching}");
           //Execute the stored procedure and put the result into data set 
           ResultSet RS=cstm.executeQuery();
           //Return the data set 
           return RS;
       }
       //End of showing subject method
       
       
       
     //Method to insert data into subject table 
       
       /**
        * 
        * @param now
        * @return
        * @throws SQLException
        */
       public ResultSet add_new(Date now) throws SQLException
     {
         //Call the stored procedure designated for adding new subjets
         CallableStatement cstm=dbconn.prepareCall("{call add_new_searching(?)}");
         cstm.setDate(1, now);
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
       public  ResultSet update_Searching(int updatedRow, String[] textboxes) throws SQLException
        {
             
            // Call the stored procedure designated for updating subject data
         
          CallableStatement cstm=dbconn.prepareCall("{call update_searching(?,?,?)}");
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
        public  ResultSet delete_Searching(int deletedRow) throws SQLException
        {
             
            // Call the stored procedure designated for deleting subject
         
          CallableStatement cstm=dbconn.prepareCall("{call delete_searching(?)}");
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
        public ResultSet search_Searching(String criterion) throws SQLException
       {
         // Call the stored procedure designated for search subject
         
          CallableStatement cstm=dbconn.prepareCall("{call Search_searching(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
                            
          //Execute the search operation  and put it into  a data set
         ResultSet rs= cstm.executeQuery();
         
          //Return the data set 
         return rs;
       
       
       }//End search subject method
}
