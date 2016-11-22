import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class Game extends JPanel{
	private static final long serialVersionUID = 1L;
	public static ArrayList<Song> songs = new ArrayList<Song>();
	public static RotatingMenu gameMenu = new RotatingMenu();
	
	public Game(Song beats){
		System.out.println("Playing: "+beats.title);
	}
	
	public static void GameSetup()
	{
		//temp
		
		GameFrame.add(gameMenu);
		gameMenu.revalidate();
		gameMenu.repaint();
		
		
		
	}

}
