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
		this.title = name;
		this.audio = new Audio("src/Songs/"+title+"/"+title+".wav");
	}
	
	public Song(String name, BeatMap map, File song){
		this.title = name;
		this.map = map;
		
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
		FileInputStream fileOut;
		ObjectInputStream outStream;
		try{
			fileOut = new FileInputStream("src/Songs/"+this.title+"/"+this.title+".songMap");
			outStream = new ObjectInputStream(fileOut);
			this.map = ((BeatMap) outStream.readObject()).getMap();
			
			outStream.close();
			fileOut.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String toString(){
		return this.title;
	}

}
