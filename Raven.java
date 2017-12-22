/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 11/1/2017
 *
 * Raven:
 * This class implements the different modules I made for this program, as it
 * manages the data related to objects.
 */ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.*;

public abstract class Raven {
        protected Polygon raven;
        public double x, y, xv, yv;
        protected Color color;
        private int id, health;
        protected boolean selected;
        private String label, type;
 
        public Raven(double x, double y, double angle, String label, int id, String type) {
                this.x = x;
                this.y = y;
                this.label = label;
                this.type = type;
                if (type == "Raven") {
                        this.health = 1;
                } else if (type == "Dragon") {
                        this.health = 100;
                } else if (type == "Battalion") {
                        this.health = 5;
                } else if (type == "WhiteWalker") {
                        this.health = 2;
                }
                this.id = id;
                xv = 0; 
                yv = 0;

                selected = false;
        }

        // Make Modules move
        public void setSpeed(double xv, double yv) { this.xv = xv; this.yv = yv; }
        public void setColor(Color color) { this.color = color; }
        public void damage       () { 
                health -= 1;
                System.out.println(type + " Unit " + id + " health is " + health); 
        }
        public boolean isDead    () { if (health == 0) return true; return false; }
        public int getHealth     () { return health; } 
        public String getLabel   () { return label; }
        public String getType    () { return type; }
        public void setSelected  () { selected = true; }
        public void unsetSelected() { selected = false; }
        public double getx       () { return x; }
        public double gety       () { return y; }
        public void tick         () { x += xv; y += yv; }
        public boolean getloc(double x, double y, double scale, double osetx, double osety) {
                AffineTransform t = new AffineTransform();
                t.scale(scale, scale);
                t.translate(this.x + osetx, this.y + osety);
                Shape r = t.createTransformedShape(raven);
                return r.contains(x, y);
        }

        // Update drawing
        public void draw(Graphics g) {        
                Graphics2D graphics2 = (Graphics2D) g.create();
                if (selected) {
                        graphics2.setColor(Color.YELLOW);
                        selected = false;
                } else if (type == "WhiteWalker") {
                        graphics2.setColor(Color.WHITE);
                } else {
                        graphics2.setColor(Color.MAGENTA);
                }
                
                graphics2.translate(x, y);
                graphics2.fillPolygon(raven);

                if (type == "WhiteWalker"){
                        graphics2.setColor(new Color(100, 200, 255));
                        graphics2.drawPolygon(raven);
                }

                graphics2.dispose();
                graphics2 = (Graphics2D) g.create();
                graphics2.translate(x, y);
                graphics2.setColor(Color.WHITE);

                if (type != "WhiteWalker") {
                        graphics2.drawString("Unit: " + Integer.toString(id), 0, -8);
                }
                graphics2.dispose();
        }
}