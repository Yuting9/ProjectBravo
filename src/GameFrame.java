/**
 * Contains the game
 * 
 * @author Yuting, Ganashsai
 */

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame implements GlobalVar {
    
    // Frame Variables
    public  MainMenu menu = new MainMenu();
    public static String state = "Menu";
    public static float calibrate;
    Container c;
    //public static Audio bgm;
    // Defualt Constructor
    public GameFrame(){
        setTitle("Rhythm Master");
        c = this.getContentPane();

        setVisible(true);
        //setBackground(new Color(0,0,0,0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        //bgm = new Audio("src/Assets/backgroundMoosic.wav");
        //bgm.loop();
        //System.out.println("doing this");
        getContentPane().add(menu);
        menu.addKeyListener(menu);
        setSize(600,600);
        //bgm.start();
    }
    
    // Resets the main frame

    public void reset(){
        frame.setTitle("Rhythm Master");
        c.removeAll();
        c.add(this.menu);
    }
    
    // 
    public void clear(){
        //System.out.println("Cleared frame");
        c.removeAll();
    }
    
    public void add(JPanel jpanel){
        //System.out.print(jpanel);
        //System.out.println("added to frame");
        
        //Container 
        c.setLayout(new BorderLayout());
        c.add(jpanel,BorderLayout.CENTER);
        repaint();
    }
    
    public void paint( Graphics g){
        super.paint(g);
    }
}
