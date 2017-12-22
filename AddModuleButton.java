/*
 * Barry Eom (beom01)
 * comp86 HW4
 * 11/1/2017
 *
 * AddModuleButton:
 * This class is the button class responsible for adding raven and battalion troops
 * from the table
 */ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddModuleButton extends JButton implements ActionListener {
        private String s1;
        Map map;
        NewModule unit;
        RavenL list;

        public AddModuleButton(String s1, Map map, RavenL list) {
                this.list = list;
                this.s1 = s1;
                this.map = map;
                this.setText(s1);
                addActionListener(this);
                unit = new NewModule(map, list);
        }
        public void actionPerformed(ActionEvent e) {
                unit.setVisible(true);
        }
}