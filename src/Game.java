import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Game extends JPanel{
	private static final long serialVersionUID = 1L;
	public static ArrayList<Song> songs = new ArrayList<Song>();
	public static RotatingMenu gameMenu = new RotatingMenu();
	
	public Game(Song beats){
		
	}
	
	public static void GameSetup()
	{
		final File folder = new File("/src/temp");
		String[] directories = folder.list(new FilenameFilter() {
				@Override
				public boolean accept(File current, String name){
					return new File(current, name).isFile();
				}
		});
		System.out.println(Arrays.toString(directories));
			
		for (final File fileEntry : folder.listFiles()) 
	    {
	    	songs.add(new Song(fileEntry.getName()));
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