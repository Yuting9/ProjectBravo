/**
 * Contains the song (Deprecated?)
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.Image;
import java.io.*;

public class Song implements Serializable{
	
	
	public String title;
	public BeatMap map;
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
	
	public static Song inport(File file){
		FileInputStream fileOut;
		ObjectInputStream outStream;
		try{
			fileOut = new FileInputStream(file.getPath());
			outStream = new ObjectInputStream(fileOut);
			Song tempSong = (Song) outStream.readObject();
			outStream.close();
			fileOut.close();
			return tempSong;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString(){
		return this.title;
	}

}
