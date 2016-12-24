/**
 * The Beatmap for the game
 * 
 * @author Yuting, Ganashsai
 */

import java.io.Serializable;
import java.util.ArrayList;

public class BeatMap implements Serializable{
	private static ArrayList<Note> map;
	
	public BeatMap(){
		map = new ArrayList<Note>();
	}
	
	public ArrayList<Note> getMap(){
		return map;
	}
}

class Note{
	int length, position, time;
	
	Note(int p, int l, int t){
		position = p;
		length = l;
		time = t;
	}
}