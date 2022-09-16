/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Info_DB_Operations {
     
    
    
    //Method used to show data in the fist record
      
    /**
     * 
     * @param RS
     * @return
     * @throws SQLException
     */
    public ResultSet RS_first(ResultSet RS) throws SQLException
      { 
          //Call the function first of the senet result set
         RS.first();
         return (RS);
      
      }
      //End of first record show
      
      //Method used to show data in the next records
      /**
       * 
       * @param RS
       * @return
       * @throws SQLException
       */
      public ResultSet RS_next(ResultSet RS) throws SQLException //,int current_ID  ) throws SQLException
      {
          //Call the function next of the senet result set
           RS.next();
          return (RS);
          
      
      
      } //End of next record show*/
      
      
       //Method used to show data in the next records
      /**
       * 
       * @param RS
       * @return
       * @throws SQLException
       */
      public ResultSet RS_previous(ResultSet RS) throws SQLException
      {
         //Call the function previous of the senet result set
         RS.previous();
          return (RS);
      
      
      } //End of previous record show
      
      /**
       * 
       * @param RS
       * @return
       * @throws SQLException
       */
      public ResultSet RS_last(ResultSet RS) throws SQLException
      { 
          //Call the function last of the senet result set
           RS.last();
         return (RS);
      
      
      } //End of last record show
       
       
       //Method to show the number of a search result
       
       /**
        * 
        * @param RS
        * @return
        * @throws SQLException
        */
       public String show_total(ResultSet RS) throws SQLException
       {
           int counter=0;
          while(RS.next())
            counter++;
        return (String.valueOf(counter));
       
        
       }
       
       //End show total method
       
       //Method that refresh the data set after updating
       
       /**
        * 
        * @param RS
        * @param currentIndex
        * @return
        * @throws SQLException
        */
       public ResultSet refresh_data(ResultSet RS ,int currentIndex) throws SQLException
       {
           //Sicne the update is refreshed only after the show data is recalled,we initialize the var num to be zero 
          
         int num=0;
         //Then we start looking for the updated row in the show data by looping the num var until the sent index(the update row's position)
         
         while(num<currentIndex)
         {
            if(RS.next()) 
             num++;
         
         }
         
         return(RS);
       
       
       }//End of refresh method
       
       
        
       
    
      
     
    
}
 