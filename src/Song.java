/**
 * Contains the song (Deprecated?)
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.Image;
import java.io.*;
import java.util.ArrayList;


public class Song implements Serializable{
	
	
	public String title;
	public ArrayList<Note> map;
	private BeatMap temp;
	public Image img;
	public File song;
	public Audio audio;
	
	public Song(String name){
		this.title = name;
		this.audio = new Audio("src/Songs/"+title+"/"+title+".wav");
	}
	
	public Song(String name, BeatMap map, File song){
		this.title = name;
		this.map = map.getMap();
		
	}
	
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	public void export(){
		FileOutputStream fileOut;
		ObjectOutputStream outStream;
		try{
			fileOut = new FileOutputStream("/src/Songs/"+title+".songMap");
			outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void get_BeatMap(){
		this.map = temp.getMap(this.title);
	}
	
	public String toString(){
		return this.title;
	}

}
