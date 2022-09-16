/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;


import Classes.InfoTab_operations;
import Classes.Info_DB_Operations;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author Administrator
 */
public class frmImgViewer extends javax.swing.JFrame {
     private static int SCALE_SMOOTH;
    /**
     * Creates new form Image_Viewer
     * @throws SQLException 
     */
    
   
    public frmImgViewer() throws SQLException {
        
         //To set the image of the frame
          this.setIconImage(new ImageIcon(getClass()
                    .getResource("/Pics/Icons/img.gif")).getImage());
        initComponents();
        //To show the frame in center
                this.setLocationRelativeTo(null);
        
        
        
        //When the form is opened ,the  information table's data is shown    
                   Inf_RS=infOp.Show_Data();
                    //Call the method designated for counting the total of records ans set the total lablel with the result
                    lblTotal.setText(idbo.show_total(Inf_RS));
                  //The first result is first shown 
                  
        
             myImg=show_image(idbo.RS_first(Inf_RS));
            h =250 ;
        //</editor-fold>
          w = 648;
         ResizeImage(myImg);
        
                  
      
        
       // txtTry.setText(infpro.var +" ");
        
    }
          ResultSet Inf_RS;
         InfoTab_operations  infOp= new InfoTab_operations();
         Info_DB_Operations  idbo=new Info_DB_Operations ();
            int h,w;
           int  index=1;
    
    //Instance of the db connection class
            
      
       ImageIcon myImg;
      
       private ImageIcon show_image(ResultSet RS) throws SQLException 
         {      
          
       
         ImageIcon format = null;
        try {
            
             lblPosition.setText(String.valueOf(index));
            if( RS.getBytes("info_pic")!=null)
            {
             byte[] imageData =  RS.getBytes("info_pic");
            format = new ImageIcon(imageData);
            
            
            }
            else
            { 
                ImageIcon miImg = new  ImageIcon(getClass().getResource("/Pics/Default.png"));
              format=miImg;}
        } catch (SQLException ex) {
            
        }
       
        return format;
      
      }
      //End of update image method 
       
       
                
       
        //Mtethod to resize sent image
   private ImageIcon ResizeImage(ImageIcon myimg)
    {
     //Fit the inserted picture with  the size of the label
        java.awt.Image img=myimg.getImage();
        java.awt.Image newImg=img.getScaledInstance(w, h,java.awt.Image.SCALE_SMOOTH);
        ImageIcon image=new ImageIcon(newImg);
          lblPic1.setIcon(image);
          lblSize.setText("w: "+w +"   h:"+h);
        return image;
       
    
    
    }//End of resize image
       
       
       
  
    /*   
      public void rotation() 
               
       {  
       
            
            ImageIcon myimg=new ImageIcon(imageData);
           
            Image format = myimg.getImage();
           
              Graphics2D g = (Graphics2D)format.getGraphics();
            
              Graphics2D g2 = (Graphics2D) g;
               
                //g2.rotate(Math.PI / 4,format.getWidth()/ 2, format.getHeight() / 2);
                //g2.drawImage(myimg, 0, 0, null);
               
             
            

            
            // The required drawing location
 /*int drawLocationX = 300;
 int drawLocationY = 300;
          ImageIcon myimg=new ImageIcon(imageData);
           Image format = myimg.getImage();
 // Rotation information

 /*double rotationRequired = Math.toRadians (45);
 double locationX = image.getWidth() / 2;
 double locationY = image.getHeight() / 2;
   BufferedImage image = new BufferedImage(w, h, format);
 AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
 AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

 // Drawing the rotated image at the required drawing locations
 g2d.drawImage(op.filter(format, null), drawLocationX, drawLocationY, null);*/
            
            /*/ImageIcon myimg=new ImageIcon(imageData);
           Image format = myimg.getImage();
        BufferedImage dimg = new BufferedImage(w, h, format);
          Graphics2D g = dimg.createGraphics();
     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
             RenderingHints.VALUE_ANTIALIAS_ON);

     g.rotate(Math.toRadians(angle), w / 2, h / 2);

     g.drawImage(img, null, 0, 0);
      
       
       }
*/
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new javax.swing.JTextField();
        lblPosition = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblPic1 = new javax.swing.JLabel();
        lblPic = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        lblPosition1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblSize = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image_viewer");
        setLocationByPlatform(true);
        setType(java.awt.Window.Type.POPUP);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.setToolTipText("Search data");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 5, 140, -1));
        txtSearch.getAccessibleContext().setAccessibleName("");

        lblPosition.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblPosition.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 11, 30, 20));

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 20, 20));

        lblPic1.setBackground(new java.awt.Color(10, 7, 2));
        getContentPane().add(lblPic1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1110, 650));

        lblPic.setBackground(new java.awt.Color(10, 7, 2));
        getContentPane().add(lblPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1110, 530));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(":");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 13, -1, -1));

        btnNext.setToolTipText("Move next ");
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 30, 20));

        btnPrevious.setToolTipText("Move previous");
        btnPrevious.setBorderPainted(false);
        btnPrevious.setContentAreaFilled(false);
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 30, 20));

        btnZoomIn.setToolTipText("Zoom in");
        btnZoomIn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnZoomIn.setBorderPainted(false);
        btnZoomIn.setContentAreaFilled(false);
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });
        getContentPane().add(btnZoomIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 40, 20));

        btnZoomOut.setToolTipText("Zoom out");
        btnZoomOut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnZoomOut.setBorderPainted(false);
        btnZoomOut.setContentAreaFilled(false);
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });
        getContentPane().add(btnZoomOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 40, 20));

        lblPosition1.setForeground(new java.awt.Color(255, 255, 255));
        lblPosition1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/SearchIcon1.jpg"))); // NOI18N
        lblPosition1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lblPosition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 6, 30, 20));

        jButton1.setToolTipText("Close the frame");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/x1.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 20, 20));

        lblSize.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblSize.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 100, 20));

        jButton4.setToolTipText("Minimize");
        jButton4.setContentAreaFilled(false);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/btnMin.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 20, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Image_bar.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
     
    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
       
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
                      
            try {
                //if not then decrement the index to the previous item in the record
                 if(index>1)
                  {
                     index--;
                     
                     
                 myImg=show_image(idbo.RS_previous(Inf_RS));
                  h = 250;
                //</editor-fold>
                 w = 648;
               ResizeImage(myImg);
                  }
            } catch (SQLException ex) {
                Logger.getLogger(frmImgViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
          
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

                        
                try {
                //if not then decrement the index to the previous item in the record
                if(index<(Integer.valueOf(lblTotal.getText())))
                     {   //if not then decrement the index to the next item in the record
                           index++;
                     
                     
                 myImg=show_image(idbo.RS_next(Inf_RS));
                  h = 250;
                //</editor-fold>
                 w = 648;
               ResizeImage(myImg);
                  }
            } catch (SQLException ex) {
                Logger.getLogger(frmImgViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
                           
                         
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
       
            try {
                 //check if the search text is empty then show the all data
                   if("".equals(txtSearch.getText()))
                    {   index=1;
                        Inf_RS=infOp.Show_Data();
                        lblTotal.setText(idbo.show_total(Inf_RS));
               
                      myImg=show_image(idbo.RS_first(Inf_RS));
                   h = 250;
                //</editor-fold>
                  w = 648;
                 ResizeImage(myImg);
                     }
                  else
                     {
                            //otherwise show the data according to the text enetered by the users
                      index=1;
                       Inf_RS= infOp.search_info(txtSearch.getText());
                       lblTotal.setText(idbo.show_total(Inf_RS));
                       myImg=show_image(idbo.RS_first(Inf_RS));
                  h = 250;
                //</editor-fold>
                 w = 648;
               ResizeImage(myImg);                             
                     }
            } catch (SQLException ex) {
              
            }
           
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
       if (h < lblPic1.getHeight() && w < lblPic1.getWidth()) {
            h += 75;
            w += 75;
            ResizeImage(myImg);

        }
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
       if (h > 75 && w > 75) {
            h -= 75;
            w -= 75;
            ResizeImage(myImg);

        }
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setState(JFrame.ICONIFIED);
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
            java.util.logging.Logger.getLogger(frmImgViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmImgViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmImgViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmImgViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new frmImgViewer().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frmImgViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblPic;
    private javax.swing.JLabel lblPic1;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblPosition1;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
