/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author RODELYN MANZO
 */
import javax.swing.Timer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.JOptionPane;

public class NewJFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame.class.getName());
private int selectedComplaintId = -1;
private int selectedRow = -1;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        setLocationRelativeTo(null);
     
    new javax.swing.Timer(5000, e -> loadComplaints()).start();
    loadComplaints();
    setupTableSelection();
    colorStatusRows();
 
    
    }
     private void setupTableSelection() {

    // Select row from Table 1
    jTable1.getSelectionModel().addListSelectionListener(e -> {

        if (!e.getValueIsAdjusting()) {

            selectedRow = jTable1.getSelectedRow();

            if (selectedRow == -1) {
    return;
}

if (selectedRow < jTable1.getRowCount()) {

    jTable2.setRowSelectionInterval(selectedRow, selectedRow);

    String imagePath =
            String.valueOf(jTable1.getValueAt(selectedRow, 4));

                // Show image if exists
                if (imagePath != null && !imagePath.isEmpty()) {
                    showImage(imagePath);
                }

                // Get complaint ID
                ComplaintDAO dao = new ComplaintDAO();
                List<Complaint> complaints = dao.getAllComplaints();

                if (selectedRow < complaints.size()) {

                    selectedComplaintId =
                            complaints.get(selectedRow).getId();

                    // Set combo box to current status
                    String currentStatus =
                            (String) jTable2.getValueAt(selectedRow, 1);

                    jComboBox1.setSelectedItem(currentStatus);
                }
            }
        }
    });

    // Select row from Table 2
    jTable2.getSelectionModel().addListSelectionListener(e -> {

        if (!e.getValueIsAdjusting()) {

            selectedRow = jTable2.getSelectedRow();

            if (selectedRow >= 0) {

                jTable1.setRowSelectionInterval(selectedRow, selectedRow);

                ComplaintDAO dao = new ComplaintDAO();
                List<Complaint> complaints = dao.getAllComplaints();

                if (selectedRow < complaints.size()) {

                    selectedComplaintId =
                            complaints.get(selectedRow).getId();

                    String currentStatus =
                            (String) jTable2.getValueAt(selectedRow, 1);

                    jComboBox1.setSelectedItem(currentStatus);
                }
            }
        }
    });

        
        // Also allow clicking on the first table to select
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0) {
                    jTable2.setRowSelectionInterval(selectedRow, selectedRow);
                }
            }
        });
    }
     private void colorStatusRows() {
         javax.swing.table.DefaultTableCellRenderer renderer =
        new javax.swing.table.DefaultTableCellRenderer() {

        @Override
        public java.awt.Component getTableCellRendererComponent(
                javax.swing.JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            java.awt.Component c =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column);

            // Default colors
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);

            // ===== PRIORITY COLUMN =====
            if (column == 0) {

                String priority = value.toString();

                if (priority.equalsIgnoreCase("Low")) {

                    c.setBackground(new Color(204, 255, 204));

                } else if (priority.equalsIgnoreCase("Moderate")) {

                    c.setBackground(new Color(255, 229, 153));

                } else if (priority.equalsIgnoreCase("High")) {

                    c.setBackground(new Color(255, 153, 153));
                }
            }

            // ===== STATUS COLUMN =====
            if (column == 1) {

                String status = value.toString();

                if (status.equalsIgnoreCase("Pending")) {

                    c.setBackground(new Color(255, 230, 153));

                } else if (status.equalsIgnoreCase("In Progress")) {

                    c.setBackground(new Color(153, 204, 255));

                } else if (status.equalsIgnoreCase("Resolved")) {

                    c.setBackground(new Color(144, 238, 144));

                } else if (status.equalsIgnoreCase("Rejected")) {

                    c.setBackground(new Color(255, 182, 193));
                }
            }

            return c;
        }
    };

    // Apply renderer to BOTH columns
    jTable2.getColumnModel().getColumn(0).setCellRenderer(renderer);
    jTable2.getColumnModel().getColumn(1).setCellRenderer(renderer);
}
     
    
    private void showImage(String imageurl) {

    try {

        ImageIcon icon = new ImageIcon("uploads/" + imageurl);

        Image img = icon.getImage().getScaledInstance(
            400,
            300,
            Image.SCALE_SMOOTH
        );

        JLabel label = new JLabel(new ImageIcon(img));

        JOptionPane.showMessageDialog(
            this,
            label,
            "Complaint Image",
            JOptionPane.PLAIN_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Unable to load image."
        );

        e.printStackTrace();
    }
}
    public void loadComplaints() {

    DefaultTableModel model1 =
            (DefaultTableModel) jTable1.getModel();

    DefaultTableModel model2 =
            (DefaultTableModel) jTable2.getModel();

    // Clear old rows
    model1.setRowCount(0);
    model2.setRowCount(0);

    ComplaintDAO dao = new ComplaintDAO();

    List<Complaint> complaints = dao.getAllComplaints();

    // COUNTERS
    int total = 0;
    int pending = 0;
    int inProgress = 0;
    int resolved = 0;
    int rejected = 0;

    for (Complaint c : complaints) {

        // TABLE 1
        model1.addRow(new Object[]{
            c.getStudentId(),
            c.getFacilityType(),
            c.getLocation(),
            c.getDescription(),
            c.getImageUrl()
        });

        // TABLE 2
        model2.addRow(new Object[]{
            c.getPriority(),
            c.getStatus(),
            c.getSubmissionDate()
        });

        // TOTAL COUNT
        total++;

        // STATUS COUNT
        String status = c.getStatus();

        if (status.equalsIgnoreCase("Pending")) {

            pending++;

        } else if (status.equalsIgnoreCase("In Progress")) {

            inProgress++;

        } else if (status.equalsIgnoreCase("Resolved")) {

            resolved++;

        } else if (status.equalsIgnoreCase("Rejected")) {

            rejected++;
        }
    }

    // DISPLAY COUNTS
    totaltxt.setText(String.valueOf(total));

    pendingtxt.setText(String.valueOf(pending));

    inprogresstxt.setText(String.valueOf(inProgress));

    resolvedtxt.setText(String.valueOf(resolved));

    rejectedtxt.setText(String.valueOf(rejected));
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
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        refresh = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        totaltxt = new javax.swing.JTextField();
        pendingpanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pendingtxt = new javax.swing.JTextField();
        inprogresspanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        inprogresstxt = new javax.swing.JTextField();
        resolvedpanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        resolvedtxt = new javax.swing.JTextField();
        rejectedpanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        rejectedtxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(66, 103, 238));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Complaint Tracker");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Report facility issues and track their resolutions");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 103, 238));
        jLabel3.setText("Employee Dashboard");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("All Complaints");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Facility", "Location", "Issue", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setOpaque(false);
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Priority", "Status", "Date Submitted"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jTable2.setOpaque(false);
        jTable2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jTable2);

        refresh.setBackground(new java.awt.Color(66, 103, 238));
        refresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("Update");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(66, 103, 238));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "In Progress", "Resolved", "Rejected" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(201, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Total Complaints");

        totaltxt.setEditable(false);
        totaltxt.setBackground(new java.awt.Color(204, 204, 255));
        totaltxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totaltxt.setText("0");
        totaltxt.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(52, 52, 52))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pendingpanel.setBackground(new java.awt.Color(255, 255, 204));
        pendingpanel.setPreferredSize(new java.awt.Dimension(201, 78));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Pending");

        pendingtxt.setEditable(false);
        pendingtxt.setBackground(new java.awt.Color(255, 255, 204));
        pendingtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pendingtxt.setText("0");
        pendingtxt.setBorder(null);

        javax.swing.GroupLayout pendingpanelLayout = new javax.swing.GroupLayout(pendingpanel);
        pendingpanel.setLayout(pendingpanelLayout);
        pendingpanelLayout.setHorizontalGroup(
            pendingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingpanelLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(pendingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pendingtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(75, 75, 75))
        );
        pendingpanelLayout.setVerticalGroup(
            pendingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingpanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pendingtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        inprogresspanel.setBackground(new java.awt.Color(204, 204, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("In Progress");

        inprogresstxt.setEditable(false);
        inprogresstxt.setBackground(new java.awt.Color(204, 204, 255));
        inprogresstxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inprogresstxt.setText("0");
        inprogresstxt.setBorder(null);

        javax.swing.GroupLayout inprogresspanelLayout = new javax.swing.GroupLayout(inprogresspanel);
        inprogresspanel.setLayout(inprogresspanelLayout);
        inprogresspanelLayout.setHorizontalGroup(
            inprogresspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inprogresspanelLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(inprogresspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inprogresspanelLayout.createSequentialGroup()
                        .addComponent(inprogresstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inprogresspanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(68, 68, 68))))
        );
        inprogresspanelLayout.setVerticalGroup(
            inprogresspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inprogresspanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inprogresstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        resolvedpanel.setBackground(new java.awt.Color(204, 255, 204));
        resolvedpanel.setPreferredSize(new java.awt.Dimension(201, 78));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Resolved");

        resolvedtxt.setEditable(false);
        resolvedtxt.setBackground(new java.awt.Color(204, 255, 204));
        resolvedtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        resolvedtxt.setText("0");
        resolvedtxt.setBorder(null);

        javax.swing.GroupLayout resolvedpanelLayout = new javax.swing.GroupLayout(resolvedpanel);
        resolvedpanel.setLayout(resolvedpanelLayout);
        resolvedpanelLayout.setHorizontalGroup(
            resolvedpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resolvedpanelLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(resolvedpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(resolvedtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );
        resolvedpanelLayout.setVerticalGroup(
            resolvedpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resolvedpanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolvedtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rejectedpanel.setBackground(new java.awt.Color(255, 204, 204));
        rejectedpanel.setPreferredSize(new java.awt.Dimension(201, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Rejected");

        rejectedtxt.setEditable(false);
        rejectedtxt.setBackground(new java.awt.Color(255, 204, 204));
        rejectedtxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rejectedtxt.setText("0");
        rejectedtxt.setBorder(null);

        javax.swing.GroupLayout rejectedpanelLayout = new javax.swing.GroupLayout(rejectedpanel);
        rejectedpanel.setLayout(rejectedpanelLayout);
        rejectedpanelLayout.setHorizontalGroup(
            rejectedpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rejectedpanelLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(rejectedpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rejectedtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(74, 74, 74))
        );
        rejectedpanelLayout.setVerticalGroup(
            rejectedpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rejectedpanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rejectedtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(pendingpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(inprogresspanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(resolvedpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(rejectedpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(refresh)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(inprogresspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pendingpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resolvedpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rejectedpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
         if (selectedComplaintId == -1) {
        JOptionPane.showMessageDialog(this, 
            "Please click on a complaint row first to select it.", 
            "No Selection", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String newStatus = (String) jComboBox1.getSelectedItem();
    
    // Ask for confirmation
    int confirm = JOptionPane.showConfirmDialog(this,
        "Update status to: " + newStatus + " for the selected complaint?",
        "Confirm Status Update",
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        // Update in database
        ComplaintDAO dao = new ComplaintDAO();
        boolean success = dao.updateComplaintStatus(selectedComplaintId, newStatus);
        
        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Status updated successfully to: " + newStatus, 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadComplaints(); // Refresh the tables
            selectedComplaintId = -1; // Reset selection
        } else {
            JOptionPane.showMessageDialog(this, 
                "Failed to update status. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    }//GEN-LAST:event_refreshActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel inprogresspanel;
    private javax.swing.JTextField inprogresstxt;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel pendingpanel;
    private javax.swing.JTextField pendingtxt;
    private javax.swing.JButton refresh;
    private javax.swing.JPanel rejectedpanel;
    private javax.swing.JTextField rejectedtxt;
    private javax.swing.JPanel resolvedpanel;
    private javax.swing.JTextField resolvedtxt;
    private javax.swing.JTextField totaltxt;
    // End of variables declaration//GEN-END:variables
}
