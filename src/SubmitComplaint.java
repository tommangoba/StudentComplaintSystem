import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author RODELYN MANZO
 */
public class SubmitComplaint extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SubmitComplaint.class.getName());
    
    private String username;
    private String studentId;
    private String uploadedImageName = "";
    /**
     * Creates new form SubmitComplaint
     */
    public SubmitComplaint(String username) {
        initComponents();
        
        this.username = username;
    submitusername.setText("Welcome, " + username + "!");
    setupNavigation();
    
        // Student ID placeholder
    addPlaceholder(studenttxt, "Student ID");

    // Location placeholder
    addPlaceholder(locationtxt, "Location/Room Number");

    // Image URL placeholder
    addPlaceholder(imagetxt, "Image URL (Optional)");

    // Description placeholder
    addPlaceholder(descriptiontxt, "Description of Issue");
    
    submitusername.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

submitusername.addMouseListener(new java.awt.event.MouseAdapter() {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {

        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to logout?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            dispose();

            LoginPage login = new LoginPage();
            login.setVisible(true);
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        submitusername.setForeground(new java.awt.Color(220, 220, 220));
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent evt) {
        submitusername.setForeground(new java.awt.Color(255, 255, 255));
    }
});
    imagetxt.setEditable(false);
    imagetxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    imagetxt.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            openImageChooser();
            
        }
    });
}
    private void openImageChooser() {

    JFileChooser chooser = new JFileChooser();

    chooser.setFileFilter(
        new javax.swing.filechooser.FileNameExtensionFilter(
            "Image Files",
            "jpg",
            "jpeg",
            "png"
        )
    );

    int result = chooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {

        try {

            java.io.File selectedFile = chooser.getSelectedFile();

            uploadedImageName = selectedFile.getName();

            java.io.File destination =
                new java.io.File("uploads/" + uploadedImageName);

            java.nio.file.Files.copy(
                selectedFile.toPath(),
                destination.toPath(),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            imagetxt.setForeground(java.awt.Color.BLACK);

            imagetxt.setText(uploadedImageName);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Error uploading image!"
            );
            
            e.printStackTrace();
        }
    }
}
    
    
    private String getLoggedInStudentId() {
    // TODO: Replace with actual login session value
    // This should come from your login page
    return studentId; // Change to actual student ID
}
private void goToMyComplaints() {
     String studentId = studenttxt.getText().trim();

    MyComplaintsPage myPage =
            new MyComplaintsPage(username, studentId);

    myPage.setVisible(true);

    this.dispose();
}
    private void setupNavigation() {
       
        jLabelMyComplaints.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMyComplaints.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToMyComplaints();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMyComplaints.setForeground(new java.awt.Color(66, 103, 238));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMyComplaints.setForeground(new java.awt.Color(102, 102, 102));
            }
        });
    }
// Reusable method for placeholders
private void addPlaceholder(javax.swing.JTextField field, String placeholder) {
    field.setForeground(Color.GRAY);
    field.setText(placeholder);

    field.addFocusListener(new java.awt.event.FocusListener() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (field.getText().equals(placeholder)) {
                field.setText("");
                field.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (field.getText().isEmpty()) {
                field.setText(placeholder);
                field.setForeground(Color.GRAY);
            }
        }
    });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        submitusername = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        studenttxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabelMyComplaints = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        locationtxt = new javax.swing.JTextField();
        imagetxt = new javax.swing.JTextField();
        classroomcb = new javax.swing.JComboBox<>();
        descriptiontxt = new javax.swing.JTextField();
        urgencycb = new javax.swing.JComboBox<>();
        btnsubmit = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(66, 103, 238));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Complaint Tracker");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Report facility issues and track their resolutions");

        submitusername.setEditable(false);
        submitusername.setBackground(new java.awt.Color(66, 103, 238));
        submitusername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitusername.setForeground(new java.awt.Color(255, 255, 255));
        submitusername.setText("Username");
        submitusername.setBorder(null);
        submitusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitusernameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitusername, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(submitusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1062, 552));

        studenttxt.setForeground(new java.awt.Color(102, 102, 102));
        studenttxt.setText("Student ID");
        studenttxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studenttxtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 103, 238));
        jLabel3.setText("SUBMIT COMPLAINTS");

        jLabelMyComplaints.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelMyComplaints.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMyComplaints.setText("MY COMPLAINTS");

        jSeparator2.setBackground(new java.awt.Color(66, 103, 238));
        jSeparator2.setForeground(new java.awt.Color(66, 103, 238));
        jSeparator2.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Submit a Facility Issue");

        jLabel6.setText("Please provide detailed information about the facility issue you encountered");

        locationtxt.setForeground(new java.awt.Color(102, 102, 102));
        locationtxt.setText("Location/Room Number");
        locationtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationtxtActionPerformed(evt);
            }
        });

        imagetxt.setForeground(new java.awt.Color(102, 102, 102));
        imagetxt.setText("Image URL (Optional)");
        imagetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagetxtActionPerformed(evt);
            }
        });

        classroomcb.setForeground(new java.awt.Color(102, 102, 102));
        classroomcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Classroom", "Laboratory", "Library", "Student Lounge", "Comfort Room", "Canteen", "Gymnasium", "Hallway", "Elevator", "Lobby", " ", " ", " " }));

        descriptiontxt.setForeground(new java.awt.Color(102, 102, 102));
        descriptiontxt.setText("Description of Issue");
        descriptiontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptiontxtActionPerformed(evt);
            }
        });

        urgencycb.setForeground(new java.awt.Color(102, 102, 102));
        urgencycb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Moderate", "High" }));

        btnsubmit.setBackground(new java.awt.Color(66, 103, 238));
        btnsubmit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnsubmit.setText("SUBMIT COMPLAINT");
        btnsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsubmitActionPerformed(evt);
            }
        });

        btnclear.setBackground(new java.awt.Color(66, 103, 238));
        btnclear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnclear.setForeground(new java.awt.Color(255, 255, 255));
        btnclear.setText("CLEAR");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(29, 29, 29)
                        .addComponent(jLabelMyComplaints)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(locationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imagetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addComponent(descriptiontxt))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(studenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(classroomcb, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(urgencycb, 0, 195, Short.MAX_VALUE)
                            .addComponent(btnsubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnclear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelMyComplaints))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(urgencycb, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(classroomcb)))
                    .addComponent(studenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(locationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(imagetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnsubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(descriptiontxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void locationtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationtxtActionPerformed

    private void studenttxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studenttxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studenttxtActionPerformed

    private void descriptiontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptiontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptiontxtActionPerformed

    private void btnsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubmitActionPerformed
         // TODO add your handling code here:
        String studentId = studenttxt.getText().trim();
        String location = locationtxt.getText().trim();
        String facilityType = classroomcb.getSelectedItem().toString();
        String imagePath = uploadedImageName;
        if (imagePath == null) {
        imagePath = "";
}
    String description = descriptiontxt.getText().trim();
    String priority = urgencycb.getSelectedItem().toString();

    // Validation
    if (studentId.isEmpty() || studentId.equals("Student ID")) {
        JOptionPane.showMessageDialog(this,
                "Please enter your Student ID",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (location.isEmpty() || location.equals("Location/Room Number")) {
        JOptionPane.showMessageDialog(this,
                "Please enter the location/room number",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (description.isEmpty() || description.equals("Description of Issue")) {
        JOptionPane.showMessageDialog(this,
                "Please enter a description of the issue",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Create complaint object
    Complaint complaint = new Complaint(
            studentId,
            location,
            facilityType,
            imagePath,
            description,
            priority
    );

    // Save complaint
    ComplaintDAO dao = new ComplaintDAO();
    boolean success = dao.saveComplaint(complaint);

    if (success) {

        JOptionPane.showMessageDialog(this,
                "Complaint submitted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        // Open MyComplaintsPage
        MyComplaintsPage myPage = new MyComplaintsPage(username, studentId);
        myPage.setVisible(true);

        this.dispose();

    } else {

        JOptionPane.showMessageDialog(this,
                "Failed to submit complaint.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnsubmitActionPerformed

    private void submitusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitusernameActionPerformed

    private void imagetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imagetxtActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        studenttxt.setText("Student ID");
    studenttxt.setForeground(Color.GRAY);

    locationtxt.setText("Location/Room Number");
    locationtxt.setForeground(Color.GRAY);

    imagetxt.setText("Image URL (Optional)");
    imagetxt.setForeground(Color.GRAY);

    descriptiontxt.setText("Description of Issue");
    descriptiontxt.setForeground(Color.GRAY);

    // Reset combo boxes to first option
    classroomcb.setSelectedIndex(0);   // Facility combo box
    urgencycb.setSelectedIndex(0);     // Priority level combo box

    // Reset uploaded image name
    uploadedImageName = "";
    }//GEN-LAST:event_btnclearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnsubmit;
    private javax.swing.JComboBox<String> classroomcb;
    private javax.swing.JTextField descriptiontxt;
    private javax.swing.JTextField imagetxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelMyComplaints;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField locationtxt;
    private javax.swing.JTextField studenttxt;
    private javax.swing.JTextField submitusername;
    private javax.swing.JComboBox<String> urgencycb;
    // End of variables declaration//GEN-END:variables
}

