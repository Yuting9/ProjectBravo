import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class Game extends JPanel{
	private static final long serialVersionUID = 1L;
	public static ArrayList<Song> songs = new ArrayList<Song>();
	public static RotatingMenu gameMenu = new RotatingMenu();
	
	public Game(Song beats){
		
	}
	
	public static void GameSetup()
	{
		//begin temporary code
		for(int i =0; i<20; i++)
		{
			songs.add(new Song("Song #"+Integer.toString(i)));
		}
		
		//end temporary code
		for (Song i: songs)
		{
			gameMenu.add_button(i.title);
		}
		
		GameFrame.add(gameMenu);
		gameMenu.revalidate();
		gameMenu.repaint();
		
	}

}
