/**
 * Selects the song from a list
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class RotatingMenu extends JPanel implements MouseMotionListener, MouseListener, KeyListener{
	
	public static ArrayList<JButton> buttons = new ArrayList<JButton>();
	public int allx,ally;
	public static int movement;

	public static int shift= 0;
	public RotatingMenu(){
		JLabel lblUp = new JLabel(), lblDown = new JLabel();
		GameFrame.state= "RotatingMenu";
		this.setLayout(null);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		lblUp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblDown.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblUp.setBounds(130, 0, 264, 50);
		lblDown.setBounds(130, 521, 264, 50);
		this.add(lblUp);
		this.add(lblDown);
		if (GameFrame.state.equals("Game")){
			this.revalidate();
			this.repaint();	
		}
	}
	
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	public void add_button(String title){
		int temp_x=40*buttons.size()+(int)(shift%10000);
		buttons.add(new JButton(title));
		buttons.get(buttons.size()-1).setSize(100,30);
		buttons.get(buttons.size()-1).setName(title);
		System.out.println(buttons.get(buttons.size()-1).getName());
		buttons.get(buttons.size()-1).setLocation((int)(-4*(temp_x*temp_x-570*temp_x)/10000), temp_x);

		buttons.get(buttons.size()-1).addMouseListener(this);
		this.add(buttons.get(buttons.size()-1));

		requestFocus();
	}
	
	public static void update(){
		int temp_x;
		for(int i=0;i<buttons.size();i++){
			temp_x=40*i+(int)(shift%10000);
			buttons.get(i).setLocation((int)(-4*(temp_x*temp_x-570*temp_x)/10000), temp_x);
			System.out.println((int)(-4*(temp_x*temp_x-570*temp_x)/1000));
			System.out.println(temp_x);
			System.out.println();
			Game.gameMenu.add(buttons.get(i));
		}	
		Game.gameMenu.revalidate();
		Game.gameMenu.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		allx = e.getX();
		ally = e.getY();
		if(ally>=520 && allx>130){
			System.out.println("GOING DOWN");
			movement=-2;
		}
		else if(ally<=50 && allx>130){
			System.out.println("GOING UP");
			movement=2;
		}
		else
			movement=0;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {	}

	@Override
	public void mouseEntered(MouseEvent e) {
		;
		if(e.getSource().getClass() == JButton.class){
			e.getComponent().getName();
			System.out.println("Show clipart for: " +e.getComponent().getName());
			Game.songs.get(buttons.indexOf(e.getComponent())).audio.start();
			// Image Get And Set Here
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {	
		Game.songs.get(buttons.indexOf(e.getComponent())).audio.stop();
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		// Untested Code - Please Test
		if(arg0.getKeyCode() == 38){
			movement = 55;
		}
		else if(arg0.getKeyCode() == 40){
			movement = -55;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
