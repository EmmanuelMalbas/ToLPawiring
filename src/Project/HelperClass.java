package Project;
import javax.swing.JFrame;

public class HelperClass {
    public HelperClass(JFrame panel) {
            panel.setVisible(true);
            panel.pack();
            panel.setLocationRelativeTo(null);
            panel.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
