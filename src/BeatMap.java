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
	
	public ArrayList<Note> inportMap(String title){
		System.out.println("start of getmap");
		BufferedReader input=null;
		try{
			input= new BufferedReader(new FileReader("src/Songs/"+title+"/"+title+".songMap"));
		}catch(Exception e){
			e.printStackTrace();
		}
		String line = null;
		String[] strNums;
		int parameters[] = new int[3];
		try {
			while((line=input.readLine()) != null){
				strNums=line.split("\\s");
				for(int i=0;i<3;i++)
				{
					parameters[i]=Integer.parseInt(strNums[i]);
				}
				System.out.println(parameters);
				add(parameters[1],parameters[0],parameters[2]);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
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