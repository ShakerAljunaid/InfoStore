/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;


import Classes.ContactTab_operations;
import Classes.Info_DB_Operations;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class frmContacts extends javax.swing.JFrame {

    /**
     * Creates new form frmContacts
     */
    public frmContacts() {
        try {
            initComponents();
            
             //To set the image of the frame
          this.setIconImage(new ImageIcon(getClass()
                    .getResource("/Pics/Icons/cons.gif")).getImage());
            
            //When the form is opened ,the subject table's data is shown           
                con_RS=conOp.show_Contacts();
               //Call the method designated for counting the total of records ans set the total lablel with the result
                lblTotal1.setText(idbo.show_total(con_RS));
                //The first result is first shown 
                 show_data(idbo.RS_first(con_RS));
                 show_table(con_RS);
                
               
                
        } catch (SQLException ex) {
            Logger.getLogger(frmContacts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     //Initializing the global vars and methods that are used through the program
        ResultSet con_RS;
        String path;
        Info_DB_Operations  idbo=new Info_DB_Operations ();
        ContactTab_operations conOp=new   ContactTab_operations();
        int index=1;

    
     //Method to show data once starting the app
      private void show_data(ResultSet rs)
      {
          //Bind the fields of the table with the frame components properly
        try {
            
             txtID.setText(rs.getString("Contact_ID"));
             txtName.setText(rs.getString("Contact_Name"));
             txtDesc.setText(rs.getString("Description"));
             txtNum.setText(rs.getString("Contact_number"));
             txtEmail.setText(rs.getString("Email"));
             lblPosition1.setText(String.valueOf(index));
             show_image();
            
           
        } catch (SQLException ex) {
            
        }
    
      }
      //End of show data method
      
      //Method to show data as  a table
      private void show_table(ResultSet RS) throws SQLException
         
      {
          //Model to fill the jtable by the data from the database table
         DefaultTableModel model=(DefaultTableModel) tblContact.getModel();
         //Clear the old data if any
        model.getDataVector().removeAllElements();
      //Put the first data from the database to the jtable
         RS.first();
         model.addRow(new Object[]{RS.getString("Contact_Name"),RS.getString("Contact_number"),RS.getString("Email")});
         //Then loop and put the next data in the jtable
         while(RS.next())
          {
                     //Selecting the attribute to be stored into the array
            model.addRow(new Object[]{RS.getString("Contact_Name"),RS.getString("Contact_number"),RS.getString("Email")});
            // add group names to the array list
                          
          }
      
      }
       //End show as a table
      
      
    //Method to initialize thr form to get the new entry
      private void add_new() throws SQLException
      {      //Call the method designated for addin new reord
               conOp.add_new();
              //Then jump to the inserted row
              con_RS= conOp.show_Contacts();
              index=Integer.valueOf(idbo.show_total(con_RS));
              lblTotal1.setText(String.valueOf(index));
               lblPic.setIcon(null);           
              show_data(idbo.RS_last(con_RS));
             show_table(con_RS);
      
      }//End add new method
      
       private void set_update()
      {
         try {
           
            //Bind the values of the components with the parameters of the update method
                      
              String[] textboxes=new String[3];
             
              textboxes[0]= txtName.getText();
              textboxes[1]=txtEmail.getText();
              textboxes[2]=txtDesc.getText();
              
            
              if("".equals(txtNum.getText()))
                     //Call the update method
                   conOp.update_Contacts(Integer.valueOf(txtID.getText()), textboxes,+967);  
           
              else
                 conOp.update_Contacts(Integer.valueOf(txtID.getText()), textboxes,Integer.valueOf(txtNum.getText()));
            
          
           
           } catch (SQLException ex) {JOptionPane.showMessageDialog(null,ex);   
        }
      
      }//End of update method 
      
      
      //Method to update the data 
       /**
        * 
        */
       public void update_frame() 
      {
      try { 
             //When we update first we check if the result set to updated is either search or show data
              
              if("".equals(txtSearch.getText()))
                 { 
                      set_update();
                     con_RS=conOp.show_Contacts();
                //Call the method regresh to show the new updates in the form instantly
                     
                      show_data( idbo.refresh_data(con_RS,index)  );
                     
                }
              else
              {      
                    set_update();
                   
                    con_RS= conOp.search_Contacts(txtSearch.getText());
                    //Call the method regresh to show the new updates in the form instantly
                      show_data( idbo.refresh_data(con_RS,index)  );
                 
              }
                             

        } catch (SQLException ex) { 
          
        }
      
      
      }//End of update data method
      
      
      //Method to update the pic if any
      private ImageIcon show_image() 
      {
         byte[] imageData;
         ImageIcon format = null;
        try {
            if(con_RS.getBytes("Contact_pic")!=null)
            {
            imageData = con_RS.getBytes("Contact_pic");
            format = new ImageIcon(imageData);
            lblPic.setIcon(ResizeImage(format,lblPic));
            }
            else
            { ImageIcon myImg = new  ImageIcon(getClass().getResource("/Pics/Default.png"));
               lblPic.setIcon( ResizeImage(myImg,lblPic));}
        } catch (SQLException ex) {
           
        }
       
        return format;
      
      }
      //End of update image method 
      
       //Method to allow the user to chhose an image to be saved in the database
      private void setImage()
      {
   //
       JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "png", "gif");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File SelectedFile = fileChooser.getSelectedFile();
           path = SelectedFile.getAbsolutePath();
            ImageIcon myImg = new ImageIcon(path);
            //Call resize image method to fit the chosen image with the label pic
            lblPic.setIcon((ResizeImage(myImg,lblPic)));
        }
       
   }//End of choosing pic method
     //Mtethod to resize sent image
   private ImageIcon ResizeImage(ImageIcon myimg,JLabel lbl)
    {
     //Fit the inserted picture with  the size of the label
        java.awt.Image Img=myimg.getImage();
        java.awt.Image newImg=Img.getScaledInstance(lbl.getWidth(), lbl.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image=new ImageIcon(newImg);
        
        return image;
       
    
    
    }//End of resize image
      
      
      
      //Method to delete a specific row in a record
      private void delete_row() throws SQLException, FileNotFoundException, IOException
      { 
         
         //Call the method designated for deleting rows 
               conOp.delete_Contact(Integer.valueOf(txtID.getText()));
           //then jump to the nearest row     
               con_RS=conOp.show_Contacts();
               index=Integer.valueOf(idbo.show_total(con_RS));
               lblTotal1.setText(String.valueOf(index));
                   if(con_RS.next())
                show_data(idbo.RS_next(con_RS));
               else
                { index=1;
                   show_data(idbo.RS_first(con_RS));
               }
      
      
      }  //End of delete row method
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        txtNum = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        lblPic = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblContact = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblPosition1 = new javax.swing.JLabel();
        btnPrevious = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnInsert1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnInsert2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        jTabbedPane1.addTab("tab1", jLabel1);

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Contacts");
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtID.setEditable(false);
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 120, -1));

        txtSearch.setToolTipText("Search for an ID or a text");
        txtSearch.setBorder(null);
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
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 55, 210, -1));
        getContentPane().add(txtNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 180, -1));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 180, -1));

        jLabel3.setText("Email :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel4.setText("ID :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel5.setText("Name :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 40, 20));

        jLabel7.setText("Picture :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel8.setText("Phone num :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        txtDesc.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDesc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 190, 80));

        lblPic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 102, 0)));
        lblPic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPicMouseClicked(evt);
            }
        });
        getContentPane().add(lblPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 180, 80));

        jLabel10.setText("Description :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 180, -1));

        tblContact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Phone num", "Email"
            }
        ));
        tblContact.setEnabled(false);
        jScrollPane2.setViewportView(tblContact);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 280, 360));

        btnDelete.setToolTipText("Delete this row");
        btnDelete.setBorder(null);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Delete.jpg"))); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 22, 18, 20));

        btnUpdate.setToolTipText("update this row");
        btnUpdate.setBorder(null);
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.setInheritsPopupMenu(true);
        btnUpdate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Save 1.jpg"))); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 22, 20, 20));

        btnLast.setToolTipText("Move lats");
        btnLast.setBorder(null);
        btnLast.setBorderPainted(false);
        btnLast.setContentAreaFilled(false);
        btnLast.setRequestFocusEnabled(false);
        btnLast.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Last.jpg"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        getContentPane().add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 22, 20, 20));

        btnNext.setToolTipText("Move next");
        btnNext.setBorder(null);
        btnNext.setContentAreaFilled(false);
        btnNext.setPreferredSize(new java.awt.Dimension(39, 23));
        btnNext.setRequestFocusEnabled(false);
        btnNext.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/next.jpg"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 22, 15, 20));

        jLabel13.setText("}");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 10, 20));

        lblTotal1.setToolTipText("Total rows");
        getContentPane().add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 40, 20));

        jLabel11.setText("of {");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 20, 20));

        lblPosition1.setToolTipText("Current position");
        getContentPane().add(lblPosition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 30, 20));

        btnPrevious.setToolTipText("Move previous");
        btnPrevious.setBorder(null);
        btnPrevious.setContentAreaFilled(false);
        btnPrevious.setPreferredSize(new java.awt.Dimension(39, 23));
        btnPrevious.setRequestFocusEnabled(false);
        btnPrevious.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Previous.jpg"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 22, 20, 20));

        btnFirst.setBackground(new java.awt.Color(204, 255, 51));
        btnFirst.setToolTipText("Move first");
        btnFirst.setBorder(null);
        btnFirst.setContentAreaFilled(false);
        btnFirst.setPreferredSize(new java.awt.Dimension(39, 23));
        btnFirst.setRequestFocusEnabled(false);
        btnFirst.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/First.jpg"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        getContentPane().add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 22, 20, 20));

        btnInsert1.setToolTipText("Add new row");
        btnInsert1.setBorder(null);
        btnInsert1.setContentAreaFilled(false);
        btnInsert1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/new.jpg"))); // NOI18N
        btnInsert1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsert1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsert1, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 24, 20, 20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Bar 2-2.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 282, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("[Contacts]");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 80, -1));

        jButton3.setToolTipText("Exit");
        jButton3.setContentAreaFilled(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/x1.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 20, 20));

        btnInsert2.setToolTipText("Add new row");
        btnInsert2.setBorder(null);
        btnInsert2.setContentAreaFilled(false);
        btnInsert2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/new.jpg"))); // NOI18N
        btnInsert2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsert2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsert2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 20, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/cONTACT.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        //Check if the row is not the first ,if it is then disable the user to delete it
        if (Integer.valueOf(txtID.getText()) == 1) {
            btnDelete.enable(false);
        } //if not then call the method delete row
        else {
            //Alert the users before deleting by poping up an option message
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row ?", "Alert", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    try {
                        delete_row();
                          show_table(con_RS);
                    } catch (FileNotFoundException ex) {
                       
                    } catch (IOException ex) {
                       
                    }
                } catch (SQLException ex) {
                }
            }

        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            //Call the method designated for update
               update_frame();
              show_table(con_RS);
           } catch (SQLException ex) {
            Logger.getLogger(frmContacts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        //Call update data to save changes made in the row

        try {
            update_frame();
               show_table(con_RS);
            index = Integer.valueOf(lblTotal1.getText());
            show_data(idbo.RS_last(con_RS));
            
            


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        //Set the index to the last item in the record
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed


        //Check if the index is not the last item 

        try {

            //Call update data to save changes made in the row
            update_frame();
            if (index < (Integer.valueOf(lblTotal1.getText()))) {   //if not then decrement the index to the next item in the record
                index++;
                //Show the data of the next record
                show_data(idbo.RS_next(con_RS));
                  show_table(con_RS);
            }
            //show_image();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }




    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed

        try {
            //Call update data to save changes made in the row
                update_frame();
             

            if (index > 1) {
                //if not then decrement the index to the previous item in the record
                index--;
                show_data(idbo.RS_previous(con_RS));
                  show_table(con_RS);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }




    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed


        try {
            index = 1;
            //Call update data to save changes made in the row
               update_frame();
               show_table(con_RS);
            //Show the data existing in the first item record
            show_data(idbo.RS_first(con_RS));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }





    }//GEN-LAST:event_btnFirstActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

            try {
                 //check if the search text is empty then show the all data
                   if("".equals(txtSearch.getText()))
                    {   index=1;
                        con_RS=conOp.show_Contacts();
                        lblTotal1.setText(idbo.show_total(con_RS));
                                            
                         show_data(idbo.RS_first(con_RS));
                         show_table(con_RS);
                     }
                  else
                     {
                            //otherwise show the data according to the text enetered by the users
                      index=1;
                      con_RS= conOp.search_Contacts(txtSearch.getText());
                       lblTotal1.setText(idbo.show_total(con_RS));
                                             
                       show_data(idbo.RS_first(con_RS)); 
                        show_table(con_RS);
                     }
            } catch (SQLException ex) {
               
            }
                   // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnInsert1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsert1ActionPerformed
 //call the method designated for adding row
        try {
            add_new();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsert1ActionPerformed

    private void lblPicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPicMouseClicked
       //If user clicks the mouse twice then make him choose an image and insert it to the database
        if (evt.getClickCount() == 2)         
      { try {
            setImage();
            conOp.con_picUpdate(path,Integer.valueOf(txtID.getText()));
        } catch (FileNotFoundException | SQLException ex) {
          
        }
      }
      
    }//GEN-LAST:event_lblPicMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnInsert2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsert2ActionPerformed
      try {
            add_new();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        
    }//GEN-LAST:event_btnInsert2ActionPerformed

    /**
     * @param args the command line arguments
     * 
     * 
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
            java.util.logging.Logger.getLogger(frmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmContacts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frmContacts().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert1;
    private javax.swing.JButton btnInsert2;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblPic;
    private javax.swing.JLabel lblPosition1;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JTable tblContact;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNum;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
