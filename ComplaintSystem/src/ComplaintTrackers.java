/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ComplaintTrackerSystem;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author akino
 */
public class ComplaintTrackers extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ComplaintTrackers.class.getName());

    public ComplaintTrackers() {
        initComponents();
        addPlaceholderStyle(JTusername, "Student/Employee ID");
        addPlaceholderStyle(JPpassword, "Passwords");
    }

    private void addPlaceholderStyle(javax.swing.JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(new Color(153, 153, 153)); // Grey color
                }
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JMainFrame = new javax.swing.JPanel();
        JHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JLogIn = new javax.swing.JPanel();
        txtlogin = new javax.swing.JLabel();
        JTusername = new javax.swing.JTextField();
        JPpassword = new javax.swing.JPasswordField();
        JBlogin = new javax.swing.JButton();
        JBregister = new javax.swing.JButton();
        txtForgotPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1115, 780));
        setResizable(false);

        JMainFrame.setBackground(new java.awt.Color(230, 230, 230));
        JMainFrame.setPreferredSize(new java.awt.Dimension(1037, 500));

        JHeader.setBackground(new java.awt.Color(66, 103, 238));
        JHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Complaint Tracker");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Report facility issues and track their resolutions");

        javax.swing.GroupLayout JHeaderLayout = new javax.swing.GroupLayout(JHeader);
        JHeader.setLayout(JHeaderLayout);
        JHeaderLayout.setHorizontalGroup(
            JHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JHeaderLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(JHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JHeaderLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JHeaderLayout.setVerticalGroup(
            JHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JHeaderLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        JLogIn.setBackground(new java.awt.Color(255, 255, 255));
        JLogIn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JLogIn.setPreferredSize(new java.awt.Dimension(1037, 500));

        txtlogin.setBackground(new java.awt.Color(255, 255, 255));
        txtlogin.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        txtlogin.setForeground(new java.awt.Color(0, 0, 0));
        txtlogin.setText("Login");

        JTusername.setBackground(new java.awt.Color(255, 255, 255));
        JTusername.setForeground(new java.awt.Color(0, 0, 0));
        JTusername.setText("Student/Employee ID");
        JTusername.setToolTipText("");
        JTusername.addActionListener(this::JTusernameActionPerformed);

        JPpassword.setEditable(false);
        JPpassword.setBackground(new java.awt.Color(255, 255, 255));
        JPpassword.setForeground(new java.awt.Color(0, 0, 0));
        JPpassword.setText("Passwords");

        JBlogin.setBackground(new java.awt.Color(66, 103, 238));
        JBlogin.setForeground(new java.awt.Color(255, 255, 255));
        JBlogin.setText("Login");
        JBlogin.addActionListener(this::JBloginActionPerformed);

        JBregister.setBackground(new java.awt.Color(66, 103, 238));
        JBregister.setForeground(new java.awt.Color(255, 255, 255));
        JBregister.setText("Register");
        JBregister.addActionListener(this::JBregisterActionPerformed);

        txtForgotPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtForgotPassword.setText("Forgot Password?");

        javax.swing.GroupLayout JLogInLayout = new javax.swing.GroupLayout(JLogIn);
        JLogIn.setLayout(JLogInLayout);
        JLogInLayout.setHorizontalGroup(
            JLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JLogInLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTusername, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(JPpassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JLogInLayout.createSequentialGroup()
                .addGap(469, 469, 469)
                .addComponent(txtlogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JLogInLayout.createSequentialGroup()
                .addContainerGap(390, Short.MAX_VALUE)
                .addComponent(JBregister, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(390, 390, 390))
            .addGroup(JLogInLayout.createSequentialGroup()
                .addGap(479, 479, 479)
                .addComponent(txtForgotPassword)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JLogInLayout.setVerticalGroup(
            JLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JLogInLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(txtlogin)
                .addGap(32, 32, 32)
                .addComponent(JTusername, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBregister, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtForgotPassword)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        JTusername.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout JMainFrameLayout = new javax.swing.GroupLayout(JMainFrame);
        JMainFrame.setLayout(JMainFrameLayout);
        JMainFrameLayout.setHorizontalGroup(
            JMainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMainFrameLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(JMainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        JMainFrameLayout.setVerticalGroup(
            JMainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMainFrameLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(JHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(JLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JMainFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 1103, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JMainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JTusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTusernameActionPerformed

    private void JBloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBloginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBloginActionPerformed

    private void JBregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBregisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBregisterActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ComplaintTrackers().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBlogin;
    private javax.swing.JButton JBregister;
    private javax.swing.JPanel JHeader;
    private javax.swing.JPanel JLogIn;
    private javax.swing.JPanel JMainFrame;
    private javax.swing.JPasswordField JPpassword;
    private javax.swing.JTextField JTusername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txtForgotPassword;
    private javax.swing.JLabel txtlogin;
    // End of variables declaration//GEN-END:variables
}
