package CoreJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import CoreJava.SizedFrame;

public class CoreJavaFrame {
    private Color backgroundColor;
    public boolean flag;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SizedFrame frame = new SizedFrame();
                frame.setTitle("CoreJavaFrame");
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        new CloseWarning();
                    }
                });
/**
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        int result = JOptionPane.showConfirmDialog(
                                frame,
                                "Warning: This window will be closed.",
                                "Closing Warning", JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        if (result == JOptionPane.YES_OPTION){
                            System.exit(0);
                        }
                    }
                });
 */

                frame.setVisible(true);
                frame.setResizable(true);
            }
        });
    }

}

class NotHelloWorldComponent extends JComponent{
    public static final int MESSAGE_X = 10;
    public static final int MESSAGE_Y = 50;
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 50;

    public void paintComponent(Graphics g){
        g.drawString("Not a Hello, World problem",
                MESSAGE_X, MESSAGE_Y);
    }
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

class DrawComponent extends JComponent{
    /**
     * public void paintComponent(Graphics g)
     * is the fixed method implemented in class JComponent.
     * And its parameter:
     * @param g: can be only the "Graphics" but not others.
     */
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        float leftX = 200f;
        float topY = 200f;
        float width = 200f;
        float height = 150f;

        Rectangle2D rect = new Rectangle2D.Float(
                leftX, topY, width, height);
        g2.draw(rect);

        Ellipse2D ellipse = new Ellipse2D.Float();
        ellipse.setFrame(rect);
        g2.draw(ellipse);

        g2.draw(new Line2D.Float(
                leftX, topY, leftX+width, topY+height));

        float centerX = (float) rect.getCenterX();
        float centerY = (float) rect.getCenterY();
        float radius = 50f;

        Ellipse2D circle = new Ellipse2D.Float();
        circle.setFrameFromCenter(centerX, centerY,
                centerX+radius, centerY+radius);
        g2.draw(circle);
    }

    /*
    public Dimension getPreferredSize(){
        return new Dimension();
    }
     */
}

class ImageComponent extends JComponent{
    private Image image;

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    final int screenHeight = screenSize.height;
    final int screenWidth = screenSize.width;

    public ImageComponent(){
        image = new ImageIcon("E:\\fromFirefox\\wallpicGirl.jpg").getImage();
    }
    public void paintComponent(Graphics g){
        if ( image == null) return;

        // ???
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        g.drawImage(image, 0, 0,
                screenWidth/3, screenHeight/3, null);
    }
}

