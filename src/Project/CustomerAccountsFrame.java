package Project;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author Kelly
 */
public class CustomerAccountsFrame extends javax.swing.JFrame {
    
    public CustomerAccountsFrame() {
        initComponents();
        loadCustomerData();
        addDeleteButtonToTable();

        this.setTitle("Tol Pawiring");
        this.setResizable(false);
        jTable1.setRowHeight(35);
        
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setWidth(0);
        
    }
    
    private void loadCustomerData() {
    String URL = "jdbc:mysql://localhost:3306/asc_db";
    String USER = "root";
    String PASS = "";

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = (Connection) DriverManager.getConnection(URL, USER, PASS);
        Statement statement = connect.createStatement();
        String query = "SELECT customer_id, complete_name, email_address, account_created FROM customers";

        ResultSet result = statement.executeQuery(query);

        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
        tblModel.setRowCount(0); 

        while (result.next()) {
            int id = result.getInt("customer_id");
            String name = result.getString("complete_name");
            String email = result.getString("email_address");
            String accountCreated = result.getString("account_created");

            Object[] tbData = {String.valueOf(id), name, email, accountCreated, "Delete"};
            tblModel.addRow(tbData);
        }
        result.close();
        statement.close();
        connect.close();
    }
    catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
    }
}
    
class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Delete" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private int editingRow = -1;
    private JTable table;
    private boolean clicked = false;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton("Delete");
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        
        button.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                  clicked = true;
                   fireEditingStopped();
            });
        });
    }

    @Override
    public Component getTableCellEditorComponent (JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.editingRow = row;
        this.clicked = false;
        button.setText(value == null ? "Delete" : value.toString());
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if(!clicked) {
            return null;
        }
        
        int modelRow = table.convertRowIndexToModel(editingRow);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        if(modelRow < 0  || modelRow >= model.getRowCount()) {
            clicked = false;
            return null;
        }
        
        Object idObject = model.getValueAt(modelRow, 0);
        int id;
        
         try {
            id = Integer.parseInt(String.valueOf(idObject));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid record id: " + idObject);
            clicked = false;
            return null;
        }
         
       SwingUtilities.invokeLater(() -> {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

         int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
         
         if(confirm  == JOptionPane.YES_OPTION) {
             deleteFromDatabase(id);
             
               if (modelRow >= 0 && modelRow < model.getRowCount()) {
                model.removeRow(modelRow);
            }
   
             JOptionPane.showMessageDialog(null, "Record deleted successfully!");
         }
        clicked = false;
        editingRow = -1;
       
    });
        return null;
    }
    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

   private void deleteFromDatabase(int id) {
    String URL = "jdbc:mysql://localhost:3306/asc_db";
    String USER = "root";
    String PASS = "";
    String query = "DELETE FROM customers WHERE customer_id=?";

    try (Connection connect = (Connection) DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement statement = connect.prepareStatement(query)) {

        statement.setInt(1, id);
        int affected = statement.executeUpdate();

        if (affected > 0) {
        } else {
            JOptionPane.showMessageDialog(null,
                    "No record found with ID: " + id,
                    "Delete Failed",
                    JOptionPane.WARNING_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
                "Error deleting record: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
    }
}

}
private void addDeleteButtonToTable() {
    jTable1.getColumn("Action").setCellRenderer(new ButtonRenderer());
    jTable1.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

    jTable1.getColumn("Action").setMinWidth(40);
    jTable1.getColumn("Action").setPreferredWidth(40);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Customer Accounts");
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 110));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Complete Name", "Email Address", "Account Created", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 980, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained

    }//GEN-LAST:event_jTable1FocusGained

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       loadCustomerData();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(CustomerAccountsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerAccountsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerAccountsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerAccountsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerAccountsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}