package CoreJava;

import gui.NewBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseWarning extends JFrame {
    private boolean flag;
    FlowLayout layout = new FlowLayout(
           FlowLayout.CENTER, 5, 5);
    JPanel labelPanel;
    JPanel buttonPanel;

    public CloseWarning(){
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Close Warning!");

        this.setLabelPanel();
        this.setButtonPanel();

        this.add(labelPanel);
        this.add(Box.createVerticalStrut(5));
        this.add(buttonPanel);

        this.setVisible(true);
        this.setResizable(false);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        final int screenHeight = screenSize.height;
        final int screenWidth = screenSize.width;

        this.setBounds(screenWidth/2-150, screenHeight/2-75, 300, 150);
        // this.setLocationByPlatform(true);
        // this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void setLabelPanel(){
        JLabel warningLabel = new JLabel("Warning: this window will be closed.");
        warningLabel.setAlignmentX(TOP_ALIGNMENT);
        warningLabel.setHorizontalAlignment(0);
        // warningLabel.setBorder(BorderFactory.createEmptyBorder(4,5,0,5));
        labelPanel = new JPanel();
        labelPanel.setLayout(layout);
        // labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        // labelPanel.setBorder(BorderFactory.createBevelBorder(1));
        labelPanel.add(warningLabel);
    }

    private void setButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton confirm = new JButton("Confirmation");
        JButton cancel = new JButton("Cancel");

        buttonPanel.add(confirm);
        buttonPanel.add(Box.createHorizontalStrut(90));
        buttonPanel.add(cancel);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("true");
                System.exit(0);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("false");
                CloseWarning.this.dispose();
            }
        });
    }
/**
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CloseWarning();
            }
        });
    }
 */
}
