/**
 * This is the game. GV tells me it's the game. I'm not sure personally, but I sort of have to trust him on this
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
	private int max_index;
	private int timeHeld[] = new int[6];
	private int currentNote[] = new int[6];
	private int score;
	
	private static BufferedImage image;

	
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
		
		try{
			image= ImageIO.read(new File("src/Assets/background.png"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		GameFrame.add(gameMenu);
		gameMenu.revalidate();
		gameMenu.repaint();
		
	}
	
	public Game(Song song){
		GameFrame.clear();
		GameFrame.add(this);
		//System.out.println("playing: "+song.title);
		this.song=song;
		this.setLayout(null);
		addKeyListener(this);
		setLayout(null);
		//why = new DrawPanel();
		this.repaint();
		this.revalidate();
		song.addMap();
		for (int i=0;i<6;i++)
			currentNote[i]=-1;
		score=0;
		timer.start();
		this.repaint();
		this.requestFocus();
	}
	
	public Game(){
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, null);
		if (g instanceof Graphics2D){
			Paint p = new GradientPaint(0.0f, 0.0f, new Color(0,0,0,200), 0.0f, getHeight(), new Color(40,40,40,50));
			Graphics2D g2D = (Graphics2D) g;
			g2D.setPaint(p);
			g2D.fillRect(0,0,getWidth(),getHeight());
		}
		g.setColor(Color.WHITE);
		g.drawLine(100, 0, 100, 600);
		g.drawLine(100*2, 0, 100*2, 600);
		g.drawLine(100*3, 0, 100*3, 600);
		g.drawLine(100*4, 0, 100*4, 600);
		g.drawLine(100*5, 0, 100*5, 600);
		g.drawLine(0, 550, 600, 550);
		g.drawLine(0, 510, 600, 510);
		g.setColor(Color.blue);
		for (Note n: song.map){
			if(timeHeld[n.position]!=0 && 5*(time - n.time - n.length)<510 && 5*(time - n.time)>=510)
			{
				g.fillRect(100*n.position,5*(time - n.time - n.length), 100,510-5*(time - n.time - n.length));
			}
			else if (5*(time - n.time) - 5*n.length<=510)
			{
				g.fillRect(100*n.position,5*(time - n.time - n.length), 100,5*n.length);
			}
			//if (5*(time-n.time-n.length)==510)
				//System.out.println(time-102+"   "+n.time+"    "+(time-102-n.time-n.length));
			else
				max_index=song.map.indexOf(n);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(5*(time-(int)Calibrate.avg)==510)
		{
			System.out.println("started");
			song.audio.start();
		}
		if(song.audio.percentDone()==100)
		{
			this.timer.stop();
			GameFrame.reset();
			RotatingMenu.reset();
			songs.clear();
			gameMenu= new RotatingMenu();
			GameFrame.add(new MainMenu());
			
		}
		//System.out.println(song.audio.percentDone());
		this.repaint();
		time++;
	}
	
	public void keyPressedChecker(int index)
	{
		boolean breaker = false;
		for (int i=max_index;i<song.map.size();i++)
		{
			Note n= song.map.get(i);
			for(int difference=-5;difference<6;difference++){
				if (n.time==time+i && n.position==index)
				{
					System.out.println(difference);
					System.out.println();
					score+=6-Math.abs(difference);
					currentNote[index]=i;
					breaker=true;
					break;
				}
			}
			if(breaker)
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyChar());
		if(arg0.getKeyChar()=='s')
		{
			keyPressedChecker(0);
			timeHeld[0]++;
		}
		if(arg0.getKeyChar()=='d')
		{
			keyPressedChecker(1);
			timeHeld[1]++;
		}
		if(arg0.getKeyChar()=='f')
		{
			keyPressedChecker(2);
			timeHeld[2]++;
		}
		if(arg0.getKeyChar()=='j')
		{
			keyPressedChecker(3);
			timeHeld[3]++;
		}
		if(arg0.getKeyChar()=='k')
		{
			keyPressedChecker(4);
			timeHeld[4]++;
		}
		if(arg0.getKeyChar()=='l')
		{
			keyPressedChecker(5);
			timeHeld[5]++;
		}
	}
	
	public void keyReleasedChecker(int index)
	{
		if(currentNote[index]!=-1){
			Note n = song.map.get(currentNote[index]);
			int d=10;
			for(int diff=-5;diff<5;diff++)
			{
				if(time+diff== n.time)
				{
					d=Math.abs(diff);
					break;
				}
			}
			score+=6-d;
			System.out.println(score);
			timeHeld[index]=0;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyChar()=='s')
		{
			keyReleasedChecker(0);
		}
		if(arg0.getKeyChar()=='d')
		{
			keyReleasedChecker(1);
		}
		if(arg0.getKeyChar()=='f')
		{
			keyReleasedChecker(2);
		}
		if(arg0.getKeyChar()=='j')
		{
			keyReleasedChecker(3);
		}
		if(arg0.getKeyChar()=='k')
		{
			keyReleasedChecker(4);
		}
		if(arg0.getKeyChar()=='l')
		{
			keyReleasedChecker(5);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
