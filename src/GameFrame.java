/**
 * Contains the game
 * 
 * @author Yuting, Ganashsai
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{
	
	// Frame Variables
	private static MainMenu menu = new MainMenu();
	public static String state = "Menu";
	public static Timer timer;
	public static float calibrate;
	
	// Defualt Constructor
	public GameFrame(){
		setTitle("Rhythm Master");
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add(menu);
		menu.addKeyListener(menu);
		timer = new Timer(17,this);
		timer.start();
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (state.equals("RotatingMenu")){
			if(RotatingMenu.getMovement()==2 && RotatingMenu.shift<0 ){
				RotatingMenu.shift+=7;	
				RotatingMenu.update();
				//System.out.println(RotatingMenu.shift);
			}
			if(RotatingMenu.getMovement()==-2 && RotatingMenu.shift>530+(40*(RotatingMenu.getButtons().size()-1))*-1 ){
				RotatingMenu.shift-=7;
				RotatingMenu.update();
				//System.out.println(RotatingMenu.shift);
				//System.out.println(40*(RotatingMenu.getButtons().size()-1));
				//System.out.println();
			}
			if(arg0.getSource() == RotatingMenu.btnReturn){
				
			}
		}	
	}
}
