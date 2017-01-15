/**
 * Contains the game
 * 
 * @author Yuting, Ganashsai
 */

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
	
	// Frame Variables
	public static MainMenu menu = new MainMenu();
	public static String state = "Menu";
	public static float calibrate;
	
	// Defualt Constructor
	public GameFrame(){
		setTitle("Rhythm Master");
		setSize(600,600);
		setVisible(true);
		//setBackground(new Color(0,0,0,0));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add(menu);
		menu.addKeyListener(menu);
	}
	
	// Resets the main frame
	public static void reset(){
		Main.frame.getContentPane().removeAll();
		Main.frame.getContentPane().add(menu);
	}
	
	// 
	public static void clear(){
		//System.out.println("Cleared frame");
		Main.frame.getContentPane().removeAll();
	}
	
	public static void add(JPanel jpanel){
		//System.out.print(jpanel);
		//System.out.println("added to frame");
		Main.frame.getContentPane().add(jpanel);
		Main.frame.repaint();
	}
	
	public void paint( Graphics g){
		super.paint(g);
	}
}
