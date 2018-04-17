/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomgraphics2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author hanse
 */
public class LineExample extends JPanel {

    private final int weigth = 1000;//anchura
    private final int heigth = 680;//altura

    public LineExample() {
        this.setPreferredSize(new Dimension(weigth, heigth));
    } // constructor

    private void draw(Graphics g) {
        for (int i = 0; i < 1000; i++) {
            int x1 = (int) (Math.random() * (weigth));
            int x2 = (int) (Math.random() * (weigth));
            int y1 = (int) (Math.random() * (heigth));
            int y2 = (int) (Math.random() * (heigth));
            if (x1 != x2) {
                if ((y1 + y2) > 20 && (x1 + x2) > 20) {
                    g.setColor(Color.BLUE);
                    linearFunction(g, x1, x2, y1, y2);
                }
            }   
        } 
    } 

    private void linearFunction(Graphics g, double x1, double x2, double y1, double y2) {
        double y;
        double m = (y1 - y2) / (x1 - x2);
        double b = y1 - ((y1 - y2) / (x1 - x2)) * x1;  // b= y-m(x)
        for (double x = x1; x <= x2; x += 0.1 / 6) {
            y = (m * x + b);
            g.drawLine((int) coord_x(x), (int) coord_y(y), (int) coord_x(x), (int) coord_y(y));
        }
    }// linearFunction

    private double coord_x(double x) {
        return x;
    }

    private double coord_y(double y) {
        double real_y = (double) this.getHeight() - y;
        return real_y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Graphing Function");
        window.setContentPane(new LineExample());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setResizable(false);
        window.setLocation(170, 10);
        window.setVisible(true);
    }

} // fin clase

