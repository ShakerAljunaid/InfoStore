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

/**
 *
 * @author Administrator
 */
public class SubjectTab_operations {
     //Instance of the db connection class
        Connection dbconn = new DB_connection().connect();
        
      //Method to show the data existing in the subject table
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet show_subjects() throws SQLException
               
       {
           //Call the stored procedure designated for showing subjets
           CallableStatement cstm=dbconn.prepareCall("{call show_subjects}");
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
         CallableStatement cstm=dbconn.prepareCall("{call add_new_subject}");
         
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
        * @param updatingDate
        * @return
        * @throws SQLException
        */
       public  ResultSet update_Subject(int updatedRow, String[] textboxes,Date updatingDate) throws SQLException
        {
             
            // Call the stored procedure designated for updating subject data
         
          CallableStatement cstm=dbconn.prepareCall("{call update_subject(?,?,?,?,?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
           cstm.setInt(1, updatedRow);
          cstm.setString(2,textboxes[0]);
          cstm.setString(3,textboxes[1]);
          cstm.setString(4,textboxes[2]);
          cstm.setString(5,textboxes[3]);
          cstm.setString(6,textboxes[4]);
         
                 
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
       public ResultSet sub_picUpdate(String path,int currentRow) throws FileNotFoundException, SQLException
           {
            //Define the input stream to contain the image
            
         InputStream inStr=new FileInputStream(new File(path) );
         
          
          // Call the stored procedure designated for updating info 
         
          CallableStatement cstm=dbconn.prepareCall("{call update_subPic(?,?)}");
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
          public  ResultSet delete_subject(int deletedRow) throws SQLException
        {
             
            // Call the stored procedure designated for deleting subject
         
          CallableStatement cstm=dbconn.prepareCall("{call delete_subject(?)}");
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
          public ResultSet search_subject(String criterion) throws SQLException
       {
         // Call the stored procedure designated for search subject
         
          CallableStatement cstm=dbconn.prepareCall("{call Search_subject(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
                            
          //Execute the search operation  and put it into  a data set
         ResultSet rs= cstm.executeQuery();
         
          //Return the data set 
         return rs;
       
       
       }//End search subject method 
       
        
       //Method to fill the subject combo box with  the subjects name
       /**
        * 
        * @param cmb
        * @throws SQLException
        */
       public void fill_combo_box(JComboBox cmb) throws SQLException
       {
           //Call the method designated for retrieving data from subject table and put the data into a data set
           ResultSet RS=show_subjects();
           
           //Array to store the data to be put into the combo box
        ArrayList<String> groupNames = new ArrayList<String>();
        //Loop to insert the data retrieved into the array
        while(RS.next())
         {
             //Selecting the attribute to be stored into the array
            String groupName = RS.getString("Subject_name"); 
            // add group names to the array list
              groupNames.add(groupName);
               
        }
      
       // Populate the combo box
       DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
         cmb.setModel(model);
       
       
       }//End filling  combo box method
       
      
      
      
}
