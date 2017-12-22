/*
 * Barry Eom (beom01)
 * comp86 HW4
 * 11/1/2017
 *
 * RavenL:
 * This class contains the list that keeps track of all the Raven modules
 * As an example, it stores all the battalion units
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;
 
public class RavenL extends JScrollPane implements ListSelectionListener {
        private ArrayList<String> ravens;
        private JList<String> list;
        private DefaultListModel<String> listSet;
        private Map map;
  
        public RavenL(Map map) {   
                this.map = map; 
                ravens = new ArrayList<String>(map.getRavenNum());
                for(int i = 0; i < map.getRavenNum(); i++) {
                        ravens.add("Unit " + map.getLabel(i));
                }
                setPreferredSize(new Dimension(100, 250));
                // Add raven's list 
                String names[] = ravens.toArray(new String[ravens.size()]);
                listSet = new DefaultListModel<String>();
                list = new JList<String>();
                list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

                for (String s : ravens){
                        listSet.addElement(s); 
                }
                list.setModel(listSet);

                getViewport().add(list); 
                list.addListSelectionListener(this);
        }

        public void removeMod() {
                if (list.isSelectionEmpty())
                        return;
                else {
                        listSet.remove(list.getSelectedIndex());
                        list.setSelectedIndex(0);
                }
        }

        public void removeSelected(int i) {
                if (i < 0 || i >= listSet.size()) return;
                listSet.remove(i);
                list.setSelectedIndex(0);
        }

        public void addMod(String s) {
                listSet.addElement(s);
        }

        public void valueChanged (ListSelectionEvent e) {
                // Print selected Raven diagram
                if (e.getValueIsAdjusting())
                        return;
                if (list.isSelectionEmpty())
                        return;
                else {
                int i = list.getSelectedIndex();                
                map.setSelectedIndex(i);
                map.repaint();
                }
        }
}