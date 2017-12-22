/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 11/1/2017
 *
 * ControlPanel:
 * Sets up the settings and configurations for the control panels that are 
 * represented in the main window
 */ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
 
public class ControlPanel extends JPanel { 

        private ArrayList<JButton> Background_arr;

        public ControlPanel(int hgap, int vgap, String title) {  
                setLayout(new FlowLayout(FlowLayout.CENTER, hgap, vgap));
                TitledBorder border = BorderFactory.createTitledBorder(title);
                border.setTitleJustification(TitledBorder.CENTER);
                CompoundBorder border2 = BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), border);
                setBorder(border2);
                Background_arr = new ArrayList<JButton>();
        }
        public void addButton(String s) { 
                PrintButton button = new PrintButton(s);
                this.add(button);
        }
        public void addBackGroundColorButton(String s, Map map, int color) {
                BackGroundColorButton button = new BackGroundColorButton(s, map, color);
                this.add(button);
                Background_arr.add(button);
        } 

        public void addZButton(String s, Map map, boolean i) {
                ZButton button = new ZButton(s, map, i);
                this.add(button);
                Background_arr.add(button);
        }

        public void disableBackgroundChange() {
                for (JButton jb : Background_arr) {
                        jb.setEnabled(false);
                }
        }

        public void enableBackgroundChange() {
                for (JButton jb : Background_arr) {
                        jb.setEnabled(true);
                }       
        }

}