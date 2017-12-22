/*
 * Barry Eom (beom01)
 * comp86 HW4
 * 11/1/2017
 *
 * DeleteModuleButton:
 * This class is the button class responsible for removing or destroying troops
 * from the canvas and the list
 */ 


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteModuleButton extends JButton implements ActionListener {

        private String s1;
        Map map;
        RavenL list;

        public DeleteModuleButton(String s1, Map map, RavenL list) {
                this.s1 = s1;
                this.map = map;
                this.list = list;
                this.setText(s1);
                addActionListener(this);

        }
        public void actionPerformed(ActionEvent e) {
                map.deleteModule();
                list.removeMod();
        }
}