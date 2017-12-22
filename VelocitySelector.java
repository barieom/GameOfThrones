/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 10/19/2017
 *
 * VelocitySelector:
 * This class creates a 2D selector that can be dragged to modify the velocity
 * of the ravens
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.util.ArrayList;

public class VelocitySelector extends JPanel implements MouseMotionListener {

        private double x, y;
        private int w, h, radius; 
        private boolean updated;

        public VelocitySelector(int w, int h, int radius) {
                setPreferredSize(new Dimension(w, h));
                this.radius = radius;
                this.w = w;
                this.h = h;
                x = w / 2;
                y = h / 2;
                updated = true;
                addMouseMotionListener(this);
        }

        public double getx() {
                return x - w / 2;
        }
        public double gety() {
                return y - h / 2;
        }
        public void setCoords(double xv, double yv) {
                x = xv / 10;
                y = yv / 10;
                repaint();
        }
        public void setx(double xv) {
                x = xv + w / 2;
                repaint();
        }
        public void sety(double yv) {
                y = yv + h / 2;
                repaint();
        }

        public boolean hasUpdate() {
                if (updated) {
                        updated = false;
                        return true;
                }

                return false; 
        }

        private void doDrawing(Graphics g) {
                Graphics2D graphics2 = (Graphics2D) g;
                Dimension size = getSize();
                w = size.width;
                h = size.height;

                graphics2.setColor(Color.WHITE);
                graphics2.fillRect(0, 0, w, h);

                // graphics2.setColor(Color.GRAY);
                // graphics2.drawLine(w/2, 1, h/2, h - 1);
                // graphics2.drawLine(1, w/2, w - 1, h/2);

                graphics2.setColor(Color.BLACK);
                graphics2.setColor(graphics2.getColor().darker());
                graphics2.fillOval((int)x - radius - 2, (int)y - radius - 2, radius * 2+4, radius * 2+4);

                graphics2.setColor(Color.BLACK);
                graphics2.fillOval((int)x - radius, (int)y - radius, radius * 2, radius * 2);

        }

        @Override
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
                updated = true;
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint();
        }
        public void mouseMoved(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}

}