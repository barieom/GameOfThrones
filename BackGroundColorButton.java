/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 10/19/2017
 *
 * BackGroundColorButton:
 * This class allows for the background of map to be changed
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class BackGroundColorButton extends JButton implements ActionListener {
        private String ct;
        private Map map;
        private int color; 
        public BackGroundColorButton(String color_type, Map map, int color) {
                this.ct = color_type;
                this.map = map;
                this.color = color;
                setText(ct);
                addActionListener(this);
        }

         public void actionPerformed(ActionEvent e) {
                if (color == 1) {
                    map.setBackground(Color.WHITE);
                } else if (color == 2) {
                    map.setBackground(Color.BLUE);
                } else if (color == 3) {
                    map.setBackground(Color.RED);
                }
                map.repaint();
        }

}