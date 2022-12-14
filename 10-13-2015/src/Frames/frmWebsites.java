/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;


import Classes.Info_DB_Operations;
import Classes.Useful_websitesTab_operations;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class frmWebsites extends javax.swing.JFrame {

    /**
     * Creates new form frmWebsites
     */
    public frmWebsites() {
        initComponents();
         try {
             //To show the frame in center
             
             this.setLocationRelativeTo(null);
              /*  Dimension dim = frm.getSize();
               this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2);*/
              //To set the image of the frame
          this.setIconImage(new ImageIcon(getClass()
                    .getResource("/Pics/Icons/web.gif")).getImage());
               
          
               setLink();
            
                               UW_RS= UWOp.show_useful_websites();
                            lblTotal1.setText(idbo.show_total(UW_RS));
                            //The first result is first shown 
                       
                          show_data(idbo.RS_first(UW_RS));
                          show_table(UW_RS);
        } catch (SQLException ex) {
            Logger.getLogger(frmWebsites .class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    frmMainPage frm=new frmMainPage();
     ResultSet UW_RS;
        String path;
        Info_DB_Operations  idbo=new Info_DB_Operations ();
         Useful_websitesTab_operations UWOp=new   Useful_websitesTab_operations();
        int index=1;
        
        
         String strURL;
     
             
      //Method to link the uri and open it 
        
           public void setLink(){
           //lblTry.setText("<html><font color=\"#0000CF\"><u><a href= "+text+">Google</a></u></font></html>"); //$NON-NLS-1$ //$NON-NLS-2$
           lblLink.setText(("<html><a href=\" " + strURL + "\">Go to the link </a></html>"));
          
            lblLink.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent me) {
            lblLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
          }
        public void mouseExited(MouseEvent me) {
           lblLink.setCursor(Cursor.getDefaultCursor());
        }
        
        
          public void mouseClicked(MouseEvent me)
        {
          try {
                  
                   URI uri = new URI(strURL);
                    open(uri);
                  try {

                    }
                    catch(Exception e) {
                       System.out.println(e);
                    }
                }
            catch(URISyntaxException ex) {
                   
                  }
                 }
             });
           }
     private static void open(URI uri) {
    if (Desktop.isDesktopSupported()) {
      try {
        Desktop.getDesktop().browse(uri);
      } catch (IOException e) { /* TODO: error handling */ }
      } else { /* TODO: error handling */ }
    }
    //End of linking method
     
     
     //Method to show data once starting the app
      private void show_data(ResultSet rs)
      {
          //Bind the fields of the table with the frame components properly
        try {
              strURL=rs.getString("st_link");
               txtID.setText(rs.getString("st_ID"));
               txtWebsite.setText(rs.getString("st_link"));
               txtDesc.setText(rs.getString("st_details"));
              
               lblPosition1.setText(String.valueOf(index));
            
            
           
        } catch (SQLException ex) {
          
        }
    
      }
      //End of show data method
      
      //Method to show data as  a table
      private void show_table(ResultSet RS) throws SQLException
         
      {
          //Model to fill the jtable by the data from the database table
         DefaultTableModel model=(DefaultTableModel) tblWebsites.getModel();
         //Clear the old data if any
        model.getDataVector().removeAllElements();
      //Put the first data from the database to the jtable
         RS.first();
         model.addRow(new Object[]{RS.getString("st_link")});
         //Then loop and put the next data in the jtable
         while(RS.next())
          {
                     //Selecting the attribute to be stored into the array
           model.addRow(new Object[]{RS.getString("st_link")});
            // add group names to the array list
                          
          }
      
      }
       //End show as a table
      
     
      
      
    //Method to initialize thr form to get the new entry
      private void add_new() throws SQLException
      
      {     
          //Call the method designated for addin new reord
               UWOp.add_new();
              //Then jump to the inserted row
              UW_RS= UWOp.show_useful_websites();
              index=Integer.valueOf(idbo.show_total(UW_RS));
              lblTotal1.setText(String.valueOf(index));
                      
              show_data(idbo.RS_last(UW_RS));
               show_table(UW_RS);
      
      }//End add new method
      
       private void set_update()
      {
         try {
           
            //Bind the values of the components with the parameters of the update method
                      
              String[] textboxes=new String[4];
             
              textboxes[0]= txtWebsite.getText();
              textboxes[1]=txtDesc.getText();
              
            
             
                     //Call the update method
                   UWOp.update_useful_websites(Integer.valueOf(txtID.getText()), textboxes);  
           
            
          
           
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
                    UW_RS=UWOp.show_useful_websites();
                //Call the method regresh to show the new updates in the form instantly
                     
                      show_data( idbo.refresh_data(UW_RS,index)  );
                     
                }
              else
              {      
                    set_update();
                   
                   UW_RS= UWOp.search_useful_websites(txtSearch.getText());
                    //Call the method regresh to show the new updates in the form instantly
                      show_data( idbo.refresh_data(UW_RS,index)  );
                    
              }
                             

        } catch (SQLException ex) { 
          
        }
      
      
      }//End of update data method
      
     
      
      
      //Method to delete a specific row in a record
      private void delete_row() throws SQLException, FileNotFoundException, IOException
      { 
         
         //Call the method designated for deleting rows 
               UWOp.delete_useful_websites(Integer.valueOf(txtID.getText()));
           //then jump to the nearest row     
                UW_RS=UWOp.show_useful_websites();
               index=Integer.valueOf(idbo.show_total(UW_RS));
               lblTotal1.setText(String.valueOf(index));
                   if(UW_RS.next())
                   {show_data(idbo.RS_next(UW_RS));
                    show_table(UW_RS);
                   }
               else
                { index=1;
                   show_data(idbo.RS_first(UW_RS));
                    show_table(UW_RS);
               }
      
      
      }  //End of delete row method
    
    
        
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblTotal1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblPosition1 = new javax.swing.JLabel();
        btnPrevious = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextPane();
        txtWebsite = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblWebsites = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblLink = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Websites");
        setResizable(false);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setToolTipText("Exit");
        jButton3.setContentAreaFilled(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/x1.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 3, 18, 20));

        btnDelete.setToolTipText("Delete this row");
        btnDelete.setBorder(null);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Delete.jpg"))); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 3, 20, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("[Websites]");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 4, -1, -1));

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
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 3, 20, 20));

        btnInsert.setToolTipText("Add new row");
        btnInsert.setBorder(null);
        btnInsert.setContentAreaFilled(false);
        btnInsert.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/new.jpg"))); // NOI18N
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 3, 20, 20));

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
        getContentPane().add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 3, 20, 20));

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
        getContentPane().add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 3, 15, 20));

        lblTotal1.setToolTipText("Total rows");
        getContentPane().add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 3, 40, 20));

        jLabel11.setText("of {");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 3, 20, 20));

        jLabel13.setText("}");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 10, 20));

        lblPosition1.setToolTipText("Current position");
        getContentPane().add(lblPosition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 3, 30, 20));

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
        getContentPane().add(btnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 3, 15, 20));

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
        getContentPane().add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, 20, 20));

        txtDesc.setBackground(new java.awt.Color(235, 142, 33));
        txtDesc.setBorder(null);
        txtDesc.setCaretColor(new java.awt.Color(216, 73, 73));
        jScrollPane1.setViewportView(txtDesc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 140, 130));

        txtWebsite.setBackground(new java.awt.Color(212, 119, 46));
        txtWebsite.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtWebsite.setForeground(new java.awt.Color(250, 250, 14));
        txtWebsite.setBorder(null);
        getContentPane().add(txtWebsite, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 120, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("Descriptipon");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 126, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setText("Web site :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 74, -1, -1));

        txtID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 40, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("ID :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, -1, -1));

        tblWebsites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Website name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWebsites.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblWebsitesMouseClicked(evt);
            }
        });
        tblWebsites.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblWebsitesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblWebsites);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 170, 190));

        txtSearch.setToolTipText("Search for an ID or a text");
        txtSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 130, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Searchicon_1.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 77, -1, 20));

        lblLink.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getContentPane().add(lblLink, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 342, 100, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/WSBG.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 456, 350));

        jButton4.setToolTipText("Minimize");
        jButton4.setContentAreaFilled(false);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/btnMin.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 3, 18, 20));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Bar 2-2.jpg"))); // NOI18N
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

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
                        show_table(UW_RS);

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
            show_table(UW_RS);
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        //call the method designated for adding row
        try {
            add_new();
            show_table(UW_RS);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        //Call update data to save changes made in the row

        try {
            update_frame();

            index = Integer.valueOf(lblTotal1.getText());
            show_data(idbo.RS_last(UW_RS));

            show_table(UW_RS);


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
                show_data(idbo.RS_next(UW_RS));
                show_table(UW_RS);
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
                show_data(idbo.RS_previous(UW_RS));
                show_table(UW_RS);
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

            //Show the data existing in the first item record
            show_data(idbo.RS_first(UW_RS));
            show_table(UW_RS);
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
            if ("".equals(txtSearch.getText())) {
                index = 1;
                UW_RS = UWOp.show_useful_websites();
                lblTotal1.setText(idbo.show_total(UW_RS));

                show_data(idbo.RS_first(UW_RS));
                show_table(UW_RS);
            } else {
                //otherwise show the data according to the text enetered by the users
                index = 1;
                UW_RS = UWOp.search_useful_websites(txtSearch.getText());
                lblTotal1.setText(idbo.show_total(UW_RS));

                show_data(idbo.RS_first(UW_RS));
                show_table(UW_RS);

            }
        } catch (SQLException ex) {
           
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tblWebsitesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblWebsitesMouseClicked
      
        //Select the index where the selected row
        int i = tblWebsites.getSelectedRow();
                   index=i+1    ; 
                   update_frame();
    }//GEN-LAST:event_tblWebsitesMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblWebsitesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblWebsitesKeyReleased
      //Select the index where the selected row
        int i = tblWebsites.getSelectedRow();
                   index=i+1    ; 
                   update_frame();       
    }//GEN-LAST:event_tblWebsitesKeyReleased

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
            java.util.logging.Logger.getLogger(frmWebsites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmWebsites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmWebsites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmWebsites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frmWebsites().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLink;
    private javax.swing.JLabel lblPosition1;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JTable tblWebsites;
    private javax.swing.JTextPane txtDesc;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtWebsite;
    // End of variables declaration//GEN-END:variables
}
