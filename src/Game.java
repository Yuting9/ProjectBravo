import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Game extends JPanel{
	public static ArrayList<Song> songs = new ArrayList<Song>();
	public static RotatingMenu gameMenu = new RotatingMenu();
	
	public Game(Song beats){
		
	}
	
	public static void GameSetup()
	{
		File folder = new File("src/Songs/");
		System.out.println(folder.listFiles());
		for (final File fileEntry : folder.listFiles()){
	    	songs.add(new Song(fileEntry.getName()));
	    }
		for (Song i: songs){
			gameMenu.add_button(i.title);
		}
		GameFrame.add(gameMenu);
		gameMenu.revalidate();
		gameMenu.repaint();
	}
	
	

}
