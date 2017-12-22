/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 10/19/2017
 *
 * Battalion:    
 * Class that represents a drawing of a battalion unit (represented as a sword)
 *
 */ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
 
public class Battalion extends Raven { 
		// Polygon for Battalion, which is represented by swords
        private int [ ] x_sword = {0, 6, 5, 8, 11,10, 16, 9, 9,10, 8, 6, 7, 7};
        private int [ ] y_sword = {0, 0,20,25, 20, 0,  0,-3,-7,-8,-9,-8,-7,-3};
        
        public Battalion(double x, double y, double angle, String label, int id, String type) {
                super(x, y, angle, label, id, type);
                raven = new Polygon(x_sword, y_sword, x_sword.length);
        }
} 