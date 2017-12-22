/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 10/19/2017
 *
 * Dragon:
 * This class draws a polygon representing a dragon in the Game of Thrones Game
 */

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
 
public class Dragon extends Raven { 
        // this draws a dragon
        private int xDrag[] = {2, 3, 3, 7, 2, 2, 6, 22, 34, 38, 22, 5, 3, 9, 2, 0, -2, -9, -3, -5, -22, -38, -34, -22, -6, -2, -2, -7, -3, -3, -2};
        private int yDrag[] = {0, 2, 5, 8, 7,11,15,  6, 20, 23, 20,20,24,29,26,45, 26, 29, 24, 20,  20,  23,  20,   6, 15, 11,  7,  8,  5,  2,  0};
        public Dragon(double x, double y, double angle, String label, int id, String type) {
                super(x, y, angle, label, id, type);
                raven = new Polygon(xDrag, yDrag, xDrag.length);
        }
}