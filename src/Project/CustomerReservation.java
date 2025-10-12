package Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class CustomerReservation extends javax.swing.JFrame {
        private int userId = -1;
        private String fullName = null;
        private String userEmail = null;
        private String userName = null;

        DatePicker datePicker = new DatePicker();
        TimePicker timePicker = new TimePicker();
    
    public CustomerReservation(int userId, String fullName, String userEmail, String userName) {
    this.userId = userId;
    this.fullName = fullName;
    this.userEmail = userEmail;
    this.userName = userName;

    initComponents();
    loadServices();
    loadCarModels();
    setupDateTimePickers();

    // Optionally show the user's name on the form
    if (jLabel10 != null) {
        jLabel10.setText("Welcome " + (userName == null ? "" : userName));
    }
}
    public CustomerReservation() {
        initComponents();
        loadServices();
        loadCarModels();
        setupDateTimePickers();
        
        jPanel6.setLayout(new java.awt.FlowLayout());
        jPanel6.add(datePicker);
        jPanel6.add(timePicker);
        
        datePicker.getComponentDateTextField().setText("Add Date");
        timePicker.getComponentTimeTextField().setText("Add Time");
        
        // ✅ Disable past dates
        datePicker.getSettings().setVetoPolicy(new DateVetoPolicy() {
        @Override
        public boolean isDateAllowed(LocalDate date) {
            // Only allow today or future dates
            return !date.isBefore(LocalDate.now());
        }
        
    });
    
     // ✅ Restrict date (no past dates)
    datePicker.getSettings().setVetoPolicy(date -> !date.isBefore(LocalDate.now()));

    // ✅ Restrict time (only 10:00 AM to 8:00 PM)
    timePicker.getSettings().setVetoPolicy(new TimeVetoPolicy() {
        @Override
        public boolean isTimeAllowed(LocalTime time) {
            LocalTime start = LocalTime.of(10, 0);  // 10:00 AM
            LocalTime end = LocalTime.of(20, 0);    // 8:00 PM
            return !time.isBefore(start) && !time.isAfter(end);
        }
    });

    // ✅ Optional placeholder setup
    datePicker.getComponentDateTextField().setText("Add Date");
    datePicker.getComponentDateTextField().setForeground(Color.GRAY);
    timePicker.getComponentTimeTextField().setText("Add Time");
    timePicker.getComponentTimeTextField().setForeground(Color.GRAY);

    // ✅ Add placeholder text
    datePicker.getComponentDateTextField().setText("Add Date");
    datePicker.getComponentDateTextField().setForeground(Color.GRAY);
    timePicker.getComponentTimeTextField().setText("Add Time");
    timePicker.getComponentTimeTextField().setForeground(Color.GRAY);

    // ✅ Optional: clear placeholders on focus
    datePicker.getComponentDateTextField().addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (datePicker.getComponentDateTextField().getText().equals("Add Date")) {
                datePicker.getComponentDateTextField().setText("");
                datePicker.getComponentDateTextField().setForeground(Color.BLACK);
            }
        }
    });
    timePicker.getComponentTimeTextField().addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (timePicker.getComponentTimeTextField().getText().equals("Add Time")) {
                timePicker.getComponentTimeTextField().setText("");
                timePicker.getComponentTimeTextField().setForeground(Color.BLACK);
            }
        }
    });
        
    }
    
    private void setupDateTimePickers() {
    jPanel6.setLayout(new java.awt.FlowLayout());
    jPanel6.add(datePicker);
    jPanel6.add(timePicker);

    // ✅ Placeholder text
    datePicker.getComponentDateTextField().setText("Add Date");
    datePicker.getComponentDateTextField().setForeground(Color.GRAY);
    timePicker.getComponentTimeTextField().setText("Add Time");
    timePicker.getComponentTimeTextField().setForeground(Color.GRAY);

    // ✅ Disable past dates
    datePicker.getSettings().setVetoPolicy(date -> !date.isBefore(LocalDate.now()));

    // ✅ Restrict time (only 10:00 AM to 8:00 PM)
    timePicker.getSettings().setVetoPolicy(new TimeVetoPolicy() {
        @Override
        public boolean isTimeAllowed(LocalTime time) {
            LocalTime start = LocalTime.of(10, 0);
            LocalTime end = LocalTime.of(20, 0);
            return !time.isBefore(start) && !time.isAfter(end);
        }
    });

    // ✅ Remove placeholder text on focus
    datePicker.getComponentDateTextField().addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (datePicker.getComponentDateTextField().getText().equals("Add Date")) {
                datePicker.getComponentDateTextField().setText("");
                datePicker.getComponentDateTextField().setForeground(Color.BLACK);
            }
        }
    });
    timePicker.getComponentTimeTextField().addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (timePicker.getComponentTimeTextField().getText().equals("Add Time")) {
                timePicker.getComponentTimeTextField().setText("");
                timePicker.getComponentTimeTextField().setForeground(Color.BLACK);
            }
        }
    });
}

   
   public void loadCarModels() {
    try (Connection conn = DBConnect.getConnection()) {
        String sql = "SELECT car_model FROM tbl_car_model"; // your table name
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        jComboBox3.removeAllItems();
        jComboBox3.addItem("Select Car Model --");
        while (rs.next()) {
            jComboBox3.addItem(rs.getString("car_model"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   
   public void loadServices() {
    try (Connection conn = DBConnect.getConnection()) {
        String sql = "SELECT service_name FROM tbl_service"; // your table name
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Select Service --");
        while (rs.next()) {
            jComboBox2.addItem(rs.getString("service_name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reservation");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 157, 224));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ToL-Pawiring");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, 63));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/male_user_50px.png"))); // NOI18N
        jLabel10.setText(" Hello Customer!");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 20, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/g.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 90));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("LOGOUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 70, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1930, 110));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(253, 201, 22));
        jPanel5.setForeground(new java.awt.Color(253, 201, 22));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setBackground(new java.awt.Color(92, 93, 93));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(92, 93, 93));
        jLabel28.setText("@TheChosenOne 2025");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 1620, 90));

        jPanel3.setBackground(new java.awt.Color(253, 201, 22));
        jPanel3.setForeground(new java.awt.Color(253, 201, 22));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 157, 224));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(92, 93, 93));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Type of Services");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(92, 93, 93));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Services --" }));
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 330, 30));

        jLabel5.setBackground(new java.awt.Color(92, 93, 93));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BOOK A RESERVATION");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        jLabel6.setBackground(new java.awt.Color(92, 93, 93));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(92, 93, 93));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car Model --" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 330, 30));

        jLabel7.setBackground(new java.awt.Color(92, 93, 93));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Car Model");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        jLabel8.setBackground(new java.awt.Color(92, 93, 93));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 20, -1));

        jLabel9.setBackground(new java.awt.Color(92, 93, 93));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Between 10:00am - 8pm");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, -1, -1));

        jLabel11.setBackground(new java.awt.Color(92, 93, 93));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        jLabel12.setBackground(new java.awt.Color(92, 93, 93));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Schedule");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        jButton1.setBackground(new java.awt.Color(253, 201, 22));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit_property_24px.png"))); // NOI18N
        jButton1.setText("RESERVATION");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 200, 50));

        jButton3.setBackground(new java.awt.Color(24, 25, 155));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("BOOK NOW");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 200, 50));
        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 330, 40));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 560, 490));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 640, 570));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/2024-10-30_resized_1590x760.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1590, 760));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1590, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                new HelperClass(new CustomerReservationList());
                this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Ensure user is logged in (userId populated)
    if (userId <= 0) {
        JOptionPane.showMessageDialog(this, "User not logged in. Please login first.", "Not logged in", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validate combo selections
    String service = (String) jComboBox2.getSelectedItem();
    String carModel = (String) jComboBox3.getSelectedItem();
    if (service == null || service.startsWith("Select") || service.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a service.", "Validation", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (carModel == null || carModel.startsWith("Select") || carModel.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a car model.", "Validation", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Get date and time from pickers
    LocalDate date = datePicker.getDate();
    LocalTime time = timePicker.getTime();

    if (date == null || time == null) {
        JOptionPane.showMessageDialog(this, "Please choose schedule date and time.", "Validation", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Disallow past date/time
    if (date.isBefore(LocalDate.now()) || (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now()))) {
        JOptionPane.showMessageDialog(this, "Selected date/time is in the past.", "Validation", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Enforce business hours 10:00 - 20:00
    LocalTime start = LocalTime.of(10, 0);
    LocalTime end = LocalTime.of(20, 0);
    if (time.isBefore(start) || time.isAfter(end)) {
        JOptionPane.showMessageDialog(this, "Please select a time between 10:00 AM and 8:00 PM.", "Invalid time", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Combine date + time into a single string for 'schedule' column (adjust format to your DB expectations)
    String schedule = date.toString() + " " + time.toString(); // e.g. 2025-10-12 14:30
    // Insert into database
    String sql = "INSERT INTO tbl_reservation (id, flname, email, service_type, car_model, schedule, status, date_place) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())";

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, userId);                    // link to tbl_users.id
        ps.setString(2, fullName != null ? fullName : "");
        ps.setString(3, userEmail != null ? userEmail : "");
        ps.setString(4, service);
        ps.setString(5, carModel);
        ps.setString(6, schedule);
        ps.setString(7, "Pending");

        int affected = ps.executeUpdate();
        if (affected > 0) {
            JOptionPane.showMessageDialog(this, "Reservation placed successfully!");
            // Optionally refresh UI or clear selections
            loadServices();
            loadCarModels();
            datePicker.clear();
            timePicker.clear();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to place reservation.", "DB Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Logout Confirmation",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // Close this window
            this.dispose();

            // Open the login form
            LoginForm login = new LoginForm();
            login.setVisible(true);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerReservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}