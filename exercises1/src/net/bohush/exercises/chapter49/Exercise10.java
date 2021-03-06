package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise10 extends JFrame{

    public Exercise10() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new FunctionPanel(), BorderLayout.CENTER);
    }
    
    class FunctionPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(100, getHeight() / 2);
            g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            
            //x
            g2d.draw(new Line2D.Double(-50, 0, getWidth() - 150, 0));
            g2d.draw(new Line2D.Double(getWidth() - 175, -15, getWidth() - 150, 0));
            g2d.draw(new Line2D.Double(getWidth() - 175,  15, getWidth() - 150, 0));
            g2d.drawString("X", getWidth() - 150, -5);
            //y
            g2d.draw(new Line2D.Double(0, - getHeight() / 2 + 50, 0, getHeight() / 2 - 50));
            g2d.draw(new Line2D.Double(0, - getHeight() / 2 + 50, -15, - getHeight() / 2 + 75));
            g2d.draw(new Line2D.Double(0, - getHeight() / 2 + 50,  15, - getHeight() / 2 + 75));
            g2d.drawString("Y", 5, - getHeight() / 2 + 50);
            
            double x = 0;
            double y = 0;            
            double scale = 0.002;
            for (int i = 0; i < getWidth() - 150; i++) {
                double x2 = i;
                double y2 = x2 * x2;
                g2d.draw(new Line2D.Double(x, - scale * y, x2, - scale * y2));
                x = x2;
                y = y2;
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise10();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(900, 600);
        jFrame.setMinimumSize(new Dimension(400, 400));
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
