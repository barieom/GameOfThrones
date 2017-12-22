/*
 * Barry Eom (beom01)
 * comp86 HW5
 * 11/16/2017
 *
 * BorderBound:
 * This sets borders for the game
 */ 

import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;


public class BorderBound {
        private Color c;
        private double x, y, width, height;
        
        public BorderBound(double x, double y, double w, double h, Color c) {
                this.width  = w;
                this.height = h;
                this.x = x;
                this.y = y;
                this.c = c;
        }

        public boolean contains(double x, double y) {
                return ((this.x<x) && (this.x+width>x)) && ((this.y<y) && (this.y+height>y));
        }
        
        public void draw(Graphics g) {
                Graphics2D graphics2 = (Graphics2D) g.create();
                graphics2.translate(x, y);
                graphics2.setColor(c);
                graphics2.fillRect(0, 0, (int)width, (int)height);
        }
        public double getx() { return x; }
        public double gety() { return y; }
}