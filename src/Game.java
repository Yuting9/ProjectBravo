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
		addKeyListener(this);
		setLayout(null);
		//why = new DrawPanel();
		image = new ImageIcon("bin/Unititled-2.png");
		label = new JLabel(image);
		this.add(label);
		label.setLocation(0, 0);
		this.repaint();
		this.revalidate();
		song.addMap();
		for (Note n: song.map)
			System.out.println(n.length);
		
		timer.start();
		this.repaint();
	}
	
	public Game(){
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, this);
		g.setColor(Color.BLACK);
		g.drawLine(100, 0, 100, 600);
		g.drawLine(100*2, 0, 100*2, 600);
		g.drawLine(100*3, 0, 100*3, 600);
		g.drawLine(100*4, 0, 100*4, 600);
		g.drawLine(100*5, 0, 100*5, 600);
		g.drawLine(0, 580, 600, 580);
		g.setColor(Color.blue);
		for (Note n: song.map){
			g.fillRect(100*n.position,5*(time - n.time) - 5*n.length, 100,5*n.length);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(5*time-20==600)
		{
			song.audio.start();
		}
		System.out.println(song.audio.percentDone());
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
