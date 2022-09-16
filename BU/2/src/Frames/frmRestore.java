/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Administrator
 */


public class frmRestore extends javax.swing.JFrame {

    /**
     * Creates new form frmRestore
     */
    public frmRestore() {
        initComponents();
         //To set the image of the frame
          this.setIconImage(new ImageIcon(getClass()
                    .getResource("/Pics/Icons/restore.gif")).getImage());
        
        //To set the background
        
         ImageIcon myImg = new  ImageIcon(getClass().getResource("/Pics/RestoreBG.jpg"));
              lblBG.setIcon(ResizeImage(myImg,lblBG));
        
        
    }
  String path;
  //int num = 0;
  int num = 0;
  private Timer t=null;
   
    
   
  
    public void iterate()  {
       //Create new thread to show the values in the progress bar without creating this thread the update of the progress bar won't work     

    // Runs outside of the Swing UI thread
    new Thread(new Runnable() {
       public void run() {
          //call the restore method
          // Restoredbfromsql();
          //Loop to update the progress bar value
        for ( i= 0; i <= 100; i++) {

          // Runs inside of the Swing UI thread
          SwingUtilities.invokeLater(new Runnable() {
            public void run() {
             prgbarRestore.setValue(i);
            }
          });

          try {
            java.lang.Thread.sleep(100);
          }
          catch(Exception e) { }
        }
         //When done show msg informing the usr the restore was done
         JOptionPane.showMessageDialog(null, "Restore was successfullt done", "Retore succeeded", JOptionPane.INFORMATION_MESSAGE); 
      }
    }).start();
          
         /*   try {
           
          

       
       
       
       /* t=new Timer(100,new ActionListener(){

           @Override
           public void actionPerformed(ActionEvent e) {
               num++;
                prgbarRestore.setValue(num);
                if(prgbarRestore.getValue()<100)
                     prgbarRestore.setValue(prgbarRestore.getValue()+1); 
           }
        
        
               
        
        })  ;
        } catch (InterruptedException ex) {
            Logger.getLogger(frmRestore.class.getName()).log(Level.SEVERE, null, ex);
        }
               }*/
           
               
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        Server = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Server1 = new javax.swing.JLabel();
        Server2 = new javax.swing.JLabel();
        txtRestorePath = new javax.swing.JTextField();
        txtServerName = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        prgbarRestore = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        lblBG = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restore");
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Server.setText("Password :");
        getContentPane().add(Server, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 70, 20));

        btnRestore.setText("Restore");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestore, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 110, -1));

        jLabel4.setText("Retore from :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, 20));

        Server1.setText("Server :");
        getContentPane().add(Server1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 50, 20));

        Server2.setText("User name :");
        getContentPane().add(Server2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 70, 20));

        txtRestorePath.setText("D:/a.sql");
        getContentPane().add(txtRestorePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 260, 30));

        txtServerName.setEditable(false);
        txtServerName.setText("localhost");
        getContentPane().add(txtServerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 260, 30));

        txtUserName.setText("Shaker");
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 260, 30));

        jButton2.setText("jButton1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 30, -1));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 260, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/x1.jpg"))); // NOI18N
        jButton4.setToolTipText("Close");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 30, 20));

        prgbarRestore.setStringPainted(true);
        getContentPane().add(prgbarRestore, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 390, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("[Restore]");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 70, -1));
        getContentPane().add(lblBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     */
    public  void Restoredbfromsql() {
         String userName=txtUserName.getText();
         String userPassword=txtPassword.getText();
         String restorePath=txtRestorePath.getText();
         
         
         
        try { 
            Process exec = null;
          try {
              exec = Runtime.getRuntime().exec("/C:/wamp/bin/mysql/mysql5.5.24/bin/mysql -u"+userName+" -p"+userPassword+" info_store -e\" source "+ restorePath);
          } catch (IOException ex) {
             
          }
        
                   
             
                      //Wait for the command to complete, and check if the exit value was 0 (success)
                      if(exec.waitFor()==0)
                      {
                          //normally terminated, a way to read the output
                          InputStream inputStream = exec.getInputStream();
                          byte[] buffer = null;
              try {
                  buffer = new byte[inputStream.available()];
                   
              } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, ex, "Alert", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Sorry restore failed", "Retore failed", JOptionPane.INFORMATION_MESSAGE);
              }
              try {
                   inputStream.read(buffer);
                  
                 
              } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, ex, "Alert", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Sorry restore failed", "Retore failed", JOptionPane.INFORMATION_MESSAGE);
              }

                          String str = new String(buffer);
                          System.out.println(str);
                          //If no problem show update the progress bar
                           iterate();
                      }
                      else
                      {
                          // abnormally terminated, there was some problem
                                      //a way to read the error during the execution of the command
                          InputStream errorStream = exec.getErrorStream();
                          byte[] buffer = null;
              try {
                  buffer = new byte[errorStream.available()];
              } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, ex, "Alert", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Sorry restore failed", "Retore failed", JOptionPane.INFORMATION_MESSAGE);
              }
              try {
                  errorStream.read(buffer);
              } catch (IOException ex) {
                 JOptionPane.showMessageDialog(null, ex, "Alert", JOptionPane.INFORMATION_MESSAGE);
                   JOptionPane.showMessageDialog(null, "Sorry restore failed", "Retore failed", JOptionPane.INFORMATION_MESSAGE);
              }

                          String str = new String(buffer);
                          System.out.println(str);
                          JOptionPane.showMessageDialog(null, str, "Retore failed", JOptionPane.INFORMATION_MESSAGE);
                      }
                
                      
          } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex, "Alert", JOptionPane.INFORMATION_MESSAGE);
              JOptionPane.showMessageDialog(null, "Sorry restore failed", "Retore failed", JOptionPane.INFORMATION_MESSAGE);
        }
                              
                              
                              
        
    }
    
    //to run the wamp server
     private ImageIcon ResizeImage(ImageIcon myimg,JLabel lbl)
       {
     //Fit the inserted picture with  the size of the label
        java.awt.Image Img=myimg.getImage();
        java.awt.Image newImg=Img.getScaledInstance(lbl.getWidth(), lbl.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image=new ImageIcon(newImg);
        
        return image;
       
        
    }//End of resize image 
    
     int i=0;
    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
          
        
        
        if(!"".equals(txtUserName.getText()) &&!"".equals(txtPassword.getText()))
         {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to restore \n Once you restore all changes after the restore will be erased ?","Warning", JOptionPane.YES_NO_CANCEL_OPTION);
            if(result == JOptionPane.YES_OPTION)
            {                               
                Restoredbfromsql();
            }
       
         
            }
        else if ("".equals(txtUserName.getText()))
            JOptionPane.showMessageDialog(null, "User name field is empty", "Validation Error", JOptionPane.INFORMATION_MESSAGE);
         else if ("".equals(txtPassword.getText()))
            JOptionPane.showMessageDialog(null, "User password field is empty", "Validation Error", JOptionPane.INFORMATION_MESSAGE);
       
        
         
            
            
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.sql","sql");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File SelectedFile = fileChooser.getSelectedFile();
           path = SelectedFile.getAbsolutePath();
           txtRestorePath.setText(path);
        }      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmRestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new frmRestore().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Server;
    private javax.swing.JLabel Server1;
    private javax.swing.JLabel Server2;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblBG;
    private javax.swing.JProgressBar prgbarRestore;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtRestorePath;
    private javax.swing.JTextField txtServerName;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
/* class ProgressBar
 {

    
    
    
    
      JProgressBar current;
    
    int num = 0;
    
    public ProgressBar() {
       
        
        JPanel pane = new JPanel();
        pane.setLayout(new FlowLayout());
        current = new JProgressBar(0, 2000);
        current.setValue(0);
        current.setStringPainted(true);
        pane.add(current);
       
      
    }
    
    
    public void iterate() {
        
       current.setStringPainted(true);
        while (num < 2000) {
            current.setValue(num);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            num += 95;
        }
    }
    
}
*/