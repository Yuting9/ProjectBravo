/**
 * The Beatmap for the game
 * 
 * @author Yuting, Ganashsai
 */

import java.io.*;
import java.util.ArrayList;

public class BeatMap{
	// We're gonna have to sort these by start time
	private static ArrayList<Note> map;
	
	public BeatMap(){
		map = new ArrayList<Note>();
	}
		
	public ArrayList<Note> getMap(){
		return map;
	}

	
	public void add(int l, int p, int t){
		//insertion sort here
		map.add(new Note(p,l,t));
	}
	
	public void writeMap(File beatPlace){
		try {
			System.out.println("Created the Beatmap: " + beatPlace.createNewFile());
			FileOutputStream fileOut = new FileOutputStream(beatPlace);
			PrintWriter outFile = new PrintWriter(fileOut);
			for(Note note : map){
				System.out.println(note);
				outFile.println(note);
			}
			outFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}

class Note{
	int length, position, time;
	Note(int p, int l, int t){
		position = p;
		length = l;
		time = t;
	}
	public String toString(){
		return position + " " + length + " " + time;
	}
}