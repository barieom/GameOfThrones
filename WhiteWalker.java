/*
 * Barry Eom (beom01)
 * comp86 HW5
 * 11/16/2017
 *
 * WhiteWalker:
 * White Walker class that draws an evil white walker (or wights!!
 */ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class WhiteWalker extends Raven {
        private int xWW[] = {0, 4, 5, 8, 7, 8, 8, 3, 6, 6, 8,  8,  6, 6, 2, -2, -6,-6,-8, -8, -6, -6, -3, -8, -8, -7, -8, -5, -4}; 
        private int yWW[] = {0, 0, 2,-2, 4, 5, 7, 9, 9, 8, 7, 11, 14,16,20, 20, 16,14,11,  7,  8,  9,  9,  7,  5,  4, -2,  2,  0};
        private int crawl_speed = 15;

        public WhiteWalker(int x, int y, int id) {
                super(x, y, 0, "Evillll", id, "WhiteWalker");
                raven = new Polygon(xWW, yWW, xWW.length);
        }

        public void tick() {
                y += .15;
        }
}