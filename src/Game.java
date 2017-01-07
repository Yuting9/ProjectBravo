/**
 * This is the game. GV tells me it's the game. I'm not sure personally, but I sort of have to trust him on this
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.io.File;
import java.io.FilenameFilter;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Game extends JPanel implements ActionListener, KeyListener{
	public static ArrayList<Song> songs = new ArrayList<Song>();
	public static RotatingMenu gameMenu = new RotatingMenu();
	private Timer timer = new Timer(16,this);
	private Song song;
	private int time;
	private JLabel label;
	private ImageIcon image;
	//private static ImageIcon image;
	private JPanel why;

	
	public static void GameSetup(){
		File folder = new File("src/Songs/");
		for (final File fileEntry : folder.listFiles()){
			if(fileEntry.isDirectory()){
		    	songs.add(new Song(fileEntry.getName()));
			}
	    }
		for (Song i: songs){
			gameMenu.add_button(i.title);
		}
		
		//try{
		//	image= ImageIO.read(new File("bin/Untitled-2.png"));
		//}catch(Exception e)
		//{
		//	e.printStackTrace();
		//}
		
		GameFrame.add(gameMenu);
		gameMenu.revalidate();
		gameMenu.repaint();
	}
	
	public Game(Song song){
		GameFrame.clear();
		GameFrame.add(this);
		System.out.println("playing: "+song.title);
		this.song=song;
		this.setLayout(null);
		song.get_BeatMap();
		addKeyListener(this);
		setLayout(null);
		//why = new DrawPanel();
		image = new ImageIcon("bin/Unititled-2.png");
		label = new JLabel(image);
		this.add(label);
		label.setLocation(0, 0);
		this.repaint();
		this.revalidate();
		for (Note n: song.map)
			System.out.println(n.length);
		song.audio.start();
		timer.start();
		this.repaint();
	}
	
	public Game(){
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, this);
		g.setColor(Color.blue);
		for (Note n: song.map){
			g.fillRect(100*n.position,5*(time - n.time) - 5*n.length, 100,5*n.length);
		}
	}
	
	/*
	class DrawPanel extends JPanel{
		int std = 0, stf = 0, stj = 0, stk = 0;
		
		public DrawPanel(){
			this.repaint();
		}
		
		public void drawMap(Graphics g){
			int place = this.getWidth()/6;
			g.setColor(Color.blue);
			g.fillRect(place*n.position,5*(time - n.time) - 5*n.length, place,5*n.length);
			}
		}
		

		
		public void paintComponent(Graphics g){
			int place = this.getWidth()/6;
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.drawLine(place, 0, place, this.getHeight());
			g.drawLine(place*2, 0, place*2, this.getHeight());
			g.drawLine(place*3, 0, place*3, this.getHeight());
			g.drawLine(place*4, 0, place*4, this.getHeight());
			g.drawLine(place*5, 0, place*5, this.getHeight());
			
			drawMap(g);
		}
	}
	*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.print("stuff");
		this.repaint();
		time++;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
