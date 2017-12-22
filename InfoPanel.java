/*
 * Barry Eom (beom01)
 * comp86 HW5
 * 11/16/2017
 *
 * InfoPanel:
 * Panel class that prints out the status of the game
 */ 

import java.awt.*;
import javax.swing.*;
import java.text.*;

public class InfoPanel extends ControlPanel {
        private JLabel troopcount, wwcount, days;
        private DecimalFormat dec;
        
        public InfoPanel() {
                super(15, 15, "Westeros Status");
                troopcount = new JLabel("Remaining Troops: ");
                wwcount = new JLabel("Remaining White Walkers: ");
                days = new JLabel("Days passed: ");
                add(troopcount);
                add(wwcount);
                add(days);
                dec = new DecimalFormat("#0.0");

        }

        public void updateInfo(int troop, int ww, double time) {
                troopcount.setText("Remaining Troops: " + troop);
                wwcount.setText("Remaining White Walkers: " + ww);
                days.setText("Days passed: " + dec.format(time));
        }
}