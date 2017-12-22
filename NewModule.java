/*
 * Barry Eom (beom01)
 * comp86 HW5
 * 11/12/2017
 *
 * NewModule:
 * Implements the addition and subtraction of troops (which I call modules) and
 * creates a separate JPanel for it
 */ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.text.*;
import java.util.Random;
 
public class NewModule extends JFrame implements ActionListener {
        private JPanel main, xPanel, yPanel, radioPanel, buttonPanel;
        private JFormattedTextField x_t, y_t;
        private ButtonGroup bg;
        private JLabel x, y;
        private Map map;
        private RavenL list;
        private Random random;
     
        public NewModule(Map map, RavenL list) {
                this.map = map;
                this.list = list;

                setTitle("Spawn New Troops");
                main = new JPanel();
                add(main);
                main.setPreferredSize(new Dimension(400, 200));

                // Sets location of where the troops will be spawned
                xPanel = new JPanel();
                x = new JLabel("Longitude: ");
                x_t = new JFormattedTextField(1500);
                x_t.setPreferredSize(new Dimension(50, 25));
                xPanel.add(x);
                xPanel.add(x_t);
                main.add(xPanel);
                yPanel = new JPanel();
                y = new JLabel("Latitude: ");
                y_t = new JFormattedTextField(2000);
                y_t.setPreferredSize(new Dimension(50, 25));
                yPanel.add(y);
                yPanel.add(y_t);
                main.add(yPanel);

                // Add troop button!!!
                radioPanel = new JPanel();
                JRadioButton raven = new JRadioButton("Raven");
                JRadioButton battalion = new JRadioButton("Battalion");
                raven.doClick();
                battalion.doClick();
                bg = new ButtonGroup();
                bg.add(raven);
                bg.add(battalion);
                radioPanel.add(raven);
                radioPanel.add(battalion);
                radioPanel.setPreferredSize(new Dimension(200, 35));

                main.add(radioPanel);
                buttonPanel = new JPanel();
                JButton ok = new JButton("Add");
                ok.addActionListener(this);
                JButton cancel = new JButton("Exit");
                cancel.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                                NewModule.this.setVisible(false);
                        }
                });
                buttonPanel.add(ok);
                buttonPanel.add(cancel);
                main.add(buttonPanel);

                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                NewModule.this.setVisible(false);
                        }
                }); 
                pack();
            }
 
            public void actionPerformed(ActionEvent e) {
                    Random rand = new Random();

                    // Generates troops at random locations throughout Westeros
                    x_t.setValue(rand.nextInt(1000) + 1000);
                    y_t.setValue(rand.nextInt(1500) + 1300);
                    int x = (int)x_t.getValue();
                    int y = (int)y_t.getValue();
                    String type = getSelectedButtonText(bg);
                    map.addModule(x, y, type);
                    list.addMod("Unit " + (map.getNumModules() - 1));

            }

            public String getSelectedButtonText(ButtonGroup buttonGroup) {
                for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) { return button.getText(); }
                }
                return null;
        }
}