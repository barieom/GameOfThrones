/*
 * Barry Eom (beom01)
 * comp86 HW5
 * 11/16/2017
 *
 * Main:
 * Main class which consolidates all the different java classes to make an
 * interactive beta-version video game of Game of Thrones
 *
 */ 
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
 
public class Main implements ActionListener{ 
        static final int TICK = 100;
        double day;
        Map map;
        VelocityPanel vp;
        RavenL list;
        JFrame window;
        InfoPanel gamestate_info;

        public Main() { 
                run();
        }
 
        private void run() {
                window = new JFrame("Game of Thrones Game");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel mainPanel = new JPanel(new BorderLayout()); 
                window.add(mainPanel);
                Timer t = new Timer(TICK, this);
                map = new Map(21);   
                day = 0;
  
                // Set battlefield buttons 
              
                ControlPanel colorset = new ControlPanel(25, 40, "Pick battlefield type and magnification");
                colorset.setPreferredSize(new Dimension(1200, 150));
                colorset.addBackGroundColorButton("Paper", map, 1);
                colorset.addBackGroundColorButton("Sea", map, 2);
                colorset.addBackGroundColorButton("Bloody", map, 3);                
                mainPanel.add(colorset, BorderLayout.PAGE_END);

                // Set canvas
                map.setPreferredSize(new Dimension(800, 600));
                mainPanel.add(map, BorderLayout.CENTER);

                // Fill the l_Panel (left panel) panel with buttons and set
                // velocity movements
                ControlPanel l_Panel = new ControlPanel(130, 0, "Tactical Movement");
                l_Panel.setPreferredSize(new Dimension(200, 300));
                vp = new VelocityPanel(map, l_Panel.getPreferredSize()); 
                gamestate_info = new InfoPanel();
                gamestate_info.setPreferredSize(new Dimension(180, 150));
                l_Panel.add(vp);
                l_Panel.add(gamestate_info);
                
                mainPanel.add(l_Panel, BorderLayout.LINE_START);

                // r_Panel (right panel) for warrior units
                ControlPanel r_Panel = new ControlPanel(100, 25, "GoT War Units");
                r_Panel.setPreferredSize(new Dimension(200, 600));
               

                // Miscelleneous buttons at the top 
                ControlPanel strategy_button = new ControlPanel(25, 40, "Create reinforcements?");
                strategy_button.setPreferredSize(new Dimension(1200, 100));
                
                
                // Destroy and add troop buttons
                list = new RavenL(map);
                list.setPreferredSize(new Dimension(100, 300));
                DeleteModuleButton dmb = new DeleteModuleButton("Destroy Troop", map, list);
                AddModuleButton amb    = new AddModuleButton("Add reinforcement Troops", map, list);
                strategy_button.add(dmb);
                strategy_button.add(amb);
                mainPanel.add(strategy_button, BorderLayout.PAGE_START);

                
                r_Panel.add(list);
                StopButton motion = new StopButton("Freeze Gameplay", "Continue Gameplay", t, colorset);
                r_Panel.add(motion);
                mainPanel.add(r_Panel, BorderLayout.LINE_END);

                colorset.addZButton("ZOOM IN", map, true);
                colorset.addZButton("ZOOM OUT" , map, false);


                //Show&display
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);

                t.start();
        }

        public void actionPerformed(ActionEvent e) {
                if (vp.hasUpdate()) {
                        vp.updateVelocity();
                }
                int game;
                game = map.tickAll(list);
                if (game == -1) {
                        gameOver();
                } else if (game == 0) {
                        winGame();
                }
                gamestate_info.updateInfo(map.getRavenNum(), map.getWhiteNum(), ++day/100);
                map.repaint();
        }
        public static void main(String[] args) {
                Main main = new Main();
        }

        private void winGame() {
                window.setVisible(false);
                try { Thread.sleep(500); } catch (Exception e) {}
                System.out.println("Winter has come and gone...");
                try { Thread.sleep(1500); } catch (Exception e) {}
                System.out.println("The White Walkers have been eradicated");
                try { Thread.sleep(2500); } catch (Exception e) {}
                System.out.println("You've saved the world!! Congratulations!");
                try { Thread.sleep(2500); } catch (Exception e) {}
                System.out.println("Too bad this is just a game :/");
                try { Thread.sleep(2500); } catch (Exception e) {}
                System.out.println("Game over!!");
                System.exit(0);
                return;
        }

        private void gameOver() {
                window.setVisible(false);
                try { Thread.sleep(500); } catch (Exception e) {}
                System.out.println("Winter has come and stayed...");
                try { Thread.sleep(1500); } catch (Exception e) {}
                System.out.println("The White Walkers have conquered Westeros");
                try { Thread.sleep(2500); } catch (Exception e) {}
                System.out.println("You're responsible for this... the end of the world");
                try { Thread.sleep(2500); } catch (Exception e) {}
                System.out.println("Disappointment... There's nothing more to say... ");
                try { Thread.sleep(2500); } catch (Exception e) {}
                System.out.println("Game over!!");
                System.exit(0);
                return;
        }
} 