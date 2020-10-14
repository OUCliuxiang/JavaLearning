package CoreJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class SizedFrame extends JFrame {
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JTextField printField;

    public SizedFrame(){
        //get screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        final int screenHeight = screenSize.height;
        final int screenWidth = screenSize.width;

        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // add(new NotHelloWorldComponent());
        // add(new DrawComponent());
        // add(new ImageComponent());
        /**
         * replace setSize() method to use the preferred size
         * of new added component.
         * Only when the method:
         *
         * public Dimension getPreferredSize(){
         *     return new Dimension(Width, Height)
         * }
         *
         * is overwrote.
         */
        // pack();

        // this.setContentPane(buttonPanel);
        this.setTextPanel();
        this.setButtonPanel();
        this.add(textPanel);
        this.add(buttonPanel);

        setSize(screenWidth/3, screenHeight/3);
        setLocationByPlatform(true);

        this.setVisible(true);
        this.setResizable(true);
    }

    private void setTextPanel(){
        // textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setAlignmentY(0.0f);
        // textPanel.setPreferredSize(new Dimension(300, 15));
        textPanel.setMaximumSize(new Dimension(400,25));
        // textPanel.setMinimumSize(new Dimension(30, 15));
        printField = new JTextField();
        JTextArea textArea = new JTextArea();
        // textPanel.setMaximumSize(new Dimension(mainWindowSize.width-20,25));
        textPanel.add(printField);
        textArea.append("this is a text area");
        textPanel.add(textArea);
    }

    private void setButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        // buttonPanel.setMaximumSize(new Dimension(400,40));

        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");

        OuterColorAction cyanAction = new OuterColorAction(
                "Cyan", new ImageIcon("E:\\pics\\foxwq.png"), Color.CYAN);
        JButton cyanButton = new JButton(cyanAction);

        // JButton ejectWarning = new JButton("Eject");
        /*
        Button set method -- 1: Anonymous inner class
         */
        yellowButton.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                printField.setText("Panel's color has been set as yellow.");
                textPanel.setBackground(Color.YELLOW);
                buttonPanel.setBackground(Color.YELLOW);
                SizedFrame.this.getContentPane().setBackground(Color.YELLOW);
            }
        });

        /*
        Button set method -- 2: Outer action listener class
         */
        ColorAction blueAction = new ColorAction(Color.BLUE);
        blueButton.addActionListener(blueAction);

        /*
        Button set method -- 3: call the outer function.
         */
        makeButton("Red", Color.RED);

        buttonPanel.add(yellowButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(cyanButton);

        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl C"), "panel.cyan");
        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.cyan", cyanAction);
    }

    private void makeButton(String name, final Color backgroundColor){
        JButton button = new JButton(name);
        buttonPanel.add(button);

        button.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                SizedFrame.this.getContentPane().setBackground(backgroundColor);
                textPanel.setBackground(backgroundColor);
                buttonPanel.setBackground(backgroundColor);
                printField.setText("Panel's color has been set as " + name.toLowerCase());
            }
        });
    }

    private class ColorAction implements ActionListener{
        private Color backgroundColor;
        public ColorAction(Color c){
            backgroundColor = c;
        }
        public void actionPerformed(ActionEvent e){
            printField.setText("Panel's color has been set as blue.");
            SizedFrame.this.getContentPane().setBackground(backgroundColor);
            textPanel.setBackground(backgroundColor);
            buttonPanel.setBackground(backgroundColor);
        }
    }

    private class OuterColorAction extends AbstractAction{
        public OuterColorAction(String name, Icon icon, Color color){
            this.putValue(Action.NAME, name);
            this.putValue(Action.SMALL_ICON, icon);
            this.putValue("color", color);
            this.putValue(Action.SHORT_DESCRIPTION,
                    "set panel color to " +
                            Action.NAME.toLowerCase());
        }

        public void actionPerformed(ActionEvent event){
            Color c  = (Color) this.getValue("color");
            printField.setText("Panel's color has been set as cyan.");
            SizedFrame.this.getContentPane().setBackground(c);
            textPanel.setBackground(c);
            buttonPanel.setBackground(c);
        }
    }
}


