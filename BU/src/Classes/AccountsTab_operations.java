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
public class AccountsTab_operations {
    
    
    //Instance of the db connection class
        Connection dbconn = new DB_connection().connect();
    
    //Method to show the data existing in the Accounts table
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet show_Accounts() throws SQLException
               
       {
           //Call the stored procedure designated for showing subjets
           CallableStatement cstm=dbconn.prepareCall("{call show_Accounts}");
           //Execute the stored procedure and put the result into data set 
           ResultSet RS=cstm.executeQuery();
           //Return the data set 
           return RS;
       }
       //End of showing Accounts method
       
       
       
     //Method to insert data into Accounts table 
       
        /**
         * 
         * @return
         * @throws SQLException
         */
        public ResultSet add_new() throws SQLException
     {
         //Call the stored procedure designated for adding new subjets
         CallableStatement cstm=dbconn.prepareCall("{call add_new_Account}");
         
           //Execute the stored procedure and put the result into data set 
           ResultSet RS=cstm.executeQuery();
            //Return the data set 
           return RS;
     
      }
       //End of adding new Accounts method
       
       
     
        //Method that is used to execute the update operation
     /**
      * 
      * @param updatedRow
      * @param textboxes
      * @return
      * @throws SQLException
      */
     public  ResultSet update_Accounts(int updatedRow, String[] textboxes) throws SQLException
        {
             
            // Call the stored procedure designated for updating Accounts data
         
          CallableStatement cstm=dbconn.prepareCall("{call update_Accounts(?,?,?,?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
           cstm.setInt(1, updatedRow);
           cstm.setString(2,textboxes[0]);
           cstm.setString(3,textboxes[1]);
           cstm.setString(4,textboxes[2]);
           cstm.setString(5,textboxes[3]);
         
         
                 
          //Execute the stored procedure and put the result into data set 
              
           ResultSet RS=  cstm.executeQuery();
           //Return the data set 
          return  RS;
          
        }
        //End of update Accounts method  
        
        
              
        
        
      
      //Method to delete data from the Accounts table
     /**
      * 
      * @param deletedRow
      * @return
      * @throws SQLException
      */
     public  ResultSet delete_Accounts(int deletedRow) throws SQLException
        {
             
            // Call the stored procedure designated for deleting Accounts
         
          CallableStatement cstm=dbconn.prepareCall("{call delete_Account(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setInt(1,deletedRow);
          
          //Execute the deletion operation and put into a data set
               
           ResultSet RS=  cstm.executeQuery();
          //Return the data set 
          return  RS;
          
        }
        //End of delete Row method
        
        //method to search of data in the Accounts 
        /**
         * 
         * @param criterion
         * @return
         * @throws SQLException
         */
        public ResultSet search_Accounts(String criterion) throws SQLException
       {
         // Call the stored procedure designated for search Accounts
         
          CallableStatement cstm=dbconn.prepareCall("{call Search_Accounts(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
                            
          //Execute the search operation  and put it into  a data set
         ResultSet rs= cstm.executeQuery();
         
          //Return the data set 
         return rs;
       
       
       }//End search Accounts method
    
    
}
