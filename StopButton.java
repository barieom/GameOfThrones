/*
 * Barry Eom (beom01)
 * comp86 HW4
 * 11/1/2017
 *
 * StopButton:
 * This class allows the gameplay to stop by manipulating Timer t variable
 */ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StopButton extends JButton implements ActionListener {
        private String s1, s2;
        Timer t;
        ControlPanel background;

        public StopButton(String s1, String s2, Timer t, ControlPanel background) {
                this.background = background;
                this.s1 = s1;
                this.s2 = s2;
                this.t = t;
                this.setText(s1);
                addActionListener(this);
        }

        // stop timer if pressed
        public void actionPerformed(ActionEvent e) {
                if (this.getText().equals(s1)) {
                        System.out.println(s1);
                        this.setText(s2);
                        t.stop();
                        background.disableBackgroundChange();
                } else {
                        System.out.println(s2);
                        this.setText(s1);
                        t.start();
                        background.enableBackgroundChange();
                }
        }
}