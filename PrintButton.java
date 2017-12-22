/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 10/19/2017
 *
 * PrintButton:
 * Prints the string of the button
 *
 */ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrintButton extends JButton {
        private String string_var;
        public PrintButton(String string_var) {   
                this.string_var = string_var;
                this.setText(string_var);
                this.addActionListener(
                        new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                        System.out.println(PrintButton.this.string_var);
                                }
                        }
                ); 
        }

} 