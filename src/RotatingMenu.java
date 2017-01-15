/**
 * Selects the song from a list
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class RotatingMenu extends JPanel implements MouseMotionListener, MouseListener, ActionListener{
	
	private static ArrayList<JButton> buttons = new ArrayList<JButton>();
	public static JButton btnReturn = new JButton("Back");
	private int allx,ally;
	private static int movement;
	private Timer timer= new Timer(16,this);
	private static int shift= 0;
	public RotatingMenu(){
		timer.start();
		JLabel lblUp = new JLabel(), lblDown = new JLabel();
		GameFrame.state= "RotatingMenu";
		this.setLayout(null);
		addMouseMotionListener(this);
		
		lblUp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblDown.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblUp.setBounds(230, 0, 260, 50);
		lblDown.setBounds(230, 521, 260, 50);
		btnReturn.setBounds(490,521,110,50);
		btnReturn.addActionListener(this);
		this.add(lblUp);
		this.add(lblDown);
		this.add(btnReturn);
		if (GameFrame.state.equals("Game")){
			this.revalidate();
			this.repaint();	
		}
		System.out.println("here");
		for(Song s:Game.songs){
			System.out.println(s);
		}
		for (JButton b: buttons){
			System.out.println(b.getName());
		}
	}
	public static void reset()
	{
		buttons.clear();
	}
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	public void add_button(String title){
		int temp_x=40*buttons.size()+(int)(shift%10000);
		buttons.add(new JButton(title));
		buttons.get(buttons.size()-1).setSize(7*title.length()+40,30);
		buttons.get(buttons.size()-1).setName(title);
		//System.out.println(buttons.get(buttons.size()-1).getName());
		buttons.get(buttons.size()-1).setLocation((int)(-4*(temp_x*temp_x-570*temp_x)/10000), temp_x);
		buttons.get(buttons.size()-1).addMouseListener(this);
		this.add(buttons.get(buttons.size()-1));

		requestFocus();
	}
	
	public static int getMovement(){
		return movement;
	}
	
	public static ArrayList<JButton> getButtons(){
		return buttons;
	}
	
	public static void update(){
		int temp_x;
		for(int i=0;i<buttons.size();i++){
			temp_x=40*i+(int)(shift%10000);
			buttons.get(i).setLocation((int)(-4*(temp_x*temp_x-570*temp_x)/10000), temp_x);
			//System.out.println((int)(-4*(temp_x*temp_x-570*temp_x)/1000));
			//System.out.println(temp_x);
			//System.out.println();
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
			//System.out.println("GOING DOWN");
			movement=-2;
		}
		else if(ally<=50 && allx>130){
			//System.out.println("GOING UP");
			movement=2;
		}
		else
			movement=0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
		//System.out.println("playing "+Game.songs.get(buttons.indexOf(e.getComponent())));
		Game.songs.get(buttons.indexOf(e.getComponent())).audio.stop();
		new Game(Game.songs.get(buttons.indexOf(e.getComponent())));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		;
		if(e.getSource().getClass() == JButton.class){
			e.getComponent().getName();
			//System.out.println("Show clipart for: " +e.getComponent().getName());
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
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==timer){
			if(movement==2 && shift<0 ){
				shift+=7;	
				update();
			}
			if(getMovement()==-2 && shift>530+(40*(buttons.size()-1))*-1 ){
				shift-=7;
				update();
			}	
		}
		if(arg0.getSource() == btnReturn){
			System.out.println("doing this");
			timer.stop();
			GameFrame.reset();
			RotatingMenu.reset();
			Game.songs.clear();
			buttons.clear();
			GameFrame.add(new MainMenu());
		}
	}

	
	
}
