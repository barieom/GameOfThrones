/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 10/19/2017
 *
 * VelocityPanel:
 * This class contains the list that keeps track of all the Raven modules
 * As an example, it stores all the battalion units
 */ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import javax.swing.text.*;
import java.beans.PropertyChangeListener; 
import java.beans.PropertyChangeEvent;
import java.text.*;
 
public class VelocityPanel extends JPanel implements PropertyChangeListener {
        private double vx, vy;
        private VelocitySelector velocity;
        private JFormattedTextField xText, yText;
        private NumberFormat format;
        private JPanel xTextPanel, yTextPanel;
        private JLabel xTextLabel, yTextLabel;
        private Map map;
        // private Thread thread;

        public VelocityPanel(Map map, Dimension size) {
                this.map = map;
                velocity = new VelocitySelector(50, 50, 5);
                this.add(velocity);

                
                setLayout(new FlowLayout(FlowLayout.CENTER, 100, 15));
                Dimension newSize = new Dimension(size.width - 40, 275);
                setPreferredSize(newSize); 
                format = NumberFormat.getNumberInstance();

                // set velocity format for x and y
                xTextPanel = new JPanel();
                xText = new JFormattedTextField(format);
                xText.setPreferredSize(new Dimension(65, 25));
                xText.setHorizontalAlignment(SwingConstants.RIGHT);
                xText.setValue(new Double(0.00));
                xText.setColumns(5);
                xText.addPropertyChangeListener("value", this);
                xTextLabel = new JLabel("X Velocity:");
                xTextPanel.add(xTextLabel);
                xTextPanel.add(xText);

                yTextPanel = new JPanel();
                yText = new JFormattedTextField(format);
                yText.setPreferredSize(new Dimension(65, 25));
                yText.setHorizontalAlignment(SwingConstants.RIGHT);
                yText.setValue(new Double(0.00));
                yText.setColumns(5);
                yText.addPropertyChangeListener("value", this);
                yTextLabel = new JLabel("Y Velocity:");
                yTextPanel.add(yTextLabel);
                yTextPanel.add(yText);

                this.add(xTextPanel);
                this.add(yTextPanel); 
        }
        
        public void updateVelocity() {
                vx = velocity.getx() / 5; 
                vy = velocity.gety() / 5;
                map.setModuleVelocity(vx, vy, map.getSelectedIndex());
                xText.setValue(new Double(vx * 5));
                yText.setValue(new Double(vy * 5));

        } 

        public boolean hasUpdate() {
                return velocity.hasUpdate();
        }
 
        public void propertyChange(PropertyChangeEvent e) {
                Object source = e.getSource();
                double vel;
                if (source == xText) {
                        vel = ((Number)xText.getValue()).doubleValue();
                        velocity.setx(vel);
                } else if (source == yText) {
                        vel = ((Number)yText.getValue()).doubleValue();
                        velocity.sety(vel);
                }       
        }
}