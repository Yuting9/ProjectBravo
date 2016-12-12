import java.util.ArrayList;

public class BeatMap {
	private static int vars; // Placeholder
	private static ArrayList<Note> map;
	
	public BeatMap(){
		map = new ArrayList<Note>();
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