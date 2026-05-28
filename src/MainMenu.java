 

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    // Define the exact colors from your design
    private final Color MAIN_BLUE = new Color(66, 103, 238);
    private final Color BG_GRAY = new Color(240, 240, 240);
    private final Color BORDER_COLOR = new Color(220, 220, 220);
    private final Color TEXT_GRAY = new Color(150, 150, 150);

    public MainMenu() {
        // 1. Frame Setup
        setTitle("Complaint Tracker");
        setSize(1050, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_GRAY);

        // 2. Header Panel (Blue)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(MAIN_BLUE);
        headerPanel.setPreferredSize(new Dimension(1050, 130));
        headerPanel.setLayout(null); // Absolute layout for exact placement

        JLabel titleLabel = new JLabel("Complaint Tracker");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(40, 25, 400, 40);
        headerPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Report facility issues and track their resolutions");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(220, 230, 255)); // Slightly dimmed white
        subtitleLabel.setBounds(42, 65, 400, 20);
        headerPanel.add(subtitleLabel);

        JLabel userLabel = new JLabel("Tom Gabrielle  ∨");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(870, 45, 150, 20);
        headerPanel.add(userLabel);

        add(headerPanel, BorderLayout.NORTH);

        // 3. Tab Area (White Card)
        JPanel mainContent = new JPanel();
        mainContent.setBackground(BG_GRAY);
        mainContent.setLayout(null);
        mainContent.setBorder(new EmptyBorder(20, 20, 20, 20));

        // The white container that holds everything
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(20, 20, 995, 450);
        cardPanel.setLayout(null);
        // Add a subtle border to the white card
        cardPanel.setBorder(new LineBorder(new Color(210, 210, 210), 1));
        mainContent.add(cardPanel);

        // 4. Custom Tab Headers (To match the flat web look perfectly)
        JLabel submitTabLabel = new JLabel("SUBMIT COMPLAINT");
        submitTabLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        submitTabLabel.setForeground(MAIN_BLUE);
        submitTabLabel.setBounds(30, 15, 150, 20);
        cardPanel.add(submitTabLabel);

        // Blue underline for active tab
        JPanel activeTabLine = new JPanel();
        activeTabLine.setBackground(MAIN_BLUE);
        activeTabLine.setBounds(30, 40, 135, 2);
        cardPanel.add(activeTabLine);

        JLabel myComplaintsTabLabel = new JLabel("MY COMPLAINTS");
        myComplaintsTabLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        myComplaintsTabLabel.setForeground(Color.DARK_GRAY);
        myComplaintsTabLabel.setBounds(190, 15, 150, 20);
        cardPanel.add(myComplaintsTabLabel);

        // Divider line under tabs
        JPanel divider = new JPanel();
        divider.setBackground(BORDER_COLOR);
        divider.setBounds(0, 41, 995, 1);
        cardPanel.add(divider);

        // 5. Form Header
        JLabel formTitle = new JLabel("Submit a Facility Issue");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formTitle.setForeground(Color.BLACK);
        formTitle.setBounds(30, 65, 300, 25);
        cardPanel.add(formTitle);

        JLabel formDesc = new JLabel("Please provide detailed information about the facility issue you encountered.");
        formDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formDesc.setForeground(Color.GRAY);
        formDesc.setBounds(30, 90, 500, 20);
        cardPanel.add(formDesc);

        // 6. Form Fields
        int startY = 130;
        int col1X = 30;
        int col2X = 300;
        int col3X = 570;
        int fieldWidth = 250;
        int fieldHeight = 45;
        int ySpacing = 65;

        // Column 1
        JTextField txtStudentId = createTextField("Student ID");
        txtStudentId.setBounds(col1X, startY, fieldWidth, fieldHeight);
        cardPanel.add(txtStudentId);

        JTextField txtLocation = createTextField("Location/Room Number");
        txtLocation.setBounds(col1X, startY + ySpacing, fieldWidth, fieldHeight);
        cardPanel.add(txtLocation);

        JTextField txtImage = createTextField("Image URL (Optional)");
        txtImage.setBounds(col1X, startY + (ySpacing * 2), fieldWidth, fieldHeight);
        cardPanel.add(txtImage);

        // Column 2
        String[] facilities = {"Facility", "Classroom", "Laboratory", "Library", "Restroom"};
        JComboBox<String> cbFacility = createComboBox(facilities);
        cbFacility.setBounds(col2X, startY, fieldWidth, fieldHeight);
        cardPanel.add(cbFacility);

        JTextArea txtDescription = new JTextArea("Description of Issue");
        txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDescription.setForeground(TEXT_GRAY);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        // Custom padding for Text Area
        txtDescription.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1),
                new EmptyBorder(10, 15, 10, 15)
        ));
        
        JScrollPane scrollPane = new JScrollPane(txtDescription);
        scrollPane.setBorder(null); // Remove default scroll pane border
        scrollPane.setBounds(col2X, startY + ySpacing, fieldWidth, 110);
        cardPanel.add(scrollPane);

        // Column 3
        String[] priorities = {"Priority Level", "Low", "Medium", "High"};
        JComboBox<String> cbPriority = createComboBox(priorities);
        cbPriority.setBounds(col3X, startY, fieldWidth, fieldHeight);
        cardPanel.add(cbPriority);

        // Custom Flat Button
        JButton btnSubmit = new JButton("➤   SUBMIT COMPLAINT");
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSubmit.setBackground(MAIN_BLUE);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false); // Removes the ugly click box
        btnSubmit.setBorderPainted(false); // Removes the 3D border
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubmit.setBounds(col3X, startY + ySpacing, fieldWidth, fieldHeight);
        cardPanel.add(btnSubmit);

        add(mainContent, BorderLayout.CENTER);
    }

    // --- Helper Methods to generate modern looking inputs ---

    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(TEXT_GRAY);
        // Compound border gives us the gray outline PLUS inner padding so text doesn't touch the edge
        textField.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1),
                new EmptyBorder(5, 15, 5, 15)
        ));
        return textField;
    }

    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setForeground(TEXT_GRAY);
        comboBox.setBackground(Color.WHITE);
        comboBox.setFocusable(false);
        // Minimalist border
        comboBox.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1),
                new EmptyBorder(0, 5, 0, 0)
        ));
        return comboBox;
    }

    public static void main(String[] args) {
        // Attempt to make it look as clean as possible across all OS
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}