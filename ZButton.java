/*
 * Barry Eom (beom01)
 * comp86 HW5
 * 11/16/2017
 *
 * Raven:
 * Button class that allows the scale in the map class to be adjusted,
 * thereby changing the zoom view of map
 */ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZButton extends JButton implements ActionListener {

        private String s1;
        private Map map;
        private boolean in;

        public ZButton(String s1, Map map, boolean in) {
                this.s1 = s1;
                this.map = map;
                this.in = in;

                setText(s1);
                addActionListener(this);
        }

         public void actionPerformed(ActionEvent e) {
                if(in) {
                        map.inZoom();
                } else {
                        map.outZoom();
                }

        }

}