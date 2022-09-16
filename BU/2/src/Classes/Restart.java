/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Frames.frmMainPage;
import java.awt.Toolkit;

/**
 *
 * @author Administrator
 */
public class Restart {
    
  

  public static void main(String[] args) {
      frmMainPage frame1 = new  frmMainPage();

        // Set Icon
       

        frame1.setVisible(true);
        frame1.setSize(600, 700);
        frame1.setTitle("Card Game");

        // Set to exit on close
        frame1.setDefaultCloseOperation( frmMainPage.EXIT_ON_CLOSE);
  
}
    
}
