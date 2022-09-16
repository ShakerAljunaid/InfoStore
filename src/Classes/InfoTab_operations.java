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
public class InfoTab_operations {
    
    //Instance of the db connection class
        Connection dbconn = new DB_connection().connect();
    
        /**
         * 
         * @return
         * @throws SQLException
         */
        public int AUtoNum() throws SQLException
        {
         
            //Instance of ResultSet into that the resulted data will be stored
             int  number = 0;
             CallableStatement cstm;
             ResultSet rs;
           //Command that is used to increment one row depending on the max row in the table
                      //Call stored procedure to add row in proper sort
             cstm=dbconn.prepareCall("{call Auto_number(?)}");
             //Match the type of the out parameters with proper var
             cstm.registerOutParameter(1,Types.INTEGER);
              cstm.setInt(1, number);
                                
               cstm.executeUpdate(); //or rs=cstm.executeQuery();
               number=cstm.getInt(1);
                                    
                   
          
           return (number + 1);
         
        }
        //End of the AutoNum method
    
        
        //Method that is used to show all  the data in a chosen row  in the table
    /**
     * 
     * @return
     * @throws SQLException
     */
    public ResultSet Show_Data() throws SQLException
        {
            // Instance of ResultSet into that the resulted data will be stored
            Statement stm=dbconn.createStatement();
            ResultSet rs=stm.executeQuery("{call show_information}");
           //Return the data set 
              return (rs);
        }
        //End of show data method*/
      
    
    //Method that is used to insert one new row after the last row in the table
    /**
     * 
     * @return
     * @throws SQLException
     */
    public ResultSet Insert_NewRow() throws SQLException
        {
            //Instance of ResultSet into that the resulted data will be stored
            
          Statement stm=dbconn.createStatement();
          //Call the stored procedure using statement instead of the callable  statement, since it does not have any parameter
          ResultSet rs=stm.executeQuery("{call Insert_info}");
           //Return the data set                                                 
             
          return (rs);
           
           }
        
        
        
        //Method that is used to execute the update operation
    /**
     * 
     * @param updatedRow
     * @param textboxes
     * @param updatingDate
     * @return
     * @throws SQLException
     */
    public  ResultSet update_Row(int updatedRow, String[] textboxes,Date updatingDate) throws SQLException
        {
             
            // Call the stored procedure designated for updating info 
         
          CallableStatement cstm=dbconn.prepareCall("{call update_info(?,?,?,?,?,?,?,?,?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setInt(1, updatedRow);
          cstm.setString(2,textboxes[0]);
          cstm.setString(3,textboxes[1]);
          cstm.setString(4,textboxes[2]);
          cstm.setString(5,textboxes[3]);
          cstm.setString(6,textboxes[4] );
           cstm.setString(7,textboxes[5] );
           cstm.setString(8,textboxes[6] );
           cstm.setDate(9, updatingDate);
           cstm.setString(10, textboxes[7]);
          
                 
          //Execute the update operation
               
           ResultSet RS=  cstm.executeQuery();
         //Return the data set 
          return  RS;
          
        }
        //End of update Row method
        
        //Method  to update the picture
    /**
     * 
     * @param path
     * @param currentRow
     * @return
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public ResultSet info_picUpdate(String path,int currentRow) throws FileNotFoundException, SQLException
        {
            //Define the input stream to contain the image
            
       InputStream inStr=new FileInputStream(new File(path) );
         
          
          // Call the stored procedure designated for updating info 
         
          CallableStatement cstm=dbconn.prepareCall("{call update_infoPic(?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
           cstm.setBlob(1,inStr );
           cstm.setInt(2,currentRow);
          //Execute the update operation
               
           ResultSet RS=  cstm.executeQuery();
         
         
            return RS;
         }//End of update pic
        
        
        
        
        
        /**
         * 
         * @param deletedRow
         * @return
         * @throws SQLException
         */
        public  ResultSet delete_Row(int deletedRow) throws SQLException
        {
             
            // Call the stored procedure designated for deleting info 
         
          CallableStatement cstm=dbconn.prepareCall("{call delete_row(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setInt(1,deletedRow);
          
          //Execute the deletion operation
               
           ResultSet RS=  cstm.executeQuery();
         //Return the data set 
          return  RS;
          
        }
        //End of delete Row method
        
    
      
       
       
     //method to search of data in the records
        /**
         * 
         * @param criterion
         * @return
         * @throws SQLException
         */
        public ResultSet search_info(String criterion) throws SQLException
       {
         // Call the stored procedure designated for search info 
         
          CallableStatement cstm=dbconn.prepareCall("{call Search_info(?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
                            
          //Execute the update operation
         ResultSet rs= cstm.executeQuery();
         //Return the data set 
                  return rs;
       
       
       }//End search info method
       
       
       //method to search of data in the record dependin on a sent course
       /**
        * 
        * @param criterion
        * @param sub
        * @return
        * @throws SQLException
        */
       public ResultSet search_sub_info(String criterion,String sub) throws SQLException
       {
         // Call the stored procedure designated for search info 
         
          CallableStatement cstm=dbconn.prepareCall("{call search_sub_inf(?,?)}");
          //Setting the in parameteres for the stored procedure in the same order of the procedure
          cstm.setString(1, criterion);
          cstm.setString(2, sub);
                            
          //Execute the update operation
         ResultSet rs= cstm.executeQuery();
         //Return the data set 
                  return rs;
       
       
       }//End search info method 
       
       
       
    
}
