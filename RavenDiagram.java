/*
 * Barry Eom (beom01)
 * comp86 HW3
 * 11/1/2017
 *
 * RavenDiagram:
 * This class draws a polygon representing a raven in the Game of Thrones Game
 */

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class RavenDiagram extends Raven {
        // this draws a raven
        private int x_raven[] = {0, 12, 14, 15, 16, 18, 30, 26, 18, 16, 20, 10, 14, 12, 4, 0};
        private int y_raven[] = {0, 0, -6, -8, -6,  0,  0,  6,  4,  10,  12, 12, 10, 4, 6, 0};
		

        public RavenDiagram(double x, double y, double angle, String label, int id, String type) {
                super(x, y, angle, label, id, type);
                raven = new Polygon(x_raven, y_raven, x_raven.length);
        }
}