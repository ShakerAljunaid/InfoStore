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
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ContactTab_operations {
    
     //Instance of the db connection class
        Connection dbconn = new DB_connection().connect();
        
      //Method to show the data existing in the subject table
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet show_Contacts() throws SQLException
               
       {
           //Call the stored procedure designated for showing subjets
           CallableStatement cstm=dbconn.prepareCall("{call show_contacts}");
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
         CallableStatement cstm=dbconn.prepareCall("{call add_new_contact}");
         
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
         * @param number
         * @return
         * @throws SQLException
         */
        public  ResultSet update_Contacts(int updatedRow, String[] textboxes,int number) throws SQLException
        {
             
            // Call the stored procedure designated for updating subject data
         
          CallableStatement cstm=dbconn.prepareCall("{call update_contacts(?,?,?,?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
           cstm.setInt(1, updatedRow);
           cstm.setString(2,textboxes[0]);
           cstm.setString(4,textboxes[1]); 
           cstm.setString(5,textboxes[2]);
           cstm.setInt(3,number);
         
                 
          //Execute the stored procedure and put the result into data set 
              
           ResultSet RS=  cstm.executeQuery();
           //Return the data set 
          return  RS;
          
        }
        //End of update subject method  
        
        
          //Method  to update the picture
        /**
         * 
         * @param path
         * @param currentRow
         * @return
         * @throws FileNotFoundException
         * @throws SQLException
         */
        public ResultSet con_picUpdate(String path,int currentRow) throws FileNotFoundException, SQLException
           {
            //Define the input stream to contain the image
            
         InputStream inStr=new FileInputStream(new File(path) );
         
          
          // Call the stored procedure designated for updating info 
         
          CallableStatement cstm=dbconn.prepareCall("{call update_conPic(?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
           cstm.setBlob(1,inStr );
           cstm.setInt(2,currentRow);
          //Execute the update operation
               
           ResultSet RS=  cstm.executeQuery();
         
         
            return RS;
         }//End of update pic
        
        
        
      
      //Method to delete data from the subject table
        /**
         * 
         * @param deletedRow
         * @return
         * @throws SQLException
         */
        public  ResultSet delete_Contact(int deletedRow) throws SQLException
        {
             
            // Call the stored procedure designated for deleting subject
         
          CallableStatement cstm=dbconn.prepareCall("{call delete_contacts(?)}");
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
        public ResultSet search_Contacts(String criterion) throws SQLException
       {
         // Call the stored procedure designated for search subject
         
          CallableStatement cstm=dbconn.prepareCall("{call Search_contacts(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
                            
          //Execute the search operation  and put it into  a data set
         ResultSet rs= cstm.executeQuery();
         
          //Return the data set 
         return rs;
       
       
       }//End search subject method 
       
     
}
