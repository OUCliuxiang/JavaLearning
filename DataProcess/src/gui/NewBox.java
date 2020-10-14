package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author OUC_LiuX
 * @see <a href="www.bing.com">The Bing home page<a/>
 */

public class NewBox extends JFrame{
    private static final long serialVersionUID = 1L;
    JTextField textField = null;
    JButton chooseButton = null;
    JButton confirmation = null;
    JButton closeWindows = null;

    public NewBox(){
        this.setTitle("Files Selection Windows");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 5);
        JLabel label = new JLabel("Select the file: ");
        textField = new JTextField(30);
        chooseButton = new JButton("Browse");
        confirmation = new JButton("Confirmation");
        closeWindows = new JButton("Cancel");

        // the layout setting:
        layout.setAlignment(FlowLayout.CENTER);
        this.setLayout(layout);
        this.setBounds(400, 200, 600, 500);
        this.setVisible(true);
        this.setResizable(true);
        this.add(label);
        this.add(textField);
        this.add(chooseButton);
        this.add(confirmation);
        this.add(closeWindows);
        chooseFile();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void chooseFile(){
        /**
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showDialog(new JLabel(), "Selection");
                File file = chooser.getSelectedFile();
                textField.setText(file.getAbsoluteFile().toString());
            }
        });
         */
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showDialog(new JLabel(), "Selection");
                chooser.showDialog(new JLabel(), "Cancel");
                File file = chooser.getSelectedFile();
                textField.setText(file.getAbsoluteFile().toString());
            }
        });
    }

    public void cancelAndClose(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Windows Closing. ");
                System.exit(0);
            }
        });
        closeWindows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This button performs the windows close.");
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewBox();
            }
        });
    }
}
