/*
 * Barry Eom (beom01)
 * comp86 HW5
 * 11/1/2017
 *
 * Map:
 * This class is the canvas. Creates series of diagram classes - ranging from
 * ravens to ground-troop-battalions. I think this class highlights the 
 * well designed abstraction and the modularity I implemented for this program.
 */ 

import java.awt.*;
import java.util.ArrayList;
import java.awt.font.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;
import java.util.Random;
import java.lang.*;

public class Map extends JPanel implements MouseListener {
        // index of the currently active Module
        private int selectedIndex = 0;
        private int ravenNum;
        private int w, h;
        private double scale, x_adj, y_adj;
        private final Font font = new Font("Cambria", Font.PLAIN, 16);
        private ArrayList<Raven> ravens;   
        private BufferedImage westeros;
        private ArrayList<BorderBound> bound;
        private ArrayList<WhiteWalker> whitewalkers;
        
        public Map(int num) {      
                this.ravens = new ArrayList<Raven>();
                scale = 1.0; 
                x_adj = 0; 
                y_adj = 0;
                ravenNum = num; 
                bound = new ArrayList<BorderBound>();
                whitewalkers = new ArrayList<WhiteWalker>();

                for (int i = 0; i < ((ravenNum - 3)/2); i++) {  
                        RavenDiagram rav = new RavenDiagram(1400+i*20, 1300, 0, Integer.toString(i), i, "Raven");
                        ravens.add(rav);
                } 
                for (int i = 0; i < ((ravenNum - 3)/2); i++) {
                	Battalion army = new Battalion(820+i*20, 2540, 0, Integer.toString(50 + i), 50 + i, "Battalion");
                        ravens.add(army);
                }
                for (int i = 0; i < 3; i++) {
                	Dragon drag = new Dragon(2030+i*30, 2510, 0, Integer.toString(100 + i), i + 100, "Dragon"); 
                        ravens.add(drag);
                }

                // Spawn WhiteWalkers at random locations throughout the map
                Random rand = new Random();
                int x_rand, y_rand;
                for (int i = 0; i < 666; i++) {
                        x_rand = rand.nextInt(100) + 25;
                        y_rand = rand.nextInt(600) + 0;
                        WhiteWalker wight = new WhiteWalker(870+i+x_rand, -100 + y_rand, i);
                        whitewalkers.add(wight);
                }

                try {
                        westeros = ImageIO.read(new File("Known_World.JPEG"));
                        
                } catch (IOException ex) {
                        // do nothing
                }

                int size = 10000;
                int width = 9300;
                int height  = 7000;
                BorderBound l = new BorderBound(-400, -800, 300, height, Color.BLUE);
                BorderBound r = new BorderBound(width-400, -800, 300, height, Color.RED);
                BorderBound b = new BorderBound(-100, height-1300, width, 500, Color.BLUE);
                BorderBound t = new BorderBound(-100, -800, width, 500, Color.RED);
                bound.add(l);
                bound.add(r);
                bound.add(b);
                bound.add(t);

                addMouseListener(this);

        }  

        private void doDrawing(Graphics g) {
                Graphics2D graphics2 = (Graphics2D) g;
                Dimension size = getSize();
                w = size.width;
                h = size.height;

                if (ravens.size() > selectedIndex) {
                        x_adj = w / 2 / scale - ravens.get(selectedIndex).getx();
                        y_adj = h / 2 / scale - ravens.get(selectedIndex).gety();
                } else if (ravens.size() <= selectedIndex)
                        return;

                graphics2.setFont(font); 
                graphics2.scale(scale, scale);
                graphics2.translate(x_adj, y_adj);

                // Draws the Westeros map onto the map
                graphics2.drawImage(westeros, -100, -300, null);

                // Draws both troops and white walkers onto the map
                for(int i = 0; i < ravenNum; i++) { 
                        if (i != selectedIndex) { 
                                ravens.get(i).draw(g);  
                        }
                }
                for (int i = 0; i < whitewalkers.size(); i++) {
                        whitewalkers.get(i).draw(g);
                }

                ravens.get(selectedIndex).setSelected();
                ravens.get(selectedIndex).draw(g);

                for (int i = 0; i < bound.size(); i++) {
                        bound.get(i).draw(g);
                }
        }

        public int tickAll(RavenL mlist) {

                //handles whitewalker movement
                loopW:
                for (int i = 0; i < whitewalkers.size(); i++) {
                        whitewalkers.get(i).tick();
                        // check if combat with any other module
                        for (int j = 0; j < ravenNum; j++) {
                                double x = whitewalkers.get(i).getx();
                                double y = whitewalkers.get(i).gety();
                                if (ravens.get(j).getloc(x, y, 1.0, 0, 0)) {
                                        whitewalkers.get(i).damage();
                                        ravens.get(j).damage();
                                        if (ravens.get(j).isDead() || whitewalkers.get(i).isDead()) {
                                                if (ravens.get(j).isDead()) {
                                                         printDead(j);
                                                         ravens.remove(j); 
                                                         mlist.removeSelected(j);
                                                         selectedIndex = 0;
                                                         ravenNum -= 1;
                                                }
                                                if (whitewalkers.get(i).isDead()) {
                                                         whitewalkers.remove(i);
                                                }
                                                continue loopW;
                                        }
                                }
                        }
                        // If white walkers have gotten by this point in the latitude
                        // they have overtaken Westeros. Game over!
                        if (whitewalkers.get(i).gety() >= 3750) {
                                return -1;
                        }
                }
                // No more white walkers! Game over!
                if (whitewalkers.size() == 0) {
                        return 0;
                }


                // Handles all module (dragon, battalion, raven) movements
                loopSkip:
        	for(int i = 0; i < ravenNum; i++) {
        		ravens.get(i).tick();
                        double x = ravens.get(i).getx();
                        double y = ravens.get(i).gety();
                        
                        //handles if module crashes outside the map
                        for (int j = 0; j < bound.size(); j++) {
                                if (bound.get(j).contains(ravens.get(i).getx(), ravens.get(i).gety())) {
                                        printDead(i);
                                        ravens.remove(i);
                                        mlist.removeSelected(i);
                                        ravenNum -= 1;
                                        selectedIndex = 0;
                                        continue loopSkip;
                                }
                        }

                        // handles crash
                        for(int j = 0; j < ravenNum; j++) {
                                // Checks if any two modules crashed
                                if (j != i && ravens.get(j).getloc(x,y,1.0,0,0)) {
                                        // if the two modules are the same types, don't do anything
                                        if (ravens.get(i).getType() == ravens.get(j).getType())
                                                break;

                                        // colliding modules should take damage
                                        ravens.get(i).damage(); 
                                        ravens.get(j).damage();

                                        // destroy troop if the health is 0
                                        if (j > i) {
                                                if (ravens.get(j).isDead()) {
                                                         printDead(j);
                                                         ravens.remove(j); 
                                                         mlist.removeSelected(j);
                                                         ravenNum -= 1;
                                                }
                                                if (ravens.get(i).isDead()) {
                                                         printDead(i);
                                                         ravens.remove(i);
                                                         mlist.removeSelected(i);
                                                         ravenNum -= 1;
                                                }
                                                selectedIndex = 0;
                                                continue loopSkip;
                                        } else {
                                                if (ravens.get(i).isDead()) {
                                                        printDead(i);
                                                        ravens.remove(i); 
                                                        mlist.removeSelected(i);
                                                        ravenNum -= 1;
                                                }
                                                if (ravens.get(j).isDead()) {
                                                        printDead(j);
                                                        ravens.remove(j);
                                                        mlist.removeSelected(j);
                                                        ravenNum -= 1;
                                                }
                                                selectedIndex = 0;
                                                continue loopSkip;
                                        }
                                }
                        }
        	}
                return 1;
        } 

	@Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                doDrawing(g);
                
        }

        public void deleteModule() {
        	ravens.remove(selectedIndex);
        	ravenNum--;
        }

        public void addModule(int x, int y, String type) {
        	if (type.equals("Raven")) {
        		RavenDiagram raven_new = new RavenDiagram(x, y, 0, Integer.toString(ravenNum), ravenNum, "Raven");
        		ravens.add(raven_new);
        		ravenNum++;
        	}
        	else {
        		Battalion batt_new = new Battalion(x, y, 0, Integer.toString(ravenNum), ravenNum, "Battalion");
        		ravens.add(batt_new);
        		ravenNum++;
        	}
        }
 
        // getters and setters for the values
        public int getNumModules    () { return ravenNum; }
        public void setSelectedIndex(int i) { selectedIndex = i; }
        public int getSelectedIndex () { return selectedIndex; } 
        public int getRavenNum      () { return ravenNum; } 
        public int getWhiteNum      () { return whitewalkers.size(); }
        public void inZoom          () { scale *= 1.5; }
        public void outZoom         () { scale /= 1.5; }
        public String getLabel      (int i) { 
                if ( i >= ravens.size()) 
                        return "";
                return ravens.get(i).getLabel(); 
        }
        public void setModuleVelocity(double xv, double yv, int ModuleIndex) {
                ravens.get(ModuleIndex).setSpeed(xv, yv);
        }
        
        

        // Module click 
        public void mousePressed(MouseEvent e) {
                int x_e = e.getX();
                int y_e = e.getY();
                for (int i = 0; i < ravenNum; i++) {
                        if (ravens.get(i).getloc(x_e, y_e, scale, x_adj, y_adj)) {
                                ravens.get(selectedIndex).unsetSelected();
                                selectedIndex = i;
                                ravens.get(i).setSelected();
                        }
                }
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e)  {}
        public void mouseExited(MouseEvent e)   {}
        public void mouseClicked(MouseEvent e)  {}

        private void printDead(int j) {
                System.out.println("A " + ravens.get(j).getType() + " perished");
        }
}