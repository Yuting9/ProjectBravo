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
	public Image img;
	public File song;
	public Audio audio;
	
	public Song(String name){
		title = name;
		map = new ArrayList<Note>();
		audio = new Audio("src/Songs/"+title+"/"+title+".wav");
	}
	
	public Song(String name, BeatMap beat, File songFile){
		title = name;
		map = beat.inportMap(title);
		song = songFile;
		audio = new Audio("src/Songs/"+title+"/"+title+".wav");
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
	
	public String toString(){
		return this.title;
	}

}
